package me.mircea.patterns.design.structural.singleton;

public final class EagerSingleton {
    private final String name;

    private EagerSingleton() {
        this.name = "eager";
    }

    public String getName() {
        return name;
    }

    private static final EagerSingleton instance = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return instance;
    }
}
