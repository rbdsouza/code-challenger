package com.rbs.code_challenger.processor;

import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PaymentProcessor implements Processor {

    private final List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getAllPayments() {
        return payments;
    }

    public List<Payment> getPaymentsByStatus(final PaymentStatus paymentStatus) {
        return payments.stream().filter(payment -> payment.getStatus().equals(paymentStatus)).toList();
    }

    public int getTotalNumberOfPayments() {
        return payments.size();
    }

    public double getTotalSuccessfulAmount() {
        return getPaymentsByStatus(PaymentStatus.SUCCESS)
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    public double getAverageSuccessfulAmount() {
        return getPaymentsByStatus(PaymentStatus.SUCCESS)
                .stream()
                .mapToDouble(Payment::getAmount)
                .average().orElse(0d);
    }

    @Override
    public CompletableFuture<Void> processPaymentsInParallel(List<Payment> payments) {

        //payments.parallelStream().

        return null;
    }

}
