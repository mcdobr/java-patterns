package me.mircea.patterns.concurrency.synchronization.counter;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class ConcurrencyCounterTestUtils {

    public static Counter executeTasks(final int numberOfThreads,
                                       final int numberOfTasks,
                                       Counter counter
    ) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<Counter>> tasks = IntStream.range(0, numberOfTasks)
                .mapToObj(i -> (Callable<Counter>) (counter::increment))
                .toList();
        executorService.invokeAll(tasks);

        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {
            log.info("Awaiting termination...");
        }

        return counter;
    }
}
