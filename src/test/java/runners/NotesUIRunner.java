package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/resources/features/2_notesUI-TS_4_5_6.feature",

        glue = {

                "stepdefinitions",
                "hooks"
        },

        plugin = {

                "pretty",

                "html:target/notesui-report.html",

                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

public class NotesUIRunner
        extends AbstractTestNGCucumberTests {
}