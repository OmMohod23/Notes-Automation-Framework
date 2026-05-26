package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/resources/features/6_negative-TS_12_13.feature",

        glue = {

                "stepdefinitions",
                "hooks"
        },

        plugin = {

                "pretty",

                "html:target/negative-report.html",

                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

public class NegativeRunner
        extends AbstractTestNGCucumberTests {
}