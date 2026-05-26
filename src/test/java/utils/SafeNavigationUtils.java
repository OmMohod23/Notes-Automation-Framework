package utils;

import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebDriver;

public class SafeNavigationUtils {

    public static void safeNavigate(

            WebDriver driver,

            String url
    ) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                LoggerUtils.logger.info(

                        "Navigating To: "

                                + url
                );

                driver.navigate().to(url);

                LoggerUtils.logger.info(

                        "Navigation Successful"
                );

                return;

            } catch (

                    TimeoutException e
            ) {

                attempts++;

                LoggerUtils.logger.warn(

                        "Navigation Timeout. Retrying Attempt "

                                + attempts
                );

                driver.navigate().refresh();

                WaitUtils.sleep(3000);
            }
        }

        throw new RuntimeException(

                "Unable To Load URL After Retries: "

                        + url
        );
    }
}