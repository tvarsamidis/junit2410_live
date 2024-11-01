package gr.codehub.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AssertionsDemoTest {

    @Test
    void shouldSometimesFail() {
        if (Math.random() >= 0.5) {
            fail("This test will always fail");
        }
    }

    @Test
    void shouldAlwaysPass() {
        System.out.println("Nothing to see here :)");
    }

    @Test
    void shouldAssertArrayEquality() {
        boolean[] actual = {true, true, true, false};
        boolean[] expected = {true, true, true, true};
        assertArrayEquals(expected, actual, "All " + expected.length + " elements should be identical");
    }

    @Test
    void shouldVerifyStringIsNull() {
        String s = null;
        assertNull(s);
        assertNull(s, "s should be null");
        assertNull(s, () -> "s should be null");
    }

    @Test
    @DisplayName("Assert database rows are at least 100")
    void shouldHaveAtLeast100RowsInDatabase() {
        int rows = 105;
        assertTrue(rows >= 100);
    }

    @Test
    @DisplayName("Assert new database rows are at least 100")
    @Disabled("Connect the new database first, and then check")
    void shouldHaveAtLeast100RowsInNewDatabase() {
        int rows = 105;
        assertTrue(rows >= 100);
        fail();
    }

}
