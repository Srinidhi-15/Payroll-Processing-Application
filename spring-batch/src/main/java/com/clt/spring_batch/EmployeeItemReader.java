package com.clt.spring_batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StringUtils;

@Configuration
public class EmployeeItemReader {

    // File path from application.properties: batch.file.path=/path/to/folder
    @Value("${batch.file.path}")
    private String filePath;

    @Bean
    public FlatFileItemReader<Employee> reader() {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(filePath + "/employees.csv"));
        reader.setLinesToSkip(1); // skip header
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy() {
            @Override
            public boolean isEndOfRecord(String line) {
                return super.isEndOfRecord(line) && StringUtils.hasText(line.trim());
            }
        });

        // Tokenizer: CSV format + column names
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false); // ignore missing columns
        tokenizer.setNames("id", "name", "designation", "basicSalary",
                "hra", "allowances", "deductions", "grossSalary", "netSalary", "month");

        // FieldSetMapper: map CSV → Employee object
        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        // LineMapper: tokenizer + mapper
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }
}
