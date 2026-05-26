package utils;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static WebElement waitForElementClickable(By locator) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(10)
                );

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement waitForElementVisible(By locator) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(10)
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static boolean waitForUrlContains(String partialUrl) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(10)
                );

        return wait.until(
                ExpectedConditions.urlContains(partialUrl)
        );
    }

    public static void sleep(int milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}