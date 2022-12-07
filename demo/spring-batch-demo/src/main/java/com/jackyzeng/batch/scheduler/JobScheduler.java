package com.jackyzeng.batch.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Slf4j
@Configuration
@EnableBatchProcessing
public class JobScheduler {

    @Autowired
    private Job sampleJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(fixedRate = 900000)
    public boolean fixRateScheduledJob() {
        boolean successFlag = true;
        long currentTimeMillis = System.currentTimeMillis();

        try {
            log.info("Batch Job Initiated");
            JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
            jobParametersBuilder.addLong("time", System.currentTimeMillis());
            jobParametersBuilder.addString("uuid", UUID.randomUUID().toString());
            JobParameters jobParameters = jobParametersBuilder.toJobParameters();
            JobExecution jobExecution = jobLauncher.run(sampleJob, jobParameters);
            log.info("Verifying status");

            // Check every step execution status
            for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
                if (stepExecution.getExitStatus().equals(ExitStatus.FAILED)) {
                    successFlag = false;
                    break;
                }
            }

            // Check job execution status
            if (!jobExecution.getExitStatus().equals(ExitStatus.FAILED) && successFlag) {
                log.info("Batch Job Scheduler took " + (System.currentTimeMillis() - currentTimeMillis) / 1000 + " seconds");
                return true;
            }
            log.info("Batch Job failed inside steps");

        } catch (Exception e){
            log.error("Batch Job Failed with following message : " + e.getMessage());
        }
        return false;
    }

    @Scheduled(cron = "${batch.job.cron}")
    public boolean cronScheduledJob() {
        log.info("Cron scheduled Batch Job");
        return true;
    }
}
