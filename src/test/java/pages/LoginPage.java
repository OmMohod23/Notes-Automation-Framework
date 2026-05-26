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

    private final By signInButton =
            By.xpath("//button[contains(text(),'Login')]");

    private final By errorMessage =
            By.cssSelector("[data-testid='alert-message']");

    // Actions

    public void clickLoginHomeButton() {

        SafeNavigationUtils.safeNavigate(

                driver,

                "https://practice.expandtesting.com/notes/app/login"
        );
    }

    public void enterEmail(String email) {

        WaitUtils
                .waitForElementVisible(emailField)
                .clear();

        WaitUtils
                .waitForElementVisible(emailField)
                .sendKeys(email);
    }

    public void enterPassword(String password) {

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

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                loginBtn
        );

        js.executeScript(
                "arguments[0].click();",
                loginBtn
        );
    }

    public String getErrorMessage() {

        return WaitUtils
                .waitForElementVisible(errorMessage)
                .getText();
    }
}