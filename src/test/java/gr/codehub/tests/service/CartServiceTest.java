package gr.codehub.tests.service;

import gr.codehub.tests.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartServiceImpl cartService;
    private CartServiceImpl cartServiceWithMockPayment;

    @Mock
    private PaymentService mockedPaymentService;

    private PaymentService paymentService;

    @BeforeEach
    void initialize() {
        cartServiceWithMockPayment = new CartServiceImpl(mockedPaymentService);
        cartService = new CartServiceImpl(new PaymentServiceImpl(new BigDecimal("100.00")));
        cartService.addItem(new Item("CPU", BigDecimal.valueOf(300), 1));
        cartService.addItem(new Item("RAM", BigDecimal.valueOf(120), 2));
    }

    @Test
    @DisplayName("Add correct item in cart")
    void shouldAddValidItem() {
        boolean result = cartService.addItem(new Item("SSD", BigDecimal.valueOf(80), 2));
        assertTrue(result);
    }

    @Test
    @DisplayName("Add incorrect item in cart")
    void shouldNotAddInvalidItem() {
        boolean result = cartService.addItem(new Item("SSD", BigDecimal.valueOf(80), 0));
        assertFalse(result);
    }

    @Test
    @DisplayName("Remove correct item from cart")
    void shouldRemoveValidItem() {
        boolean result = cartService.removeItem(new Item("CPU", BigDecimal.valueOf(300), 1));
        assertTrue(result);
    }

    @Test
    @DisplayName("Remove incorrect item from cart")
    void shouldNotRemoveInvalidItem() {
        boolean result = cartService.removeItem(new Item("Banana", BigDecimal.valueOf(300), 1));
        assertFalse(result);
    }

    @Test
    @DisplayName("Get all cart items")
    void shouldRetrieveAllCartItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("CPU", BigDecimal.valueOf(300), 1));
        items.add(new Item("RAM", BigDecimal.valueOf(120), 2));
        assertIterableEquals(items, cartService.getCartItems());
    }

    @Test
    @DisplayName("Calculate total cart price")
    void shouldCalculateTotalCartPrice() {
        assertEquals(BigDecimal.valueOf(540), cartService.getTotalPrice());
    }

    @Test
    @DisplayName("Checkout successfully")
    void shouldCompleteCheckoutSuccessfully() {
//        when(mockedPaymentService.getBalance()).thenReturn(BigDecimal.valueOf(550));
        //cart total price is 540
        boolean isCompleted = cartService.checkout();
        int numOfCartItems = cartService.getCartItems().size();
        assertAll(() -> assertTrue(isCompleted, "Checkout is NOT completed successfully"),
                () -> assertEquals(0, numOfCartItems, "Cart is NOT empty"));

//        verify(mockedPaymentService).withdraw(BigDecimal.valueOf(540));
    }

    @Test
    @DisplayName("Checkout unsuccessful due to insufficient balance")
    void shouldFailCheckoutDueToInsufficientBalance() {
        // when(mockedPaymentService.balance()).thenReturn(BigDecimal.valueOf(110));
        boolean isCompleted = cartService.checkout();
        int numOfCartItems = cartService.getCartItems().size();
        assertAll(() -> assertFalse(isCompleted, "Checkout is completed successfully"),
                () -> assertEquals(2, numOfCartItems, "Cart is empty"));
        verify(mockedPaymentService, never()).withdraw(BigDecimal.valueOf(540));
    }
}
