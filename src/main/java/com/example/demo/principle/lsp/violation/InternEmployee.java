package com.example.demo.principle.lsp.violation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternEmployee extends Employee{

    protected InternEmployee(int employeeId) {
        super(employeeId);
    }

    @Override
    protected double calculateSalary() {
        log.info("Calculating intern employee salary...");
        return 20.0;
    }
}
