package me.mircea.patterns.concurrency.synchronization.counter;

/**
 * A simple counter. The
 */
public interface Counter {
    int getValue();

    Counter increment();
}
