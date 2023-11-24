package com.example.demo.principle.lsp.violation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FullTimeEmployee extends Employee{

    protected FullTimeEmployee(int employeeId) {
        super(employeeId);
    }

    @Override
    protected double calculateSalary() {
        log.info("Calculating fullTime employee salary...");
        return 50.0;
    }
}
