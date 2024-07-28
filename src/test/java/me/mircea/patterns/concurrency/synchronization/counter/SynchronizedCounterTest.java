package me.mircea.patterns.concurrency.synchronization.counter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SynchronizedCounterTest {
    @Test
    void shouldHaveCounterEqualToNumberOfTasks() throws InterruptedException {
        final int numberOfTasks = 1000;

        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        ConcurrencyCounterTestUtils.executeTasks(10, numberOfTasks, synchronizedCounter);
        log.info("Value of synchronized counter is {}", synchronizedCounter.getValue());
        assertThat(synchronizedCounter.getValue()).isEqualTo(numberOfTasks);
    }
}