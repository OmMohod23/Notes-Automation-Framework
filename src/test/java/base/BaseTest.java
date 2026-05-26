package base;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    public WebDriver driver;

    public void setup() {

        driver =
                DriverFactory.initializeDriver();

        DriverFactory.setDriver(driver);



        System.out.println(
                "Browser Launched Successfully"
        );
    }

    public void tearDown() {

        DriverFactory.quitDriver();

        System.out.println(
                "Browser Closed"
        );
    }
}