package me.mircea.patterns.design.structural.proxy;

public class ProxyService implements Service {
    private final Service service;

    public ProxyService(Service service) {
        this.service = service;
    }

    @Override
    public String doSomething() {
        return "Proxied(" + service.doSomething() + ")";
    }
}
