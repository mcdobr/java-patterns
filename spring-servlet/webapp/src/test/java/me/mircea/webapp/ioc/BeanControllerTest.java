package me.mircea.webapp.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

//@WebMvcTest(
//        value = BeanController.class,
//        includeFilters = {@ComponentScan.Filter(type = FilterType., classes = {RequestBean.class})}
//)
@SpringBootTest
@AutoConfigureMockMvc
class BeanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldPass() throws Exception {
        Long first = makeRequestBeanCall();
        Long second = makeRequestBeanCall();

        assertThat(first).isNotEqualTo(second);
    }

    private long makeRequestBeanCall() throws Exception {
        return Long.parseLong(
                mockMvc.perform(MockMvcRequestBuilders.get("/api/beans/request"))
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        );
    }
}