package io.github.qndev.springbatch.statechanged.event;

import io.github.qndev.springbatch.entity.Qndev;
import org.springframework.context.ApplicationEvent;

public class QndevStateChangedEvent extends ApplicationEvent {

    public QndevStateChangedEvent(Qndev qndev) {
        super(qndev);
    }

    public Qndev getQndev() {
        return (Qndev) getSource();
    }

    public Boolean isAsync() {
        return true;
    }

}
