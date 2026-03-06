package com.rbs.code_challenger.processor;

import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;

import java.util.List;

/**
 * Defines operations for storing, querying, and processing payments.
 */
public interface Processor {

    /**
     * Adds a new payment to the processor.
     *
     * @param payment payment to add
     * @throws IllegalArgumentException if the payment is invalid or already exists
     * @throws NullPointerException if payment is null
     */
    void addPayment(Payment payment);

    /**
     * Returns all registered payments.
     *
     * @return immutable list containing all payments
     */
    List<Payment> getAllPayments();

    /**
     * Returns all payments filtered by the given status.
     *
     * @param paymentStatus payment status to filter by
     * @return immutable list of payments with the given status
     * @throws NullPointerException if paymentStatus is null
     */
    List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus);

    /**
     * Returns the total number of registered payments.
     *
     * @return total number of payments
     */
    int getTotalNumberOfPayments();

    /**
     * Returns the total amount of all successful payments.
     *
     * @return total amount of successful payments
     */
    double getTotalSuccessfulAmount();

    /**
     * Returns the average amount of all successful payments.
     *
     * @return average amount of successful payments, or 0.0 if none exist
     */
    double getAverageSuccessfulAmount();

    /**
     * Returns all payments sorted by amount in ascending order.
     *
     * @return immutable list of payments sorted by amount
     */
    List<Payment> getPaymentsSortedByAmount();

    /**
     * Processes the provided payments in parallel and adds them to the processor.
     *
     * @param payments payments to process
     * @throws NullPointerException if payments is null
     * @throws IllegalArgumentException if any payment is invalid
     */
    void processPaymentsInParallel(List<Payment> payments);
}