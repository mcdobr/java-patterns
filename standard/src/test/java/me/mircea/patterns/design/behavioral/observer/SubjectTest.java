package me.mircea.patterns.design.behavioral.observer;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;

class SubjectTest {
    @Test
    void shouldNotify() {
        Subject subject = new Subject();
        PopupNotificationObserver observer = mock(PopupNotificationObserver.class);
        subject.subscribe(observer);

        // when
        subject.notifyObservers();

        // then
        then(observer).should().onNotification();
    }
}