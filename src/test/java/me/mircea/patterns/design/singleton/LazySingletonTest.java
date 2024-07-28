package me.mircea.patterns.design.singleton;

import me.mircea.patterns.design.structural.singleton.LazySingleton;
import org.junit.jupiter.api.Test;

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
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Callable<LazySingleton> runnableTask = LazySingleton::getInstance;

        int numberOfTasks = 100;
        List<Callable<LazySingleton>> tasks = IntStream.range(0, numberOfTasks)
                .mapToObj(i -> runnableTask)
                .toList();
        List<Future<LazySingleton>> futures = executorService.invokeAll(tasks);

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