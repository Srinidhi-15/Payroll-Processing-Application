package com.clt.spring_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmployeeJobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job employeeJob;

    //cron expression
    @Scheduled(cron = "0 */1 * * * *")

    public void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
        JobLauncher jobLauncher = this.jobLauncher;
        jobLauncher.run(employeeJob, jobParameters);
        System.out.println("Scheduled Employee Job Started at: " + new Date());
    }

}
