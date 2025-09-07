package com.clt.spring_batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) throws Exception {

        // ✅ Validation: Check if required fields are missing
        if (employee.getBasicSalary() == null ||
                employee.getHra() == null ||
                employee.getAllowances() == null ||
                employee.getDeductions() == null) {

            // Log and skip this record
            System.out.println("Skipping record due to missing salary fields: " + employee);
            return null;  // Returning null tells Spring Batch to SKIP this item
        }

        // Calculate Gross Salary
        double grossSalary = employee.getBasicSalary() + employee.getHra() + employee.getAllowances();

        // Calculate Net Salary
        double netSalary = grossSalary - employee.getDeductions();

        // Set values in Employee Entity
        employee.setGrossSalary(grossSalary);
        employee.setNetSalary(netSalary);

        return employee;
    }
}
