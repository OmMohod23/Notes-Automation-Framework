package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/resources/features/5_performance-TS_11.feature",

        glue = {

                "stepdefinitions",
                "hooks"
        },

        plugin = {

                "pretty",

                "html:target/performance-report.html",

                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

public class PerformanceRunner
        extends AbstractTestNGCucumberTests {
}