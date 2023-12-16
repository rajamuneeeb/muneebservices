package com.muneebcode.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("ap/v2/batchJob")
@AllArgsConstructor
public class JobController {
    private final JobLauncher jobLauncher;
    private final Job csvToDatabaseJob;

    @GetMapping
    public void launchJob() {
        log.info("Job is launching");
        JobParameters jobParameters = new JobParametersBuilder().addParameter("TimeStamp", new JobParameter(System.currentTimeMillis())).toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(csvToDatabaseJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            log.info("Exception Occured Here :");
            throw new RuntimeException(e);
        }

    }
}
