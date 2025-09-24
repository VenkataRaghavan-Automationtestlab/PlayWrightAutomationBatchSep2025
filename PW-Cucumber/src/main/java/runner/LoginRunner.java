package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/java/features", 		// Path to your .feature files
		glue = { "stepDefinitions" }, 						// Step Definitions & Hooks package
		monochrome = true, 									// Better console output (no junk chars)
		publish = true, 									// Publishes report to Cucumber Cloud
		plugin = { "pretty", "html:target/cucumber-reports/cucumber.html", 
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml" })

public class LoginRunner extends AbstractTestNGCucumberTests {

}
