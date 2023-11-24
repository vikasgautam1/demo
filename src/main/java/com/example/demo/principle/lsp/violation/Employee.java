package com.example.demo.principle.lsp.violation;

public abstract class Employee {

    protected final int employeeId;

    protected Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    protected abstract double calculateSalary();
}
