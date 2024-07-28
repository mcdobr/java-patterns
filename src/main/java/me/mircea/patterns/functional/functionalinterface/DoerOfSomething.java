package me.mircea.patterns.functional.functionalinterface;

/**
 * Functional or SAM (single abstract method) interface. Implementations of functional interfaces
 * can be done as lambda functions, as opposed to usages of anonymous classes as you needed in the past.
 * <p>
 * Famous examples:
 * - in Java SE: {@link Runnable}.
 */
@FunctionalInterface
public interface DoerOfSomething {
    void doSomething();
}
