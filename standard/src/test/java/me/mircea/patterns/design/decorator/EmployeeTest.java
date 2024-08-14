package me.mircea.patterns.design.decorator;

import me.mircea.patterns.design.structural.decorator.Employee;
import me.mircea.patterns.design.structural.decorator.Interviewer;
import me.mircea.patterns.design.structural.decorator.RegularEmployee;
import me.mircea.patterns.design.structural.decorator.TeamLeader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {
    @Test
    void shouldComputeSalary() {
        Employee employee = new RegularEmployee();
        Employee burdenedWithResponsibility = new Interviewer(employee);
        Employee promoted = new TeamLeader(burdenedWithResponsibility);

        assertThat(promoted.computeSalary()).isGreaterThan(employee.computeSalary());
    }
}