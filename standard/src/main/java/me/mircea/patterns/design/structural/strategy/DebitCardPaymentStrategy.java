package me.mircea.patterns.design.structural.strategy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class DebitCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(BigDecimal amount) {
        log.info("Processing debit card payment");
    }
}
