package gr.codehub.tests;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class s06MainLambdaTest {
//    In JUnit 5, you can pass lambdas to assertions such as assertThrows, assertAll, and assertTimeout for better
//    control and readability:
//    assertThrows: If you want to test that a lambda expression throws an exception, use assertThrows with a lambda.


    @Test
    void shouldThrowIllegalArgumentExceptionWithLambda() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            if (Math.random() >= 0)
                throw new RuntimeException("You were caught by a random check");
        });
    }

    //    assertTimeout: You can pass a lambda to assertTimeout to verify that a block of code completes within a specified
//    time.
    @Test
    void shouldCompleteWithinTimeoutUsingLambda() {
        System.out.println("Solving the problem");
        assertTimeout(Duration.ofSeconds(2), () ->
        {
            for (int i = 0; i < 10; i++) {
                System.out.println("Calculating!");
                for (int j = 0; j < 1_000_000_000; j++) {
                    // important stuff here
                }
            }
        });
        System.out.println("The problem is solved!");
    }
    //  assertAll: This allows you to group multiple assertions, which can be beneficial with lambdas for testing
    //  multiple conditions in a single test.

    @Test
    void shouldGroupAssertionsUsingLambda() {
        double d1 = Math.random();
        double d2 = Math.random() + d1;
        assertAll("Grouped assertions",
                () -> assertTrue(d2 > d1, "Condition 1 failed"),
                () -> assertEquals(d1 < d2, true, "Value mismatch")
        );
    }

    //2. Parameterized Tests with Lambdas
    // JUnit 5 allows you to use @ParameterizedTest
    // with lambdas to supply multiple test cases dynamically.

    @ParameterizedTest
    @ValueSource(strings = {"test", "data", "string"})
    void shouldValidateStringLengthIsGreaterThanZero(String input) {
        assertTrue(input.length() > 0, () -> "String length should be > 0 for " + input);
    }

    // 3. Custom Assertions with Lambdas
    // For more complex assertions, you can encapsulate custom conditions within a lambda:

    @Test
    void shouldAssertCustomConditionWithLambda(Predicate<Object> condition, Object obj) {
        assertTrue(condition.test(obj), () -> "Custom condition failed for " + obj);
    }

    // 4. Using Dynamic Tests with Lambdas
    // JUnit 5 introduces @TestFactory for dynamic tests, where you can create tests dynamically in a collection,
    // leveraging lambdas for more flexible test definitions.

    // The dynamicTests() method ***RETURNS*** a collection of DynamicTest objects because JUnit 5’s @TestFactory
    // requires test factories to return a set of dynamically generated tests. Unlike regular @Test methods, which
    // represent static tests that are fully defined at compile-time, dynamic tests are generated and defined at
    // runtime. By returning a collection, the @TestFactory method provides JUnit with the individual tests to execute,
    // each of which can have its own name, logic, and assertion.
    @TestFactory
    Collection<DynamicTest> shouldReturnDynamicTests() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test 1", () -> assertEquals(2, 1 + 1)),
                DynamicTest.dynamicTest("Test 2", () -> assertTrue("java".contains("j")))
        );
    }


}
