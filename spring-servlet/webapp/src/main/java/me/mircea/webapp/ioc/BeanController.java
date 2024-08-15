package me.mircea.webapp.ioc;

import lombok.RequiredArgsConstructor;
import me.mircea.webapp.ioc.application.ApplicationBean;
import me.mircea.webapp.ioc.prototype.PrototypeBean;
import me.mircea.webapp.ioc.request.RequestBean;
import me.mircea.webapp.ioc.session.SessionBean;
import me.mircea.webapp.ioc.singleton.SingletonBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * There are multiple ways to inject a shorter-lived bean into a longer-lived bean.
 * This is an example of a singleton controller.
 * <p>
 * Ways you can inject:
 * - use the ApplicationContext and get a bean of a specific class
 * - inject an ObjectFactory of that type from Spring
 * - inject an ObjectProvider of that type from Spring (specialization of ObjectFactory)
 * - Add JSR 330 dependency and use Provider interface
 */
@RestController
@RequestMapping("/api/beans")
@RequiredArgsConstructor
public class BeanController {
    private final ApplicationContext applicationContext;

    private final SingletonBean singletonBean;

    private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

    @GetMapping("/singleton")
    public int getSingleton() {
        return singletonBean.hashCode();
    }

    @GetMapping("/prototype")
    public int getPrototype() {
        PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
        return prototypeBean.hashCode();
    }

    @GetMapping("/request")
    public int getRequest(RequestBean requestBean) {
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
