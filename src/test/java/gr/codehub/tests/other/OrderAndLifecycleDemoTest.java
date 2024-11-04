package gr.codehub.tests.other;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderAndLifecycleDemoTest {

    static Integer aValue = 5;

    @BeforeAll
    static void setUp() {
        //this will run at the very beginning
        System.out.println("Starting the process with value:" + aValue);
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Ending the process with value:" + aValue);
    }

    @Test
    @Order(2)
    @DisplayName("aaaaaa with order 2")
    void shouldIncrementValue3() {
        aValue++;
        System.out.println("Value is " + aValue);
        assertEquals(6, aValue);
    }

    @Test
    @Order(1)
    @DisplayName("zzzzzz with order 1")
    void shouldCheckInitialValue2() {
        System.out.println("Original value is " + aValue);
        assertEquals(5, aValue);
    }

    @Test
    @Order(3)
    @DisplayName("bbbb with order 3")
    void shouldCheckInbetweenValue1() {
        System.out.println("Original value is " + aValue);
        assertEquals(5, aValue);
    }

    @Nested
    @DisplayName("Nested Test Class")
    class NestedClass {

        String myString;

        @BeforeEach
        void initializeString() {
            System.out.println("Initializing string");
            myString = "Hello World";
        }

        @Test
        @DisplayName("Clear the content of string")
        void shouldClearString() {
            System.out.println("Clearing string");
            myString = "";
            assertTrue(myString.isEmpty());
        }

        @Test
        @DisplayName("Check string length")
        void shouldCheckStringLength() {
            System.out.println("Checking the length of string");
            String newString = myString.replace("World", "");
            assertEquals(6, newString.length());
        }

        @AfterEach
        public void cleanUp() {
            System.out.println("A clean up process");
        }
    }
}
