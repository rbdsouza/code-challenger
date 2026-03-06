package com.rbs.code_challenger.processor;

import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorTest {

    private PaymentProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new PaymentProcessor();
    }

    @Test
    void shouldAddPaymentSuccessfully() {
        Payment payment = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);

        processor.addPayment(payment);

        List<Payment> payments = processor.getAllPayments();
        assertEquals(1, payments.size());
        assertTrue(payments.contains(payment));
    }

    @Test
    void shouldThrowExceptionWhenAddNullPayment() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> processor.addPayment(null)
        );

        assertEquals("payment must not be null", exception.getMessage());
    }

    @Test
    void shouldReturnAllPayments() {
        Payment payment1 = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);
        Payment payment2 = new Payment("2", 50.0d, "USD", PaymentStatus.FAILED);

        processor.addPayment(payment1);
        processor.addPayment(payment2);

        List<Payment> payments = processor.getAllPayments();

        assertEquals(2, payments.size());
        assertTrue(payments.contains(payment1));
        assertTrue(payments.contains(payment2));
    }

    @Test
    void shouldReturnCopyWhenGetAllPayments() {
        processor.addPayment(new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS));

        List<Payment> payments = processor.getAllPayments();
        payments.add(new Payment("2", 50.0d, "USD", PaymentStatus.FAILED));

        assertEquals(2, payments.size());
        assertEquals(1, processor.getAllPayments().size());
    }

    @Test
    void shouldReturnPaymentsFilteredByStatus() {
        Payment payment1 = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);
        Payment payment2 = new Payment("2", 50.0d, "USD", PaymentStatus.FAILED);
        Payment payment3 = new Payment("3", 75.0d, "EUR", PaymentStatus.SUCCESS);

        processor.addPayment(payment1);
        processor.addPayment(payment2);
        processor.addPayment(payment3);

        List<Payment> successfulPayments = processor.getPaymentsByStatus(PaymentStatus.SUCCESS);

        assertEquals(2, successfulPayments.size());
        assertTrue(successfulPayments.contains(payment1));
        assertTrue(successfulPayments.contains(payment3));
        assertFalse(successfulPayments.contains(payment2));
    }

    @Test
    void shouldThrowExceptionWhenFilterByNullStatus() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> processor.getPaymentsByStatus(null)
        );

        assertEquals("paymentStatus must not be null", exception.getMessage());
    }

    @Test
    void shouldReturnTotalNumberOfPayments() {
        processor.addPayment(new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS));
        processor.addPayment(new Payment("2", 50.0d, "USD", PaymentStatus.FAILED));
        processor.addPayment(new Payment("3", 75.0d, "EUR", PaymentStatus.PENDING));

        int total = processor.getTotalNumberOfPayments();

        assertEquals(3, total);
    }

    @Test
    void shouldReturnZeroWhenNoPaymentsExist() {
        assertEquals(0, processor.getTotalNumberOfPayments());
        assertEquals(0.0d, processor.getTotalSuccessfulAmount());
        assertEquals(0.0d, processor.getAverageSuccessfulAmount());
        assertTrue(processor.getAllPayments().isEmpty());
    }

    @Test
    void shouldReturnTotalSuccessfulAmount() {
        processor.addPayment(new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS));
        processor.addPayment(new Payment("2", 50.0d, "USD", PaymentStatus.FAILED));
        processor.addPayment(new Payment("3", 75.0d, "EUR", PaymentStatus.SUCCESS));

        double totalSuccessfulAmount = processor.getTotalSuccessfulAmount();

        assertEquals(175.0d, totalSuccessfulAmount);
    }

    @Test
    void shouldReturnZeroTotalSuccessfulAmountWhenNoSuccessfulPaymentsExist() {
        processor.addPayment(new Payment("1", 100.0d, "BRL", PaymentStatus.FAILED));
        processor.addPayment(new Payment("2", 50.0d, "USD", PaymentStatus.PENDING));

        double totalSuccessfulAmount = processor.getTotalSuccessfulAmount();

        assertEquals(0.0d, totalSuccessfulAmount);
    }

    @Test
    void shouldReturnAverageSuccessfulAmount() {
        processor.addPayment(new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS));
        processor.addPayment(new Payment("2", 50.0d, "USD", PaymentStatus.FAILED));
        processor.addPayment(new Payment("3", 200.0d, "EUR", PaymentStatus.SUCCESS));

        double averageSuccessfulAmount = processor.getAverageSuccessfulAmount();

        assertEquals(150.0d, averageSuccessfulAmount);
    }

    @Test
    void shouldReturnZeroAverageSuccessfulAmountWhenNoSuccessfulPaymentsExist() {
        processor.addPayment(new Payment("1", 100.0d, "BRL", PaymentStatus.FAILED));
        processor.addPayment(new Payment("2", 50.0d, "USD", PaymentStatus.PENDING));

        double averageSuccessfulAmount = processor.getAverageSuccessfulAmount();

        assertEquals(0.0d, averageSuccessfulAmount);
    }

    @Test
    void shouldReturnPaymentsSortedByAmountDescending() {
        Payment payment1 = new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS);
        Payment payment2 = new Payment("2", 50.0d, "USD", PaymentStatus.FAILED);
        Payment payment3 = new Payment("3", 75.0d, "EUR", PaymentStatus.PENDING);

        processor.addPayment(payment1);
        processor.addPayment(payment2);
        processor.addPayment(payment3);

        List<Payment> sortedPayments = processor.getPaymentsSortedByAmount();

        assertEquals(3, sortedPayments.size());
        assertEquals(payment1, sortedPayments.get(0));
        assertEquals(payment3, sortedPayments.get(1));
        assertEquals(payment2, sortedPayments.get(2));
    }

    @Test
    void shouldProcessPaymentsInParallel() {
        List<Payment> paymentsToProcess = List.of(
                new Payment("1", 100.0d, "BRL", PaymentStatus.SUCCESS),
                new Payment("2", 50.0d, "USD", PaymentStatus.FAILED),
                new Payment("3", 75.0d, "EUR", PaymentStatus.PENDING)
        );

        processor.processPaymentsInParallel(paymentsToProcess);

        List<Payment> allPayments = processor.getAllPayments();

        assertEquals(3, allPayments.size());
        assertTrue(allPayments.containsAll(paymentsToProcess));
    }

    @Test
    void shouldThrowExceptionWhenProcessNullPaymentListInParallel() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> processor.processPaymentsInParallel(null)
        );

        assertEquals("payments must not be null", exception.getMessage());
    }
}