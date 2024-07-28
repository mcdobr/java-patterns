package me.mircea.patterns.design.singleton;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


class LazySingletonTest {

    @Test
    void shouldNotBeNull() {
        assertThat(LazySingleton.getInstance()).isNotNull();
    }

    @Test
    void shouldInstantiateOnlyOnce() {
        LazySingleton firstReference = LazySingleton.getInstance();
        LazySingleton secondReference = LazySingleton.getInstance();

        assertThat(firstReference).isSameAs(secondReference);
    }

    @Test
    void shouldInstantiateOnlyOnceOverMultipleThreads() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Callable<LazySingleton> runnableTask = LazySingleton::getInstance;

        List<Future<LazySingleton>> futures = executorService.invokeAll(
                IntStream.of(100)
                        .mapToObj(i -> runnableTask)
                        .toList()
        );

        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {
            System.out.println("Waiting...");
        }

        List<LazySingleton> singletonReferences = futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        LazySingleton expected = LazySingleton.getInstance();

        assertThat(singletonReferences).containsOnly(expected);
    }
}