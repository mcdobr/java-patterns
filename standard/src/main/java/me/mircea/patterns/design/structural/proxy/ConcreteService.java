package me.mircea.patterns.design.structural.proxy;

public class ConcreteService implements Service {
    @Override
    public String doSomething() {
        return "Concrete";
    }
}
