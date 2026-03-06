package com.rbs.code_challenger;

import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;
import com.rbs.code_challenger.processor.PaymentProcessor;

import java.util.List;

/**
 * Entry point for the code challenge application.
 *
 * <p>This class demonstrates how to:
 * <ul>
 *     <li>Create payments</li>
 *     <li>Add payments to the processor</li>
 *     <li>List payments</li>
 *     <li>Filter and calculate payment statistics</li>
 *     <li>Sort payments by amount</li>
 *     <li>Simulate parallel payment processing</li>
 * </ul>
 * </p>
 */
public class CodeChallengerApplication {

	public static void main(String[] args) {
		final Payment payment1 = new Payment("1", 10.0d, "BRL", PaymentStatus.SUCCESS);
		final Payment payment2 = new Payment("2", 5.0d, "BRL", PaymentStatus.SUCCESS);
		final Payment payment3 = new Payment("3", 7.0d, "BRL", PaymentStatus.SUCCESS);
		final Payment payment4 = new Payment("4", 1.1d, "BRL", PaymentStatus.SUCCESS);
		final Payment payment5 = new Payment("5", 0.87d, "BRL", PaymentStatus.SUCCESS);
		final Payment payment6 = new Payment("6", 100.0d, "BRL", PaymentStatus.SUCCESS);
		final Payment payment7 = new Payment("7", 50.0d, "BRL", PaymentStatus.FAILED);
		final Payment payment8 = new Payment("8", 75.0d, "BRL", PaymentStatus.PENDING);

		final PaymentProcessor processor = new PaymentProcessor();

		final List<Payment> initialPayments = List.of(
				payment1,
				payment2,
				payment3,
				payment4,
				payment5,
				payment6,
				payment7,
				payment8
		);

		initialPayments.forEach(processor::addPayment);

		System.out.println("=== LISTING ALL PAYMENTS ===");
		processor.getAllPayments().forEach(System.out::println);

		System.out.println("\n=== TOTAL NUMBER OF PAYMENTS ===");
		System.out.println(processor.getTotalNumberOfPayments());

		System.out.println("\n=== TOTAL SUCCESSFUL AMOUNT ===");
		System.out.println(processor.getTotalSuccessfulAmount());

		System.out.println("\n=== AVERAGE SUCCESSFUL AMOUNT ===");
		System.out.println(processor.getAverageSuccessfulAmount());

		System.out.println("\n=== SUCCESSFUL PAYMENTS ===");
		processor.getPaymentsByStatus(PaymentStatus.SUCCESS).forEach(System.out::println);

		System.out.println("\n=== PAYMENTS SORTED BY AMOUNT (ASC) ===");
		processor.getPaymentsSortedByAmount().forEach(System.out::println);

		System.out.println("\n=== PARALLEL PAYMENT PROCESSING ===");
		final List<Payment> paymentsToProcessInParallel = List.of(
				new Payment("9", 120.0d, "BRL", PaymentStatus.SUCCESS),
				new Payment("10", 15.5d, "USD", PaymentStatus.FAILED),
				new Payment("11", 44.9d, "EUR", PaymentStatus.PENDING)
		);

		processor.processPaymentsInParallel(paymentsToProcessInParallel);

		System.out.println("\n=== ALL PAYMENTS AFTER PARALLEL PROCESSING ===");
		processor.getAllPayments().forEach(System.out::println);

		System.out.println("\n=== UPDATED TOTAL NUMBER OF PAYMENTS ===");
		System.out.println(processor.getTotalNumberOfPayments());
	}
}