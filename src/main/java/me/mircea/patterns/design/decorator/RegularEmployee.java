package me.mircea.patterns.design.decorator;

public class RegularEmployee implements Employee {
    @Override
    public int computeSalary() {
        return 1000;
    }
}
