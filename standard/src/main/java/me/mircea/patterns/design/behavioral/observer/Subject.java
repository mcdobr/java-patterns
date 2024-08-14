package me.mircea.patterns.design.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<PopupNotificationObserver> subscribers;

    public Subject() {
        this.subscribers = new ArrayList<>();
    }

    public Subject subscribe(PopupNotificationObserver observer) {
        subscribers.add(observer);
        return this;
    }

    public void notifyObservers() {
        subscribers.parallelStream()
                .forEach(PopupNotificationObserver::onNotification);
    }
}
