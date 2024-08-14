package me.mircea.webapp.ioc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beans")
public class BeanController {
    private final SingletonBean singletonBean;

    private final PrototypeBean prototypeBean;

    public BeanController(SingletonBean singletonBean, PrototypeBean prototypeBean) {
        this.singletonBean = singletonBean;
        this.prototypeBean = prototypeBean;
    }

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
}
