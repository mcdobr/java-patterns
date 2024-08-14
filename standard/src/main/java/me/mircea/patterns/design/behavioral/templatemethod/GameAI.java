package me.mircea.patterns.design.behavioral.templatemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Template method pattern is based on inheritance, while Strategy is based on composition.
 * See <a href="https://refactoring.guru/design-patterns/template-method">...</a>.
 */
@Slf4j
public abstract class GameAI {

    /**
     * The actual template method, describing the desired result as a list of steps.
     * The actual steps are to be done by the concrete implementations.
     */
    void turn() {
        collectResources();
        buildStructures();
        buildUnits();
        attack();
    }

    void collectResources() {
        log.info("Collecting resources...");
    }

    abstract void buildStructures();

    abstract void buildUnits();

    abstract void attack();
}
