package com.example.demo.principle.lsp.violation;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SalaryDisburser {

    public void disburseSalaries(List<Employee> employees){
        for(Employee employee: employees){
            if(employee instanceof VolunteerEmployee){
                continue;
            }
            Double salary = employee.calculateSalary();
            log.info("Salary: {} is disbursed for employee with id: {}", salary, employee.employeeId);
        }
    }
}
