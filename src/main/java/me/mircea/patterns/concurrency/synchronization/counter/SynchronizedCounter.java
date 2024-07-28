package me.mircea.patterns.concurrency.synchronization.counter;

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
