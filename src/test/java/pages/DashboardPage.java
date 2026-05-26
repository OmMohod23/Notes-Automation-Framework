package pages;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class DashboardPage {

    WebDriver driver = DriverFactory.getDriver();

    private final By addNoteButton =
            By.cssSelector("[data-testid='add-new-note']");

    public boolean isDashboardLoaded() {

        return WaitUtils
                .waitForElementVisible(addNoteButton)
                .isDisplayed();
    }
}