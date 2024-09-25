package com.edl.student.bdd;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.edl.student.bdd.steps")
@ConfigurationParameter(key= Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features/student.feature")
@ConfigurationParameter(key= Constants.GLUE_PROPERTY_NAME, value = "com/edl/student/bdd/steps")
@ConfigurationParameter(key= Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key= Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report/cucumber.html")

public class TestRunner {
}
