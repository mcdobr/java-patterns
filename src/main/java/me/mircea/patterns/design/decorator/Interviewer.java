package me.mircea.patterns.design.decorator;

public class Interviewer implements Employee {
    private final Employee employee;

    public Interviewer(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int computeSalary() {
        return 100 + employee.computeSalary();
    }
}
