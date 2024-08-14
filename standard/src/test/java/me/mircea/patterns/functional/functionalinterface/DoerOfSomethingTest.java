package me.mircea.patterns.functional.functionalinterface;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class DoerOfSomethingTest {
    @Test
    void shouldCreate() {
        DoerOfSomething doerOfSomething = () -> log.info("Doing something...");

        assertThat(doerOfSomething).isNotNull();
    }
}