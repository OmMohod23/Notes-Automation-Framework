package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/resources/features/1_login-TS_1_2.feature",

        glue = {

                "stepdefinitions",
                "hooks"
        },

        plugin = {

                "pretty",

                "html:target/login-report.html",

                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

public class LoginRunner
        extends AbstractTestNGCucumberTests {
}