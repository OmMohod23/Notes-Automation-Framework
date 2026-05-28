package pages;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import utils.SafeNavigationUtils;
public class LoginPage {

    WebDriver driver;

    public LoginPage() {

        driver =
                DriverFactory.getDriver();
    }
    // Locators

    private final By loginButtonHome =
            By.xpath("//a[contains(text(),'Login')]");

    private final By emailField =
            By.id("email");

    private final By passwordField =
            By.id("password");

    //login button on login page, not the one on home page
    private final By signInButton =
            By.xpath("//button[contains(text(),'Login')]");

    //wrong pass or mail
    private final By errorMessage =
            By.cssSelector("[data-testid='alert-message']");

    // Actions

    public void clickLoginHomeButton() {

        SafeNavigationUtils.safeNavigate(driver,"https://practice.expandtesting.com/notes/app/login");
    }

    public void enterEmail(String email) {
        //clearing already written text in email field before writing new email
        WaitUtils
                .waitForElementVisible(emailField)
                .clear();
        //types email
        WaitUtils
                .waitForElementVisible(emailField)
                .sendKeys(email);
    }

    public void enterPassword(String password) {
        //same first clear then enter pass
        WaitUtils
                .waitForElementVisible(passwordField)
                .clear();

        WaitUtils
                .waitForElementVisible(passwordField)
                .sendKeys(password);
    }

    public void clickSignInButton() {

        WebElement loginBtn =
                WaitUtils.waitForElementClickable(signInButton);

        //Converts WebDriver object into : JavascriptExecutor
        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                //bring element to top visible area of browser
                "arguments[0].scrollIntoView(true);",
                loginBtn
        );

        //Scrolls page until button comes into visible viewport.
        js.executeScript(
                "arguments[0].click();",//Performs JavaScript click directly on element.
                loginBtn
        );
    }

    public String getErrorMessage() {

        return WaitUtils
                .waitForElementVisible(errorMessage)
                .getText();
    }
}