package me.mircea.patterns.design.structural.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void processPayment(BigDecimal amount);
}
