package me.mircea.patterns.concurrency.synchronization.counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockedCounter implements Counter {
    private final ReentrantLock lock = new ReentrantLock();

    private int counter = 0;

    public LockedCounter() {
        this(0);
    }

    public LockedCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int getValue() {
        return counter;
    }

    @Override
    public Counter increment() {
        lock.lock();
        try {
            ++this.counter;
        } finally {
            lock.unlock();
        }
        return this;
    }

}
