package me.mircea.webapp.ioc;

import lombok.RequiredArgsConstructor;
import me.mircea.webapp.ioc.application.ApplicationBean;
import me.mircea.webapp.ioc.prototype.PrototypeBean;
import me.mircea.webapp.ioc.request.RequestBean;
import me.mircea.webapp.ioc.session.SessionBean;
import me.mircea.webapp.ioc.singleton.SingletonBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beans")
@RequiredArgsConstructor
public class BeanController {
    private final ApplicationContext applicationContext;

    private final SingletonBean singletonBean;

    @GetMapping("/singleton")
    public int getSingleton() {
        return singletonBean.hashCode();
    }

    @GetMapping("/prototype")
    public int getPrototype() {
        PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
        return prototypeBean.hashCode();
    }

    @GetMapping("/request")
    public int getRequest(RequestBean requestBean) {
//        RequestBean requestBean = applicationContext.getBean(RequestBean.class);
        return requestBean.hashCode();
    }

    @GetMapping("/application")
    public int getApplication() {
        ApplicationBean applicationBean = applicationContext.getBean(ApplicationBean.class);
        return applicationBean.hashCode();
    }

    @GetMapping("/session")
    public int getSession() {
        SessionBean sessionBean = applicationContext.getBean(SessionBean.class);
        return sessionBean.hashCode();
    }
}
