package gr.codehub.tests.junit;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("example")
class RepetitionTest {

    @BeforeEach
    void setUpTestEnvironment() {
        System.out.println("Before Each Test");
        System.out.println("Before each test");
    }

    @AfterEach
    void tearDownTestEnvironment() {
        System.out.println("After each test");
    }

    @org.junit.jupiter.api.RepeatedTest(10)
    void shouldExecuteRandomBooleanTestRepeatedly() {
        // Each repeated test has its own lifecycle
        System.out.println("Executing test");
        assertTrue(ThreadLocalRandom.current().nextBoolean());
    }

    @org.junit.jupiter.api.RepeatedTest(value = 10, name = org.junit.jupiter.api.RepeatedTest.LONG_DISPLAY_NAME)
    void shouldDisplayLongNameForRepeatedTest() {
        assertTrue(ThreadLocalRandom.current().nextBoolean());
    }

    @org.junit.jupiter.api.RepeatedTest(value = 10, name = "{currentRepetition}/{totalRepetitions}")
    void shouldDisplayCustomNameDuringRepetitions() {
        assertTrue(ThreadLocalRandom.current().nextBoolean());
    }

    @org.junit.jupiter.api.RepeatedTest(value = 10, name = "{currentRepetition}/{totalRepetitions}")
    void shouldLogRepetitionInfoDuringExecution(RepetitionInfo repetitionInfo) {
        System.out.println("(CurrentRepetition/TotalRepetitions) " + repetitionInfo.getCurrentRepetition() + "/" +
                           repetitionInfo.getTotalRepetitions());
        assertTrue(ThreadLocalRandom.current().nextBoolean());
    }

    //Honestly, this is a dummy retry annotation. It just retries your tests without checking their results,
    // in order to add some logic and retry the tests when they are failing, you should add rerunner-jupiter dependency
    // in your pom.xml

    // THESE ARE DEPENDED ON io.github.artsok

    @RepeatedIfExceptionsTest(repeats = 10)
    public void shouldSucceedAfterMultipleRetries() {
        // Runs the test until it succeeds
        assertTrue(ThreadLocalRandom.current().nextBoolean());
    }

    @RepeatedIfExceptionsTest(repeats = 10, exceptions = IOException.class, name = "{currentRepetition}/{totalRepetitions}")
    void shouldRetryUntilIOExceptionIsHandled() throws IOException {
        // Repeats until an IOException is not thrown
        // Also, a custom name was added
        if (ThreadLocalRandom.current().nextBoolean()) {
            throw new IOException("Exception in I/O operation");
        }
        System.out.println("Success!");
    }

    @RepeatedIfExceptionsTest(repeats = 10, minSuccess = 4)
    void shouldSucceedAtLeastMinimumTimes() {
        // Runs the test until it succeeds 4 times
        assertTrue(ThreadLocalRandom.current().nextBoolean());
    }

    // For more info on this dependency: https://github.com/artsok/rerunner-jupiter/
}
