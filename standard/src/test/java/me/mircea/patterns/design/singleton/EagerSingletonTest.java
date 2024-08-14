package me.mircea.patterns.design.singleton;

import me.mircea.patterns.design.structural.singleton.EagerSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class EagerSingletonTest {
    @Test
    void shouldBeInstantiated() {
        assertNotNull(EagerSingleton.getInstance());
    }

    @Test
    void shouldBeTheSameInstance() {
        EagerSingleton firstReference = EagerSingleton.getInstance();
        EagerSingleton secondReference = EagerSingleton.getInstance();
        assertSame(firstReference, secondReference);
    }
}