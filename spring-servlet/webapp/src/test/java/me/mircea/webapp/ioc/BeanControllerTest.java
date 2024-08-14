package me.mircea.webapp.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    void requestBeansShouldBeDifferentObjectsOnDifferentRequests() throws Exception {
        Long first = makeRequestBeanCall();
        Long second = makeRequestBeanCall();

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    void applicationBeansShouldBeSameObjectOnDifferentRequests() throws Exception {
        Long first = makeApplicationBeanCall();
        Long second = makeApplicationBeanCall();

        assertThat(first).isEqualTo(second);
    }

    @Test
    void sessionBeansShouldBeSameObjectOnDifferentRequests() throws Exception {
        Long first = makeSessionBeanCall();
        Long second = makeSessionBeanCall();

        assertThat(first).isEqualTo(second);
    }

    private long makeRequestBeanCall() throws Exception {
        return Long.parseLong(
                mockMvc.perform(get("/api/beans/request"))
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        );
    }

    private long makeApplicationBeanCall() throws Exception {
        return Long.parseLong(
                mockMvc.perform(get("/api/beans/application"))
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        );
    }

    private long makeSessionBeanCall() throws Exception {
        return Long.parseLong(
                mockMvc.perform(get("/api/beans/session"))
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        );
    }
}