package me.mircea.webapp.ioc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beans")
@RequiredArgsConstructor
public class BeanController {
    private final SingletonBean singletonBean;

    private final PrototypeBean prototypeBean;

    private final ApplicationBean applicationBean;

    @GetMapping("/singleton")
    public int getSingleton() {
        return singletonBean.hashCode();
    }

    @GetMapping("/prototype")
    public int getPrototype() {
        return prototypeBean.hashCode();
    }

    @GetMapping("/request")
    public int getRequest(RequestBean requestBean) {
        return requestBean.hashCode();
    }

    @GetMapping("/application")
    public int getApplication() {
        return applicationBean.hashCode();
    }
}
