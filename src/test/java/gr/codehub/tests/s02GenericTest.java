package gr.codehub.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class s02GenericTest {

    @Test
    void shouldRunWithoutTestingAnyConditions() {
        System.out.println("This is test 1");
    }

    @Test
    void shouldAlwaysFail() {
        // Uncomment to simulate a random failure
        // if (Math.random() >= 0.5)
        fail("This test will fail");
    }

    @Test
    void shouldCompareInputAndOutputValues() {
        int input = 8;
        int output = 4;
        assertEquals(input, output);
    }

    @Test
    void shouldReturnExpectedValueFromGetANumber() {
        assertEquals(Main.getANumber(8), 16);
    }

    @Test
    void shouldCheckIfArraysAreEqual() {
        boolean[] input = {true, true, false};
        boolean[] output = {true, true, false};
        assertArrayEquals(input, output);
    }

    @Test
    void shouldValidateMatchingInputAndOutput() {
        int input = 8;
        int output = 4;
        assertTrue(input == output, "Input and output must be the same");
    }

    @Test
    void shouldAssertAllOperationsCorrectly() {
        assertAll("Greece",
                () -> assertEquals("Sea", "Sea"),
                () -> assertEquals(3, 5)
        );
    }

}
