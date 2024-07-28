package me.mircea.patterns.concurrency.synchronization.counter;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

/**
 * See https://blogs.oracle.com/javamagazine/post/the-unsafe-class-unsafe-at-any-speed
 *
 * @implNote Implemented by obtaining a varHandle on the counter instance variable.
 * That varHandle is then used in the increment function to read the volatile value and
 * then doing a "compare and set" operation to increment the value.
 */
public class SafeAtomicCounter implements Counter {
    private static final VarHandle varHandle;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            varHandle = lookup.findVarHandle(SafeAtomicCounter.class, "counter", int.class);
        } catch (ReflectiveOperationException reflectiveOperationException) {
            throw new Error(reflectiveOperationException);
        }
    }

    private volatile int counter;

    public SafeAtomicCounter() {
        this(0);
    }

    public SafeAtomicCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int getValue() {
        return this.counter;
    }

    /**
     * This is an example of <b>optimistic locking</b>.
     * The basic premise of optimistic locking is that multiple transactions can frequently
     * complete without interfering with each other: low data contention.
     * <p>
     * Phases:
     * <ul>
     * <li>begin</li>
     * <li>modify</li>
     * <li>validate</li>
     * <li>commit/rollback</li>
     * </ul>
     * <p>
     * Time-of-check to time-of-use bugs are very easy to introduce if
     * last two phases are not performed as single atomic operation.
     * <p>
     * Validation is deferred to the moment of "commit" and atomic CAS operation is used.
     *
     * @return This counter.
     */
    @Override
    public Counter increment() {
        int i;
        do {
            i = (int) varHandle.getVolatile(this);
        } while (!varHandle.compareAndSet(this, i, i + 1));
        return this;
    }
}
