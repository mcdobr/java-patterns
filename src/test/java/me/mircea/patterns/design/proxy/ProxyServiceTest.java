package me.mircea.patterns.design.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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