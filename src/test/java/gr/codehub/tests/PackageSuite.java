package gr.codehub.tests;

import gr.codehub.tests.functional.HypotenuseCalculatorTest;
import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Full check for all test classes in 'functional'")
@IncludePackages("gr.codehub.tests.functional")
@SelectClasses(HypotenuseCalculatorTest.class)
@IncludeClassNamePatterns("gr\\.codehub.*")
@IncludeTags("production")
// @SelectPackages({"gr.codehub.tests.functional","gr.codehub.tests.util"})
public class PackageSuite {
}
