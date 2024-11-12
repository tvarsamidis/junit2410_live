package gr.codehub.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// see https://howtodoinjava.com/junit5/junit5-test-suites-examples/

@Suite
@SuiteDisplayName("Generic Suite")
//@SelectPackages("gr.codehub.tests")
@SelectClasses({s02GenericTest.class, s04AssertionDemoTest.class})

public class s05GenericSuite {
}
