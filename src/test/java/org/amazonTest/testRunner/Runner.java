package org.amazonTest.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/org/amazonTest/features"},
        glue= {"org/amazonTest/stepDefinition"},
        plugin={"pretty","html:target/cucumber-reports"}
)

public class Runner {

}
