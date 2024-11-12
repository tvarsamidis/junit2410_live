package gr.codehub.tests.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class s03CalculatorTest {

//    @Before annotation is now @BeforeEach
//    @After annotation is now @AfterEach
//    @BeforeClass annotation is now @BeforeAll
//    @AfterClass annotation is now @AfterAll
//    @Ignore annotation is now @Disabled

    private final BasicCalculator calculator = new BasicCalculator();

    @BeforeAll
    static void setupBeforeAllTests() { // must be static
        System.out.println("Ready to start all tests");
    }

    @AfterAll
    static void cleanupAfterAllTests() { // must be static
        System.out.println("All tests finished");
    }

    @BeforeEach
    void setupBeforeEachTest() {
        System.out.println("==================");
        System.out.println("Starting next test:");
    }

    @AfterEach
    void cleanupAfterEachTest() {
        System.out.println("Final calculator value=" + calculator.getResult());
    }

    @Test
    void shouldAddNumbersCorrectly() {
        calculator.setResult(0);
        calculator.plus(3);
        calculator.plus(8);
        assertEquals(11, calculator.getResult());
    }

    @Test
    void shouldVerifyNumbersAreNotEqual() {
        int answer1 = (int) Math.pow(2, 3) * 0;
        int answer2 = (int) Math.sqrt(64);
        assertNotEquals(answer1, answer2, "Variables answer1 and answer2 must be different");
    }

    @Test
    void shouldDivideNumbersCorrectly() {
        calculator.setResult(0);
        calculator.plus(5);
        calculator.divideBy(2);
        assertEquals(2.5, calculator.getResult(), "Was expecting 2.5");
    }

    @Test
    void shouldMaintainDecimalAccuracy() {
        double number = 5;
        double root = Math.sqrt(number);
        calculator.setResult(0);
        calculator.plus(root);
        calculator.times(root);
        calculator.minus(number);
        assertEquals(0, calculator.getResult(), "Result should be close to zero");
    }

    @Test
    void shouldThrowExceptionOnDivideByZero() {
        assertThrows(Exception.class, () -> {
            calculator.divideBy(0);
        }, "Division by 0 should return an exception");
    }

    @Test
    void shouldCompleteCalculationsWithinTimeLimit() {
        assertTimeout(Duration.of(4, ChronoUnit.MILLIS), () -> {
            calculator.setResult(1_000_000_000);
            for (int i = 1; i <= 10000000; i++) {
                calculator.times(i);
                calculator.divideBy(i);
            }
        }, "Calculations take too much time");
    }

    // This test will fail on the first false test and will not continue with the rest
    @Test
    void shouldAssertMultipleConditionsFailFast() {
        int value1 = 10;
        int value2 = 8;
        assertTrue(value1 > value2, "Single: first value must be larger");
        assertTrue(value1 < value2, "Single: second value must be larger");
        assertTrue(value1 == value2, "Single: values must be equal");
    }

    @Test
    void shouldAssertMultipleConditionsIndependently() {
        int value1 = 10;
        int value2 = 8;
        assertAll("In assertAll",
                () -> assertTrue(value1 > value2, "All: first value must be larger"),
                () -> assertTrue(value1 < value2, "All: second value must be larger"),
                () -> assertTrue(value1 == value2, "All: values must be equal")
        );
    }
}
