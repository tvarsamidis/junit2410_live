package gr.codehub.tests.other;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class s10TimeoutTest {

    @Test
    void shouldFailWhenExecutionExceedsTimeout() {
        // Test does not finish, as it waits for the method to return first before declaring failure
        assertTimeout(Duration.of(1, ChronoUnit.SECONDS), () -> Thread.sleep(2000));
    }

    @Test
    void shouldReturnCorrectValueWithinTimeout() {
        // Test that asserts if a method does not time out and also checks if what the method returns is correct
        String message = assertTimeout(Duration.of(1, ChronoUnit.SECONDS), () -> "Hello, World!");
        assertEquals("Hello, World!", message);
    }

    // Unlike assertTimeout, which waits for the code to finish even if it exceeds the time limit,
    // assertTimeoutPreemptively interrupts the code as soon as the timeout is reached

    @Test
    void shouldFailWhenTimeoutOccursPreemptively() {
        // Calls the test on a different thread, which allows the assertion to finish before the actual execution of
        // the code
        assertTimeoutPreemptively(Duration.of(1, ChronoUnit.SECONDS), () -> Thread.sleep(2000));
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void shouldFailIfWholeTestExceedsTimeout() throws InterruptedException {
        // Test does not finish in time, and its execution is stopped by JUnit as it times out
        Thread.sleep(2000);
        assertTrue(true);
    }
}
