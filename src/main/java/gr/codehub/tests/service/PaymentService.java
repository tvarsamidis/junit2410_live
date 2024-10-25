package gr.codehub.tests.service;

import java.math.BigDecimal;

public interface PaymentService {
	BigDecimal getBalance();

	boolean deposit(BigDecimal amount);

	boolean withdraw(BigDecimal amount);
}
