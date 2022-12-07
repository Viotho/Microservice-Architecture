package com.jackyzeng.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Could also use @BeforeJob/@AfterJob annotations.
 * Using JobExecutionListener interface OR JobExecutionListenerSupport class.
 */

@Slf4j
@Component
public class SampleJobListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("----------Before Job----------");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("----------After Job----------");
    }
}
