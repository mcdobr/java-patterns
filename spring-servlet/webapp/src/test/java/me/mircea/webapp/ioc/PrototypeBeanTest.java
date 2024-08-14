package me.mircea.webapp.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrototypeBeanTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldCreatePrototypeEachTime() {
        PrototypeBean reference = applicationContext.getBean(PrototypeBean.class);
        PrototypeBean secondReference = applicationContext.getBean(PrototypeBean.class);

        assertThat(reference).isNotSameAs(secondReference);
    }
}