package me.mircea.patterns.design.proxy;

import me.mircea.patterns.design.structural.proxy.ConcreteService;
import me.mircea.patterns.design.structural.proxy.ProxyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProxyServiceTest {
    private ProxyService proxyService;

    @BeforeEach
    void setUp() {
        proxyService = new ProxyService(new ConcreteService());
    }

    @Test
    void shouldCallConcreteImplementation() {
        String result = proxyService.doSomething();

        assertThat(result).contains("Proxied");
    }
}