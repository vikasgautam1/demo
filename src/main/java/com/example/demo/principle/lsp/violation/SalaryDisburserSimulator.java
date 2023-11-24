package com.example.demo.principle.lsp.violation;

import java.util.ArrayList;
import java.util.List;

public class SalaryDisburserSimulator {

    public void simulate(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new FullTimeEmployee(1));
        employees.add(new ContractualEmployee(2));
        employees.add(new VolunteerEmployee(3));
        employees.add(new InternEmployee(4));
        SalaryDisburser salaryDisburser = new SalaryDisburser();
        salaryDisburser.disburseSalaries(employees);
    }
}
