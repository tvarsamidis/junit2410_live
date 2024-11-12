package gr.codehub.tests;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// see https://howtodoinjava.com/junit5/junit5-test-suites-examples/

@Suite
@SuiteDisplayName("Full Suite")

//	Specifies packages (and sub-packages) from which to include test classes for execution.
@SelectPackages("gr.codehub.tests")
// @SelectPackages({"com.howtodoinjava.junit5.examples.packageA",
//                     "com.howtodoinjava.junit5.examples.packageB"})


// Filters and includes/excludes sub-packages from the already selected packages using @SelectPackages.
// @IncludePackages
@ExcludePackages("gr.codehub.tests.service")
//
// e.g. all in packageC
//@SelectPackages("com.howtodoinjava.junit5.examples")
//@IncludePackages("com.howtodoinjava.junit5.examples.packageC")
//
// no packageC
//@SelectPackages("com.howtodoinjava.junit5.examples")
//@ExcludePackages("com.howtodoinjava.junit5.examples.packageC")

// Filters and includes/excludes test classes or methods with specific tags from the already
// selected packages/classes using @SelectPackages and @SelectClasses.
//@IncludeTags("development") // will ONLY RUN THESE
// @ExcludeTags()

// the following will AND the IncludeTags
//@IncludeClassNamePatterns("^.*Info.*$")
@IncludeClassNamePatterns("^.*Info.*$")


//@IncludeClassNamePatterns("^.*Test$")
//@IncludeClassNamePatterns("^.*Test$")
// You may apply more than one pattern in above annotations. In case of multiple patterns,
// they are combined using OR semantics.
// It means that if fully qualified name of a class matches against at least one of the patterns,
// the class will be included/excluded from the test suite.


// Specifies individual test classes or test methods to include in the test suite.
@SelectClasses(s02GenericTest.class)
// @SelectClasses( { ClassATest.class, ClassBTest.class, ClassCTest.class } )

public class s05FullSuite {
}
