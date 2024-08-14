package me.mircea.patterns.design.structural.strategy;

import java.math.BigDecimal;

/**
 * The context.
 */
public class PaymentProcessor {
    private final PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(BigDecimal amount) {
        paymentStrategy.processPayment(amount);
    }
}
