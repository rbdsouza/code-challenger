package com.rbs.code_challenger.processor;

import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Processes payments in memory.
 */
public class PaymentProcessor implements Processor {

    private static final long PROCESSING_DELAY_MS = 300L;

    private final List<Payment> payments = new ArrayList<>();

    /**
     * Adds a new payment.
     *
     * @param payment payment to add
     * @throws NullPointerException if payment is null
     */
    @Override
    public void addPayment(final Payment payment) {
        Objects.requireNonNull(payment, "payment must not be null");
        payments.add(payment);
    }

    /**
     * Returns all payments.
     *
     * @return list of all payments
     */
    @Override
    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    /**
     * Returns payments filtered by status.
     *
     * @param paymentStatus payment status
     * @return list of payments with the given status
     * @throws NullPointerException if paymentStatus is null
     */
    @Override
    public List<Payment> getPaymentsByStatus(final PaymentStatus paymentStatus) {
        Objects.requireNonNull(paymentStatus, "paymentStatus must not be null");

        return payments.stream()
                .filter(payment -> payment.status() == paymentStatus)
                .toList();
    }

    /**
     * Returns the total number of payments.
     *
     * @return total number of payments
     */
    @Override
    public int getTotalNumberOfPayments() {
        return payments.size();
    }

    /**
     * Returns the total amount of successful payments.
     *
     * @return total successful amount
     */
    @Override
    public double getTotalSuccessfulAmount() {
        return payments.stream()
                .filter(payment -> payment.status() == PaymentStatus.SUCCESS)
                .mapToDouble(Payment::amount)
                .sum();
    }

    /**
     * Returns the average amount of successful payments.
     *
     * @return average successful amount, or 0.0 if none exist
     */
    @Override
    public double getAverageSuccessfulAmount() {
        return payments.stream()
                .filter(payment -> payment.status() == PaymentStatus.SUCCESS)
                .mapToDouble(Payment::amount)
                .average()
                .orElse(0.0d);
    }

    /**
     * Returns payments sorted by amount in descending order.
     *
     * @return payments sorted by amount descending
     */
    @Override
    public List<Payment> getPaymentsSortedByAmount() {
        return payments.stream()
                .sorted(Comparator.comparingDouble(Payment::amount).reversed())
                .toList();
    }

    /**
     * Processes payments in parallel and adds them to the list.
     *
     * @param payments payments to process
     * @throws NullPointerException if payments is null
     */
    @Override
    public void processPaymentsInParallel(final List<Payment> payments) {
        Objects.requireNonNull(payments, "payments must not be null");

        payments.parallelStream().forEach(this::processPayment);
    }

    /**
     * Processes a single payment.
     *
     * @param payment payment to process
     */
    private void processPayment(final Payment payment) {
        Objects.requireNonNull(payment, "payment must not be null");

        System.out.printf(
                "Processing payment %s in thread: %s%n",
                payment.id(),
                Thread.currentThread().getName()
        );

        simulateProcessingDelay();
        addPayment(payment);
    }

    /**
     * Simulates processing delay.
     */
    private void simulateProcessingDelay() {
        try {
            Thread.sleep(PROCESSING_DELAY_MS);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("payment processing interrupted", ex);
        }
    }
}