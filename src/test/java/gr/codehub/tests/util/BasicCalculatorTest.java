package gr.codehub.tests.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicCalculatorTest {

    private final BasicCalculator calculator = new BasicCalculator();

    @Test
    void executeAdditionWithIntegersProperly() {
        calculator.setResult(0);
        calculator.plus(1);
        assertEquals(1, calculator.getResult(), 0.0001);

    }

    @Test
    void runAdditionWithDoublesWithCorrectAnswer() {
        System.out.println("Continuing");
        int d = 1 + 2;
        assertEquals(d, 3);
    }

}
