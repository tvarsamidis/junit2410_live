package gr.codehub.tests.other;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Conditional Tests")
public class ConditionalTest {


    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void shouldExecuteOnWindows() {
        assertTrue(new File("./hello").getAbsolutePath().contains("\\"));
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void shouldNotExecuteOnWindows() {
        assertTrue(new File("./hello").getAbsolutePath().contains("\\"));
    }

    @Test
    @DisabledForJreRange(min=JRE.JAVA_8, max = JRE.JAVA_21)
    public void shouldNotRunForJava8To21() {
        System.out.println("No assert");
    }

    @Test
    @DisabledOnJre(JRE.JAVA_21)
    public void shouldNotRunForJava21() {
        System.out.println("No assert");
    }

    @Test
    @EnabledIfSystemProperty(named = "file.separaaaator", matches = "[/]")
    public void shouldRunIfFileIsForwardSlash(){
        System.out.println("Run file.separator test");
    }

    @Test
    @EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Oracle.*")
    public void shouldRunIfJVMIsFromOracle(){
        System.out.println("JVM is from Oracle");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = "D:\\\\Prog.*")
    public void shouldRunIfInstalledInFolderPrograms() {
        System.out.println("run an assert");
    }
}
