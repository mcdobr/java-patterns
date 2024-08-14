package me.mircea.webapp.ioc.prototype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = "prototype")
public class PrototypeBean {
}
