package gr.codehub.tests.other;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class that showcases how to run tests under certain conditions when building an application. Each of these
 * annotations can be placed on any level (i.e., test interface, test class, or test method) and work. This class also
 * contains an extension (RunOnCIExtension.class) which also checks for a condition
 */

@Tag("example")
public class s09ConditionalTest {

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    public void shouldExecuteOnWindowsAndMac() {
        // Only enabled for windows & mac
    }

    @Test
    @DisabledOnOs(OS.LINUX)
    public void shouldSkipOnLinux() {
        // Disabled for Linux
        assertTrue(true);
    }

    @Test
    @EnabledOnJre({JRE.JAVA_21, JRE.JAVA_22})
    public void shouldExecuteOnJava21And22Only() {
        // Only enabled for JRE21 & JRE22
        assertTrue(true);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_13)
    public void shouldRunFromJava8ToJava13() {
        // Only enabled on Java 8, 9, 10, 11, 12, and 13.
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_14, max = JRE.JAVA_15)
    public void shouldSkipOnJava14And15() {
        // Enabled for all except for Java 14 and 15.
    }

    @Test
    @DisabledOnJre(JRE.OTHER)
    public void shouldRunWithSupportedJREsOnly() {
        // Only enabled on Java ????
    }

    // from the command line
    // java -XshowSettings:properties -version
    // This one does not work: java -version
    @Test
    @EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Oracle.*")
    public void shouldRunIfVendorIsOracle() {
        // Only enabled if the JVM vendor is Oracle
    }

    // [ and ] define a character class, meaning it will match any of the characters inside the brackets.
    // / inside the brackets matches the Unix-based file separator /
    @Test
    @DisabledIfSystemProperty(named = "file.separator", matches = "[/]") // line separator, path separator etc
    public void shouldSkipIfFileSeparatorIsSlash() {
        // Disabled if the system running supports "/" as its file separator
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "GDMSESSION", matches = "ubuntu")
    public void shouldRunOnUbuntuEnvironment() {
        // Only enabled if the environment variable "GDMSESSION" equals "ubuntu"
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "LC_TIME", matches = ".*UTF-8.")
    public void shouldSkipIfTimeIsNotUTF8() {
        //  Disabled if the environment variable "LC_TIME" does not equal "*UTF-8.". Practically means it will not
        //  run if the time is not based on UTF8
    }
}
