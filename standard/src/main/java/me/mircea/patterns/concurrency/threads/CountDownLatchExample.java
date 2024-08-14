package me.mircea.patterns.concurrency.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This example assumes no dependency between the subtasks.
 * <p>
 * For {@link CountDownLatch} all threads must count down in order for all of them to proceed.
 * <p>
 * See <a href="https://medium.com/@satyendra.jaiswal/unlocking-the-power-of-concurrency-utilities-a-deep-dive-into-countdownlatch-cyclicbarrier-and-25cd312f488b">this</a>
 */
@Slf4j
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfSubtasks = 3;
        CountDownLatch taskIsReady = new CountDownLatch(numberOfSubtasks);

        new Thread(new IndependentSubTask("Back-end", taskIsReady)).start();
        new Thread(new IndependentSubTask("Front-end", taskIsReady)).start();
        new Thread(new IndependentSubTask("Infrastructure", taskIsReady)).start();

        taskIsReady.await();
        log.info("All subtasks are done so task is done");
    }

    static class IndependentSubTask implements Runnable {
        private final String subject;
        private final CountDownLatch latch;

        public IndependentSubTask(String subject, CountDownLatch latch) {
            this.subject = subject;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000));
                log.info("Finished {} subtask", subject);
                latch.countDown();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
