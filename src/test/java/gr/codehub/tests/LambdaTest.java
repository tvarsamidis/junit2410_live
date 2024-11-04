package gr.codehub.tests;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LambdaTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWithLambda() {
        assertThrows(RuntimeException.class, () ->
        {
            if (Math.random() >= 0) {
                // some code here generates some Exception
                throw new RuntimeException("You were caught by a random check");
            }
        });
    }

    @Test
    void shouldCompleteWithinTimeoutUsingLambda() {

        System.out.println("Solving a hard problem....");
        assertTimeout(Duration.ofSeconds(2), () ->
                {
                    System.out.println("doing calculations");
                    Thread.sleep(1990);
                    System.out.println("calculations done!");
                }
        );
    }

    @Test
    void shouldGroupAssertionsUsingLambda() {
        double d1 = Math.random();
        double d2 = Math.random() + d1;
        assertAll("Grouped assertions",
                () -> {
                    assertTrue(d2 < d1, "Condition 1 failed");
                },
                () -> {
                    assertTrue(d2 != d2, "Condition 2 failed");
                }
        );
    }

    @TestFactory
    Collection<DynamicTest> shouldReturnDynamicTests() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test 1", () -> assertEquals(2, 8)),
                DynamicTest.dynamicTest("Test 2", () -> assertTrue("java".contains("j")))
        );
    }
}
