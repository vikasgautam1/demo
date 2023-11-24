package com.example.demo.principle.lsp.violation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContractualEmployee extends Employee{

    protected ContractualEmployee(int employeeId) {
        super(employeeId);
    }

    @Override
    protected double calculateSalary() {
        log.info("Calculating contractual employee salary...");
        return 30.0;
    }
}
