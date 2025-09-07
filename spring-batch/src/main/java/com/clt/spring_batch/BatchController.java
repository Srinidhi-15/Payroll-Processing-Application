package com.clt.spring_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/batch")
public class BatchController {
    private final JobLauncher jobLauncher;
    private final Job employeeJob;

    public BatchController(JobLauncher jobLauncher, Job employeeJob) {
        this.jobLauncher = jobLauncher;
        this.employeeJob = employeeJob;
    }
@PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        //save upload file to configure path

    String uploadDir =  System.getProperty("user.dir") + "/uploads"; //store in project folder

    File dir = new File(uploadDir);
    if(!dir.exists()) {
        dir.mkdirs();
    }

    File destination = new File(
            uploadDir+"/"+file.getOriginalFilename());
    file.transferTo(destination);

    //Run Job
    JobParameters jobParameters = new JobParametersBuilder().addLong("startAt",System.currentTimeMillis()).toJobParameters();
    jobLauncher.run(employeeJob,jobParameters);

    return "Batch job started successfully";
}
}
