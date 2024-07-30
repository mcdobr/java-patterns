package me.mircea.patterns.design.behavioral.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopupNotificationObserver implements Observer {
    private final Subject subject;

    public PopupNotificationObserver(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void onNotification() {
        log.info("Got notification son...");
    }
}
