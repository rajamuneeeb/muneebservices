package com.muneebcode.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After jobName {}", jobExecution.getJobConfigurationName());
        if (jobExecution.getStatus() == (BatchStatus.COMPLETED))
            log.info("Job completed successfully JobId {} ", jobExecution.getJobId());
        if (jobExecution.getStatus() == (BatchStatus.FAILED)) log.info("Job Failed JobId {}", jobExecution.getJobId());


    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        JobInstance jobInstance = jobExecution.getJobInstance();
        String jobName = jobInstance.getJobName();
        log.info("CsvToDb Job is starting jobName {} ", jobName);
    }
}
