package com.clt.spring_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final EmployeeItemReader reader;
    private final EmployeeItemProcessor processor;
    private final EmployeeItemWriter writer;

    public BatchConfig(EmployeeItemReader reader, EmployeeItemProcessor processor, EmployeeItemWriter writer) {
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    // ✅ Step definition
    @Bean
    public Step employeeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("employeeStep", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager) // 10 records per transaction
                .reader(reader.reader())
                .processor(processor)
                .writer(writer)
                .build();
    }

    // ✅ Job definition
    @Bean
    public Job employeeJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("employeeJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(employeeStep(jobRepository, transactionManager))
                .build();
    }
}
