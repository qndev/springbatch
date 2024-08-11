package io.github.qndev.springbatch.controller;

import io.github.qndev.springbatch.entity.Qndev;
import io.github.qndev.springbatch.repository.QndevRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QndevController {

    private final QndevRepository qndevRepository;

    private final JobLauncher jobLauncher;

    private final Job qndevJob;

    public QndevController(QndevRepository qndevRepository, JobLauncher jobLauncher, Job qndevJob) {
        this.qndevRepository = qndevRepository;
        this.jobLauncher = jobLauncher;
        this.qndevJob = qndevJob;
    }

    @PostMapping("/qndev")
    public Qndev qndev() {
        Qndev qndev = new Qndev();
        qndev.setName("Qndev");
        qndev.setDescription("Spring Batch");
        qndevRepository.save(qndev);
        return qndev;
    }

    @PostMapping("/qndev-writer")
    public void qndevWriter() {
        try {
            this.jobLauncher.run(qndevJob, new JobParameters());
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
