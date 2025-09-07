package com.clt.spring_batch;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

    @Id
    private Long id;              // unique employee ID
    private String name;          // employee name
    private String designation;   // job role/designation
    private Double basicSalary;   // base monthly salary
    private Double hra;           // house rent allowance
    private Double allowances;    // other allowances
    private Double deductions;    // deductions like PF, insurance, etc.
    private Double grossSalary;   // pay before deductions
    private Double netSalary;     // take-home salary (after deductions)
    private String month;         // salary month, e.g., "August 2025"


}
