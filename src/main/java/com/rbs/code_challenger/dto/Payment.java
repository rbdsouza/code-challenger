package com.rbs.code_challenger.dto;

import java.util.Objects;

/**
 * Represents a payment processed by the system.
 *
 * @param id unique payment identifier
 * @param amount payment amount
 * @param currency payment currency code, such as USD or BRL
 * @param status current payment status
 */
public record Payment(
        String id,
        double amount,
        String currency,
        PaymentStatus status
) {

    /**
     * Creates a validated payment record.
     *
     * @throws NullPointerException if id, currency, or status is null
     * @throws IllegalArgumentException if id or currency is blank, or amount is negative
     */
    public Payment {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(currency, "currency must not be null");
        Objects.requireNonNull(status, "status must not be null");

        if (id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }

        if (currency.isBlank()) {
            throw new IllegalArgumentException("currency must not be blank");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}