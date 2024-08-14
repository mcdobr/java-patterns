package me.mircea.patterns.concurrency.synchronization.counter;

/**
 * Make the increment method synchronized. If N threads are trying to execute the increment method, only one will obtain the lock and proceed through the critical section.
 * The others will be blocked until that thread release the lock.
 * This is an example of <b>pessimistic locking</b>.
 */
public class SynchronizedCounter implements Counter {
    private int counter;

    public SynchronizedCounter() {
        this(0);
    }

    public SynchronizedCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int getValue() {
        return this.counter;
    }

    @Override
    public synchronized Counter increment() {
        ++this.counter;
        return this;
    }
}
