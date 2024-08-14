package me.mircea.webapp.ioc.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
@Slf4j
public class ApplicationBean {
    public ApplicationBean() {
        log.info("Created new application-scoped bean...");
    }
}
