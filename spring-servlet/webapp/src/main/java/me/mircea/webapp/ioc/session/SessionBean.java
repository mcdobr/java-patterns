package me.mircea.webapp.ioc.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Examples: A UserPreferences object.
 */
@Component
@SessionScope
@Slf4j
public class SessionBean {
    public SessionBean() {
        log.info("Created new session bean...");
    }
}
