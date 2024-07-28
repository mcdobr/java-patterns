package me.mircea.patterns.design.behavioral.templatemethod;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class OrcAITest {
    @Test
    void shouldGoThroughAllTurnSteps() {
        OrcAI orc = new OrcAI();

        assertThat(orc).isNotNull();
        assertThatNoException().isThrownBy(orc::turn);
    }
}