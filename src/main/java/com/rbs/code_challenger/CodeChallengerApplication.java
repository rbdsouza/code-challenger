package com.rbs.code_challenger;


import com.rbs.code_challenger.dto.Payment;
import com.rbs.code_challenger.dto.PaymentStatus;
import com.rbs.code_challenger.processor.PaymentProcessor;

import java.util.Comparator;

public class CodeChallengerApplication {

	public static void main(String[] args) {

		PaymentProcessor processor = getPaymentProcessor();

		System.out.println("listing all payments");
		processor.getAllPayments().forEach(System.out::println);

		System.out.println("Total Number of Payments");
		System.out.println(processor.getTotalNumberOfPayments());

		System.out.println("Total Successful Amount");
		System.out.println(processor.getTotalSuccessfulAmount());

		System.out.println("Total Average Successful Amount");
		System.out.println(processor.getAverageSuccessfulAmount());

		System.out.println("sort payments by amount");
		processor.getAllPayments().stream()
				.sorted(Comparator.comparingDouble(Payment::getAmount).reversed())
				.toList().forEach(System.out::println);

	}

	private static PaymentProcessor getPaymentProcessor() {
		Payment payment1 = new Payment("1", 10d, "BRL", PaymentStatus.SUCCESS);
		Payment payment2 = new Payment("2", 5d, "BRL", PaymentStatus.SUCCESS);
		Payment payment3 = new Payment("3", 7d, "BRL", PaymentStatus.SUCCESS);
		Payment payment4 = new Payment("4", 1.1d, "BRL", PaymentStatus.SUCCESS);
		Payment payment5 = new Payment("5", 0.87d, "BRL", PaymentStatus.SUCCESS);
		Payment payment6 = new Payment("6", 100d, "BRL", PaymentStatus.SUCCESS);
		Payment payment7 = new Payment("7", 50d, "BRL", PaymentStatus.FAILED);
		Payment payment8 = new Payment("8", 75d, "BRL", PaymentStatus.PENDING);

		PaymentProcessor processor = new PaymentProcessor();

		processor.addPayment(payment1);
		processor.addPayment(payment2);
		processor.addPayment(payment3);
		processor.addPayment(payment4);
		processor.addPayment(payment5);
		processor.addPayment(payment6);
		processor.addPayment(payment7);
		processor.addPayment(payment8);
		return processor;
	}

}
