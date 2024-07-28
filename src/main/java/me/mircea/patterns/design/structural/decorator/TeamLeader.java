package me.mircea.patterns.design.structural.decorator;

public class TeamLeader implements Employee {
    private final Employee employee;

    public TeamLeader(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int computeSalary() {
        return 200 + employee.computeSalary();
    }
}
