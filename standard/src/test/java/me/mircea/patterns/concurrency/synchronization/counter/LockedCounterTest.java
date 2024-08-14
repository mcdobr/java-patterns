package me.mircea.patterns.concurrency.synchronization.counter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class LockedCounterTest {
    @Test
    void shouldCount() throws InterruptedException {
        final int numberOfTasks = 1000;

        SafeAtomicCounter safeAtomicCounter = new SafeAtomicCounter();
        ConcurrencyCounterTestUtils.executeTasks(10, numberOfTasks, safeAtomicCounter);
        log.info("Value of atomic counter is {}", safeAtomicCounter.getValue());
        assertThat(safeAtomicCounter.getValue()).isEqualTo(numberOfTasks);
    }
}