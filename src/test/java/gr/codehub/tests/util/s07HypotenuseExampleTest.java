package gr.codehub.tests.util;

import gr.codehub.tests.functional.HypotenuseCalculator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class s07HypotenuseExampleTest {

    public static void assertCustomHypotenuse(HypotenuseCalculator calculator) {
        double a = 3.0;
        double b = 4.0;
        double expected = 5.0;
        double delta = 0.0001; // Acceptable error margin
        double result = calculator.calculate(a, b);
        if (Math.abs(expected - result) > delta) {
            throw new AssertionError("Custom hypotenuse calculator error: expected " + expected + " but got " + result);
        }
    }

    @Test
    public void shouldCalculateStandardHypotenuseCorrectly() {
        double a = 3.0;
        double b = 4.0;
        double expected = 5.0;
        double delta = 0.0001; // Acceptable error margin
        double result = HypotenuseExample.standardHypotenuse().calculate(a, b);
        assertEquals(expected, result, delta, "Standard hypotenuse calculator error: expected " + expected + " but got " + result);
    }

    @Test
    public void shouldCalculateHypotenuseUsingCustomAssertion() {
        assertCustomHypotenuse((a, b) -> Math.sqrt((a + b) * (a + b) - 2 * a * b));
    }


    // Example with assertAll for Functional Interfaces
    // If you have multiple conditions to validate in a functional interface, assertAll can be used effectively
    // with lambda expressions to group assertions in one test method.

    @Test
    void shouldValidateMultipleConditionsForString() {
        Predicate<String> isEmpty = String::isEmpty; // Predicate
        assertAll("Lambda conditions",
                () -> assertTrue(isEmpty.test("")),
                () -> assertFalse(isEmpty.test("hello"))
        );
    }

    @Test
    void shouldCheckForEvenNumbers() {
        int number = new Random(10).nextInt();

        // Test even
        assertTrue(() -> number % 2 == 0, number + " is not an even number.");

        // Test divisibility by 2
        BiFunction<Integer, Integer, Boolean> divisible = (x, y) -> x % y == 0;
        Function<Integer, Boolean> multipleOf2 = (x) -> divisible.apply(x, 2);
        assertTrue(() -> multipleOf2.apply(number), () -> " 2 is not factor of " + number);

        // Test if contains an even number or not
        List<Integer> numbers = Arrays.asList(1, 1, 1, 1, 2);
        assertTrue(() -> numbers.stream().distinct().anyMatch(this::isEven), "Did not find an even number in the list");
    }

    boolean isEven(int number) {
        return number % 2 == 0;
    }
}
