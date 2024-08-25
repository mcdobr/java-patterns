package me.mircea.patterns.concurrency.synchronization;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedTask {
    public void showcaseReentrancyOfSynchronizedBlock() {
        String currentThreadName = Thread.currentThread().getName();
        synchronized (this) {
            log.info("Executing this from {}", currentThreadName);

            synchronized (this) {
                log.info("Doing this again from the same thread {}", currentThreadName);
            }
        }
    }
}
