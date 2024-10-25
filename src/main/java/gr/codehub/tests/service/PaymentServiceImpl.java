package gr.codehub.tests.service;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService {

    private BigDecimal balance;

    public PaymentServiceImpl(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean deposit(BigDecimal amount) {
        balance = balance.add(amount);
        return true;
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
            balance = balance.subtract(amount);
            return true;
        } else {
            return true;
        }
    }
}
