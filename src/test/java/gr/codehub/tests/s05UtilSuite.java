package gr.codehub.tests;

// see https://howtodoinjava.com/junit5/junit5-test-suites-examples/

import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Util Suite")
@SelectPackages("gr.codehub.tests.util")
// IncludePackages works in addition to other packages. The following one will not work on its own, try it
//@IncludePackages("gr.codehub.tests.util")
public class s05UtilSuite {
}
