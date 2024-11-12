package gr.codehub.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Random;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class s04AssertionDemoTest {

    @Test
    void shouldAlwaysFail() {
        // The test will fail no matter what
        fail("This test will fail");
    }

    @Test
    void shouldCompareEqualNumbers() {
        int expected = 1;
        int actual = 1;
        // Assert equality
        assertEquals(expected, actual);
    }

    @Test
    void shouldFailWhenNumbersAreNotEqual() {
        int expected = 1;
        int actual = 2;
        // Assert equality with error message
        assertEquals(expected, actual, "Fail message!");
    }

    @Test
    void shouldAssertArrayEquality() {
        boolean[] expected = {true, false, true};
        boolean[] actual = {true, true, false};
        // Assert that the arrays are equal
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldVerifyStringLength() {
        String str1 = "Hello";
        // Assert that the test is true
        assertTrue(str1.length() > 4, str1 + " must contain more than 4 characters");
    }

    @Test
    void shouldAssertAllConditions() {
        // Assert all, it outputs each fail
        assertAll("Hulk powers", () -> assertEquals("Green", "Green"), () -> assertEquals("Hummer", "Bow"));
    }

    @Test
    void shouldThrowArithmeticExceptionOnDivisionByZero() {
        // Assert that it throws an exception
        assertThrows(ArithmeticException.class, () -> {
            int division = 1 / 0;
        });
    }

    @Test
    void shouldVerifyStringIsNull() {
        String str = null;
        assertNull(str);
        assertNull(str, "str should be null");
        assertNull(str, () -> "str should be null");
    }


    @Test
    void shouldCompleteWithinTimeout() {
        // Assert that a functionality will not exceed the specified amount of time
        assertTimeout(ofMillis(1), () -> {
            // Some time-consuming tasks
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(100);
            int[] myArray = new int[randomInt];
            for (int i = 0; i < randomInt; i++) {
                myArray[i] = randomGenerator.nextInt(100);
            }
            String myString = "Hello World";
            for (int i = 0; i < 100; i++) {
                myString += " Hello World";
            }
            String myStringModified = myString.replace("World", "World!!!");
            System.out.println(myStringModified);
        });
    }

    @Test
    @DisplayName("Assume number of rows in DB is greater than 5")
    void shouldAssertEqualsWhenConditionIsTrue() {
        int numberOfRowsInDB = 100;
        // The condition must be true so the rest of assertions can be executed
        assumeTrue(numberOfRowsInDB > 5);
        assertEquals(100, numberOfRowsInDB);
    }

    @Test
    @Disabled("Implement when transaction service is tested")
    @DisplayName("Disabled test for future implementation")
    void shouldBeDisabledForFutureImplementation() {
        fail();
    }

    @Test
    @DisplayName("Assert same object reference if not on Windows OS")
    @DisabledOnOs(OS.WINDOWS)
    void shouldAssertSameObjectOnNonWindows() {
        Object kali = new Object();
        Object centos = new Object();
        // Assert objects are the same
        assertSame(kali, centos);
    }

    //@Test  // if you include this, you will see a 'competing tag' error
    @RepeatedTest(5)
    @DisplayName("Repeated test that checks random number generation")
    void shouldRepeatTestAndPrintRepetitionInfo(RepetitionInfo repetitionInfo) {
        System.out.println("Current repetition:" + repetitionInfo.getCurrentRepetition());
        Random random = new Random();
        assertEquals(3, random.nextInt(5));
    }

    // @Test  // if you include this, you will see a 'competing tag' error
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 6, 8, 11})
    @DisplayName("Parameter test for checking even numbers")
    void shouldCheckEvenNumbers(int value) {
        assertEquals(0, value % 2, value + " is not even");
    }

    @ParameterizedTest
    @MethodSource("numberOfUsers")
    @DisplayName("Parameterized test with method source for user count")
    void shouldCheckUserCount(int number, List<String> csList) {
        assertEquals(number, csList.size());
    }

    @Test
    @Tag("development")
    @DisplayName("Development tagged test")
    void shouldIncludeDevelopmentTag() {
        assertTrue(true);
    }

    @Test
    @Tag("production")
    @DisplayName("Production tagged test")
    void shouldIncludeProductionTag() {
        assertTrue(true);
    }

    @Test
    void shouldThrowExceptionAndBeLogged() {
        // is Error
        String str = null;
        assertTrue(str.isEmpty());
    }

    @Test
    @EnabledIf("isLuckyDay")
    void shouldRunOnALuckyDay() {
        System.out.println("Run on lucky day");
    }

    @Test
    @DisabledIf("isLuckyDay")
    void shouldRunOnAnUnluckyDay() {
        System.out.println("Run on unlucky day");
    }

    boolean isLuckyDay() {
        return true;
    }

}
