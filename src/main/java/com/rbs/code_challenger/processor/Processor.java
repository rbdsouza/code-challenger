package com.rbs.code_challenger.processor;

import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Processor {

    void addPayment(Payment payment);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus);
    int getTotalNumberOfPayments();
    double getTotalSuccessfulAmount();
    double getAverageSuccessfulAmount();
    CompletableFuture<Void> processPaymentsInParallel(List<Payment> payments);

}
