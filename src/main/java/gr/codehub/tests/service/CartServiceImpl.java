package gr.codehub.tests.service;

import gr.codehub.tests.domain.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

	private final List<Item> cart;
	//this will be used as mock
	//there is no implementation
	private final PaymentService paymentService;

	public CartServiceImpl(PaymentService paymentService) {
		cart = new ArrayList<>();
		this.paymentService = paymentService;
	}

	@Override
	public boolean addItem(Item item) {
		return (item.getQuantity() > 0) && addAndIncrementQuantityIfExists(item);
	}

	private boolean addAndIncrementQuantityIfExists(Item item) {
		for (Item cartItem : cart) {
			if (cartItem.equals(item)) {
				cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
				return true;
			}
		}
		return cart.add(item);
	}

	@Override
	public boolean removeItem(Item item) {
		return cart.remove(item);
	}

	@Override
	public List<Item> getCartItems() {
		return cart;
	}

	@Override
	public BigDecimal getTotalPrice() {
		return cart.stream().map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(
				BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public boolean checkout() {
		if (getTotalPrice().toBigInteger().doubleValue() <= paymentService.getBalance().toBigInteger().doubleValue()) {
			paymentService.withdraw(getTotalPrice());
			cart.clear();
			return true;
		} else {
			return false;
		}
	}
}
