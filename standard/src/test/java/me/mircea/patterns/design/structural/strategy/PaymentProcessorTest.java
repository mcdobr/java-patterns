package me.mircea.patterns.design.structural.strategy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.then;

class PaymentProcessorTest {

    @ParameterizedTest
    @MethodSource("paymentStrategies")
    void shouldProcessPayment(PaymentStrategy paymentStrategy) {
        // given
        PaymentStrategy spiedStrategy = Mockito.spy(paymentStrategy);
        PaymentProcessor paymentProcessor = new PaymentProcessor(spiedStrategy);

        // when
        paymentProcessor.processPayment(BigDecimal.TEN);

        // then
        then(spiedStrategy).should().processPayment(ArgumentMatchers.any(BigDecimal.class));
    }

    static List<PaymentStrategy> paymentStrategies() {
        return List.of(new CreditCardPaymentStrategy(), new DebitCardPaymentStrategy());
    }
}