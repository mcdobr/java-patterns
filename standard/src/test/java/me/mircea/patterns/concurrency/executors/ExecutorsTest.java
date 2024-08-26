package me.mircea.patterns.concurrency.executors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

class ExecutorsTest {
    @Test
    void shouldCreateFutureFromExecutorSubmission() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> System.out.println("abcd"));

        String expectedResult = "Abcd";
        Future<String> future = executor.submit(() -> expectedResult);

        String result;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCreateCompletableFutureFromSubmission() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        String expectedResult = "Some result";
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> expectedResult, executor);

        String result = future.join();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCreateCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        String expectedFirstResult = "abcd";
        Future<String> firstFuture = executorService.submit(() -> expectedFirstResult);
        String expectedSecondResult = "defg";
        Future<String> secondFuture = executorService.submit(() -> expectedSecondResult);

        String firstResult;
        try {
            firstResult = firstFuture.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        String secondResult;
        try {
            secondResult = secondFuture.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertThat(firstResult).isEqualTo(expectedFirstResult);
        Assertions.assertThat(secondResult).isEqualTo(expectedSecondResult);
    }

    @Test
    void shouldCreateNewThreadPerTaskExecutor() {
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(Executors.defaultThreadFactory());

        assertThat(executorService).isNotNull();
    }

    @Test
    void shouldCreateScheduledThreadPoolExecutor() {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        assertThat(executorService).isNotNull();
    }
}
