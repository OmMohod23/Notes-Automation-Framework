package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/resources/features/3_notesAPI-TS_7_8.feature",

        glue = {

                "stepdefinitions",
                "hooks"
        },

        plugin = {

                "pretty",

                "html:target/notesapi-report.html",

                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

public class NotesAPIRunner
        extends AbstractTestNGCucumberTests {
}