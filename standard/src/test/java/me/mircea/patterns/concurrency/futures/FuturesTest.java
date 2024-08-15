package me.mircea.patterns.concurrency.futures;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class FuturesTest {
    @Test
    void shouldApply() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> "1234556")
                .thenApply(Integer::parseInt);

        assertThat(future.join()).isEqualTo(1234556);
    }

    @Test
    void shouldCompose() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> {
                            simulateNetworkCall(10);
                            return s + " World!";
                        }
                ));

        assertThat(future.join()).isEqualTo("Hello World!");
    }

    @Test
    void shouldCombine() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Something nice")
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> "Something with spice"),
                        (str, something) -> str + " " + something
                );

        assertThat(future.join()).isEqualTo("Something nice Something with spice");
    }

    @Test
    void shouldApplyToEither() {
        String serviceAResult = "Some string";
        String serviceBResult = "Other string";
        CompletableFuture<String> requestToServiceA = CompletableFuture.supplyAsync(() -> {
            simulateNetworkCall(40);
            return serviceAResult;
        });

        CompletableFuture<String> requestToServiceB = CompletableFuture.supplyAsync(() -> {
            simulateNetworkCall(50);
            return serviceBResult;
        });
        CompletableFuture<String> processedEither = requestToServiceA
                .applyToEitherAsync(requestToServiceB, str -> "Result: " + str);

        String actual = processedEither.join();
        assertThat(actual).containsAnyOf(serviceAResult, serviceBResult);
        log.info(actual);
    }

    @Test
    void shouldWaitForBoth() {
        CompletableFuture<String> firstFuture = CompletableFuture.supplyAsync(() -> {
            simulateNetworkCall(10);
            return "Abcd";
        });
        CompletableFuture<String> secondFuture = CompletableFuture.supplyAsync(() -> {
            simulateNetworkCall(10);
            return "Efgh";
        });

        CompletableFuture.allOf(firstFuture, secondFuture).join();

        assertThat(firstFuture).isCompleted();
        assertThat(secondFuture).isCompleted();
    }

    private static void simulateNetworkCall(long maxMilliseconds) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(maxMilliseconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
