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

    @Override
    public Counter increment() {
        int i;
        do {
            i = (int) varHandle.getVolatile(this);
        } while (!varHandle.compareAndSet(this, i, i + 1));
        return this;
    }
}
