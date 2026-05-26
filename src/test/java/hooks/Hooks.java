package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.ScreenshotUtils;

import drivers.DriverFactory;

import utils.LoggerUtils;

public class Hooks extends BaseTest {

    @Before
    public void launchBrowser() {
        LoggerUtils.logger.info(
                "Launching Browser"
        );
        setup();

    }

    @After
    public void closeBrowser(Scenario scenario) {

        if (scenario.isFailed()) {

            ScreenshotUtils.captureScreenshot(

                    DriverFactory.getDriver(),

                    scenario.getName()
                            .replace(" ", "_")
            );

            ScreenshotUtils.attachScreenshotToAllure(

                    DriverFactory.getDriver()
            );
        }
        LoggerUtils.logger.info(
                "Closing Browser"
        );
        tearDown();


    }
}