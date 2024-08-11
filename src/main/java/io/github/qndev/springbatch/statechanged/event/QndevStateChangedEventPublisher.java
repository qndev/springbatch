package io.github.qndev.springbatch.statechanged.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class QndevStateChangedEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public QndevStateChangedEventPublisher(ApplicationEventPublisher publisher) {
        this.applicationEventPublisher = publisher;
    }

    public void publishEvent(QndevStateChangedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
