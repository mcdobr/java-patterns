package me.mircea.patterns.design.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void shouldComputeSalary() {
        Employee employee = new RegularEmployee();
        Employee burdenedWithResponsibility = new Interviewer(employee);
        Employee promoted = new TeamLeader(burdenedWithResponsibility);

        assertThat(promoted.computeSalary()).isGreaterThan(employee.computeSalary());
    }
}