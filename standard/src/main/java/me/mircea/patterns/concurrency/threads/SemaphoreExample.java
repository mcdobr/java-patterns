package me.mircea.patterns.concurrency.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Semaphore just limits the number of threads that can concurrently access the shared resource.
 */
@Slf4j
public class SemaphoreExample {
    public static void main(String[] args) {
        int permits = 2;
        Semaphore semaphore = new Semaphore(permits);

        new Thread(new Task("Alice", semaphore)).start();
        new Thread(new Task("Bob", semaphore)).start();
        new Thread(new Task("Charlie", semaphore)).start();
        new Thread(new Task("Darius", semaphore)).start();
        new Thread(new Task("Ellen", semaphore)).start();
    }

    static class Task implements Runnable {
        private final String name;
        private final Semaphore semaphore;

        public Task(String name, Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                log.info("{} waiting to use shared resource...", name);
                semaphore.acquire();
                log.info("{} is using the shared resource...", name);
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000));
                log.info("{} finished using the shared resource...", name);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        }
    }
}
