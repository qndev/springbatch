package io.github.qndev.springbatch.statechanged.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class QndevJobExecutionListener implements JobExecutionListener {

    private final Log log = LogFactory.getLog(this.getClass());

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.error("+++++++++++ [QNDEV JOB EXECUTION] before job execution +++++++++++");
        log.error("+++++++++++ [QNDEV JOB EXECUTION] job name and status: " + jobExecution.toString() + " +++++++++++");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.error("+++++++++++ [QNDEV JOB EXECUTION] after job execution +++++++++++");
        log.error("+++++++++++ [QNDEV JOB EXECUTION] job name and status: " + jobExecution.toString() + " +++++++++++");
    }

}
