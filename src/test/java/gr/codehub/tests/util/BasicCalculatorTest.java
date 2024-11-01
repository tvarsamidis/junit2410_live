package gr.codehub.tests.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicCalculatorTest {

    private final BasicCalculator calculator = new BasicCalculator();

    private static int testsRun = 0;


    @BeforeAll  // old name is BeforeClass
    static void setupBeforeAllTest2() {
        System.out.println("Ready to start all tests2");
    }

    @AfterAll // old name is AfterClass
    static void cleanUpAfterAllTests() {
        System.out.println("All tests finished");
        System.out.println(testsRun + " tests run");
    }

    private double d = Math.random();

    @BeforeEach
        // older name is Before
    void setupBeforeEachTest() {
        System.out.println("d=" + d);
    }

    @AfterEach
        // After
    void cleanUpAfterEachTest() {
        d = 0;
        testsRun++;
    }


    @Test
    void shouldAddDoubleNumbersCorrectly() {
        System.out.println("Running shouldAddDoubleNumbersCorrectly");
        calculator.setResult(0);
        calculator.plus(1);
        assertEquals(1, calculator.getResult(), 0.0001); // double numbers require accuracy level
    }

    @Test
    void shouldAddIntegerNumbersCorrectly() {
        System.out.println("Continuing");
        int d = 1 + 2;
        assertEquals(d, 3); // integer
    }

    @Test
    void shouldVerifyNumbersAreNotEqual() {
        int answer1 = (int) Math.pow(2, 3) * 0;
        int answer2 = (int) Math.sqrt(64);
        assertNotEquals(answer1, answer2, "Variables answer1 and answer2 must be different");
    }

    @Test
    void showThrowExceptionOnDivideByZero() {
        assertThrows(Exception.class,
                () -> {
                    calculator.divideBy(0);
                },
                "Division by 0 should return a NumberFormatException");
    }

    @Test
    void shouldAssertMultipleConditionsFailFast() {
        int value1 = 10;
        int value2 = 18;
        assertTrue(value1 < value2, "First value must be greater than the second one");
        assertTrue(value1 < value2, "First value must be less than the second one");
        assertTrue(value1 == value2, "First value must be equal to the second one");
    }

    @Test
    void shouldAssertMultipleConditionsIndependenty() {
        int value1 = 10;
        int value2 = 18;
        assertAll("In assertAll",
                () -> {
                    assertTrue(value1 > value2, "First value must be greater than the second one");
                    System.out.println("hello");
                },
                () -> {
                    assertTrue(value1 < value2, "First value must be less than the second one");
                },
                () -> {
                    assertTrue(value1 == value2, "First value must be equal to the second one");
                }
        );
    }
}
