package me.mircea.patterns.design.proxy;

public class ConcreteService implements Service {
    @Override
    public String doSomething() {
        return "Concrete";
    }
}
