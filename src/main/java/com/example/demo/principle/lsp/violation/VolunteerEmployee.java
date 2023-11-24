package com.example.demo.principle.lsp.violation;

public class VolunteerEmployee extends Employee{

    protected VolunteerEmployee(int employeeId) {
        super(employeeId);
    }

    @Override
    protected double calculateSalary() {
        throw new RuntimeException("Volunteers don't draw salary");
    }
}
