package com.rbs.code_challenger.dto;

/**
 * Represents the possible states of a payment.
 */
public enum PaymentStatus {

    /**
     * Payment was created but has not been completed yet.
     */
    PENDING,

    /**
     * Payment was processed successfully.
     */
    SUCCESS,

    /**
     * Payment processing failed.
     */
    FAILED
}