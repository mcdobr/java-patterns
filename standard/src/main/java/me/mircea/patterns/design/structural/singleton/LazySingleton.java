package me.mircea.patterns.design.structural.singleton;

import java.util.Objects;

public final class LazySingleton {
    private final String name;

    public String getName() {
        return name;
    }

    private LazySingleton(String name) {
        this.name = name;
    }

    private static LazySingleton instance = null;

    public synchronized static LazySingleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LazySingleton("some name");
        }
        return instance;
    }
}
