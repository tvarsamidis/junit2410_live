package gr.codehub.tests.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private final BasicCalculator calculator = new BasicCalculator();

    @Test
    void shouldAddDoubleNumbersCorrectly() {
        calculator.setResult(0);
        calculator.plus(1);
        assertEquals(1, calculator.getResult(), 0.0001);

    }

    @Test
    void shouldAddIntegerNumbersCorrectly() {
        System.out.println("Continuing");
        int d = 1 + 2;
        assertEquals(d, 3);
    }

}
