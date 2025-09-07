package com.clt.spring_batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemWriter implements ItemWriter<Employee> {
    private final EmployeeRepository repository;
    public EmployeeItemWriter(EmployeeRepository repository, EmployeeRepository repository1) {
        this.repository = repository;

    }

    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }
}
