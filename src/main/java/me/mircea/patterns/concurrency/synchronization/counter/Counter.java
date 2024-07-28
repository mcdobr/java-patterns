package me.mircea.patterns.concurrency.synchronization.counter;

/**
 * A simple counter. Supports fluent style incrementing.
 */
public interface Counter {
    int getValue();

    Counter increment();
}
