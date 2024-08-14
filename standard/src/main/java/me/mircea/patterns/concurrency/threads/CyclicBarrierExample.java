package me.mircea.patterns.concurrency.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * After all parties synchronize, the cyclic barrier is reset, the client code need not
 * and should not call reset on it.
 */
@Slf4j
public class CyclicBarrierExample {
    public static void main(String[] args) {
        int parties = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(
                parties,
                () -> log.info("So glad we could all gather")
        );

        new Thread(new TeamMember("Frontend", cyclicBarrier)).start();
        new Thread(new TeamMember("Backend", cyclicBarrier)).start();
        new Thread(new TeamMember("QA", cyclicBarrier)).start();

    }

    static class TeamMember implements Runnable {
        private final String role;
        private final CyclicBarrier cyclicBarrier;

        public TeamMember(String name, CyclicBarrier cyclicBarrier) {
            this.role = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            log.info("{} working on task", role);
            synchronizeBeforeNextPhase();

            log.info("Continuing to 2nd phase...");
            synchronizeBeforeNextPhase();

            log.info("Finished");
        }

        private void synchronizeBeforeNextPhase() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException brokenBarrierException) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
