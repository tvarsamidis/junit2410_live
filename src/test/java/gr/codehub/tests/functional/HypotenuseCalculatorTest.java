package gr.codehub.tests.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HypotenuseCalculatorTest {
    @Test
    void shouldCalculateStandardHypotenuseCorrectly() {
        double a = 3;
        double b = 4;
        double expected = 5;
        double delta = 0.0001;
        double result = alternativeHypotenuseCalculator().calculate(a, b);
        assertEquals(expected, result, delta, "blah blah");
    }

    private HypotenuseCalculator standardHypotenuseCalculator() {
        return (a, b) -> Math.sqrt(a * a + b * b);
    }

    private HypotenuseCalculator alternativeHypotenuseCalculator() {
        return (a, b) -> Math.sqrt((a + b) * (a + b) - 2 * a * b);
    }

}
