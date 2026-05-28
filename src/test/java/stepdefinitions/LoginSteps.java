package stepdefinitions;

import drivers.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.WaitUtils;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    DashboardPage dashboardPage =
            new DashboardPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {

        loginPage.clickLoginHomeButton();
    }

    @When("user enters valid email")
    public void user_enters_valid_email() {

        loginPage.enterEmail(
                ConfigReader.getProperty("email")
        );
    }

    @And("user enters valid password")
    public void user_enters_valid_password() {

        loginPage.enterPassword(
                ConfigReader.getProperty("password")
        );
    }

    @And("user clicks login button")
    public void user_clicks_login_button() {

        loginPage.clickSignInButton();
    }

    @Then("user should login successfully")
    public void user_should_login_successfully() {

        //Waits until browser URL contains: notes/app
        Assert.assertTrue(
                WaitUtils.waitForUrlContains(
                        "/notes/app"
                )
        );

        //Checks whether dashboard page loaded properly.
        Assert.assertTrue(
                dashboardPage.isDashboardLoaded()
        );
    }

    @And("user enters invalid password")
    public void user_enters_invalid_password() {

        loginPage.enterPassword("wrongPassword");
    }

    @Then("proper login error message should display")
    public void proper_login_error_message_should_display() {

        String actualError =
                loginPage.getErrorMessage();

//        Checks whether:actual value == expected value
//        If values differ: test fails,AssertionError generated


        Assert.assertEquals(
                actualError,
                "Incorrect email address or password"//this msg is hardcoded
        );
        //Compares:actual UI error message withe expected hardcoded message
    }

    @When("user enters unregistered email")
    public void user_enters_unregistered_email() {

        loginPage.enterEmail(
                "randomuser@gmail.com"
        );
    }

    @When("user leaves email field empty")
    public void user_leaves_email_field_empty() {

        loginPage.enterEmail("");
    }

    @Then("email required validation message should display")
    public void email_required_validation_message_should_display() {
        //current page url
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();

        //Checks whether current URL still contains:login
        //Meaning:user was NOT redirected after clicking login
        Assert.assertTrue(
                currentUrl.contains("login")
        );

        //If user stays on login page:
        //→ assertion passes
        //
        //If redirected elsewhere:
        //→ assertion fails.
    }

    @And("user leaves password field empty")
    public void user_leaves_password_field_empty() {

        loginPage.enterPassword("");
    }

    @Then("password required validation message should display")
    public void password_required_validation_message_should_display() {
        //Checks whether current URL still contains:login
        //Meaning:user was NOT redirected after clicking login
        String currentUrl =
                DriverFactory.getDriver().getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("login")
        );
    }
}