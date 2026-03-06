package com.rbs.code_challenger.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void shouldCreatePaymentSuccessfully() {
        Payment payment = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);

        assertEquals("1", payment.id());
        assertEquals(100.0d, payment.amount());
        assertEquals("BRL", payment.currency());
        assertEquals(PaymentStatus.SUCCESS, payment.status());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Payment(null, 100.0d, "BRL", PaymentStatus.SUCCESS)
        );

        assertEquals("id must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIdIsBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Payment("   ", 100.0d, "BRL", PaymentStatus.SUCCESS)
        );

        assertEquals("id must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Payment("1", -10.0d, "BRL", PaymentStatus.SUCCESS)
        );

        assertEquals("amount must not be negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Payment("1", 100.0d, null, PaymentStatus.SUCCESS)
        );

        assertEquals("currency must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Payment("1", 100.0d, "   ", PaymentStatus.SUCCESS)
        );

        assertEquals("currency must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStatusIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Payment("1", 100.0d, "BRL", null)
        );

        assertEquals("status must not be null", exception.getMessage());
    }

    @Test
    void shouldConsiderPaymentsEqualWhenIdsAreEqual() {
        Payment payment1 = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);
        Payment payment2 = new Payment("1", 200.0d, "USD", PaymentStatus.FAILED);

        assertEquals(payment1, payment2);
        assertEquals(payment1.hashCode(), payment2.hashCode());
    }

    @Test
    void shouldConsiderPaymentsDifferentWhenIdsAreDifferent() {
        Payment payment1 = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);
        Payment payment2 = new Payment("2", 100.0d, "BRL", PaymentStatus.SUCCESS);

        assertNotEquals(payment1, payment2);
    }
}