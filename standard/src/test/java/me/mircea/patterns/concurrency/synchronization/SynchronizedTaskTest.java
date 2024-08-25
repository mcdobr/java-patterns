package me.mircea.patterns.concurrency.synchronization;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
class SynchronizedTaskTest {

    @Test
    void shouldShowReentrancy() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        SynchronizedTask synchronizedTask = new SynchronizedTask();

        Runnable runnable = () -> synchronizedTask.showcaseReentrancyOfSynchronizedBlock();
        IntStream.range(1, 100)
                .forEach(count -> executorService.submit(() -> synchronizedTask.showcaseReentrancyOfSynchronizedBlock()));
    }
}