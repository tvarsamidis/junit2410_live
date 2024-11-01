package gr.codehub.tests;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Full check for all test classes")
@SelectPackages("gr.codehub.tests")
@ExcludeClassNamePatterns("^.*AssertionsDemo.*$")
// @SelectPackages({"gr.codehub.tests.functional","gr.codehub.tests.util"})
public class PackageSuite {
}
