package me.mircea.patterns.concurrency.synchronization.counter;

public class UnsafeCounter implements Counter {
    private int counter;

    public UnsafeCounter() {
        this(0);
    }

    public UnsafeCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int getValue() {
        return this.counter;
    }

    @Override
    public Counter increment() {
        ++this.counter;
        return this;
    }
}
