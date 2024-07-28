package me.mircea.patterns.concurrency.synchronization.counter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UnsafeCounterTest {
    @Test
    void shouldHaveLessOrEqualThanNumberOfActualOperations() throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        int numberOfTasks = 1_000;
        ConcurrencyCounterTestUtils.executeTasks(10, numberOfTasks, unsafeCounter);

        log.info("Value of unsafe counter is {}", unsafeCounter.getValue());
        assertThat(unsafeCounter.getValue()).isLessThanOrEqualTo(numberOfTasks);
    }
}