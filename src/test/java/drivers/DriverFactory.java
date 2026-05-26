package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.ConfigReader;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver
            = new ThreadLocal<>();

    public static WebDriver initializeDriver() {

        String browser =
                ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            driver.set(
                    new ChromeDriver()
            );
        }

        driver.get()

                .manage()

                .timeouts()

                .implicitlyWait(

                        Duration.ofSeconds(

                                Integer.parseInt(

                                        ConfigReader.getProperty(
                                                "implicitWait"
                                        )
                                )
                        )
                );

        driver.get()

                .manage()

                .timeouts()

                .pageLoadTimeout(
                        Duration.ofSeconds(60)
                );

        return driver.get();
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }

    public static void setDriver(

            WebDriver webDriver
    ) {

        driver.set(webDriver);
    }
}