package me.mircea.webapp.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SingletonBeanTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldCreateSingletonOnlyOnce() {
        SingletonBean reference = applicationContext.getBean(SingletonBean.class);
        SingletonBean secondReference = applicationContext.getBean(SingletonBean.class);

        assertThat(reference).isSameAs(secondReference);
    }
}