package io.github.qndev.springbatch.statechanged.event;

import io.github.qndev.springbatch.entity.Qndev;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class QndevStateChangedEventListener {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job qndevJob;

    @PostPersist
    @PostUpdate
    @PostRemove
    public void afterQndevStateChanged(Qndev qndev) {
        QndevStateChangedEvent event = new QndevStateChangedEvent(qndev);
        SpringApplicationContext.getBean(QndevStateChangedEventPublisher.class).publishEvent(event);
    }

    @Async
    @EventListener(classes = {QndevStateChangedEvent.class}, condition = "#event.isAsync()")
    public void onQndevStateChangedEvent(QndevStateChangedEvent event) {
        doIt(event);
    }

    private void doIt(QndevStateChangedEvent event) {
        log.error("+++++++++++ [QNDEV STATE CHANGED] add/update/delete complete for qndev entity: " + event.getQndev().getId() + " +++++++++++");
        try {
            this.jobLauncher.run(this.qndevJob, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

}
