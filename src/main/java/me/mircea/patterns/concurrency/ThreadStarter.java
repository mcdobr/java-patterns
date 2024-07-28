package me.mircea.patterns.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStarter {
    public static void main(String[] args) throws InterruptedException {
        Thread extendedThread = createExtendedThread();
        extendedThread.join();

        Thread threadWithTask = createThreadWithTask();
        threadWithTask.join();
    }

    private static Thread createExtendedThread() {
        return new ExtendedThread();
    }

    private static Thread createThreadWithTask() {
        Runnable runnable = () -> log.info("Running in {} from runnable task", Thread.currentThread().getName());
        return new Thread(runnable);
    }

    static class ExtendedThread extends Thread {
        @Override
        public void run() {
            log.info("Running in {}", Thread.currentThread().getName());
        }
    }
}
