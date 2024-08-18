package me.mircea.patterns.benchmarks;

import me.mircea.webapp.patterns.functional.IterativeOperations;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@State(value = Scope.Benchmark)
public class IterativeOperationsBenchmark {
    List<Integer> integers;

    @Setup
    public void setUp() {
        integers = IntStream.range(1, 1_000_000)
                .map(integer -> ThreadLocalRandom.current().nextInt())
                .boxed()
                .toList();
    }

    @Benchmark
    public void measureSumWithStreams() {
        IterativeOperations.sumWithStreams(integers);
    }

    @Benchmark
    public void measureSumWithClassicLoop() {
        IterativeOperations.sumWithClassicLoop(integers);
    }

    @Benchmark
    public void measureSumWithIterators() {
        IterativeOperations.sumWithIterators(integers);
    }

    @Benchmark
    public void measureWithForEachLoop() {
        IterativeOperations.sumWithForEachLoop(integers);
    }
}
