package utils;

import org.openqa.selenium.By;

import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.StaleElementReferenceException;

import org.openqa.selenium.WebDriver;

public class SafeClickUtils_selfHealing {

    public static void safeClick(

            WebDriver driver,

            By locator
    ) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                driver.findElement(locator)
                        .click();

                LoggerUtils.logger.info(

                        "Clicked Successfully: "

                                + locator
                );

                return;

            } catch (

                    StaleElementReferenceException

                    |

                    ElementClickInterceptedException e
            ) {

                attempts++;

                LoggerUtils.logger.warn(

                        "Retrying Click For: "

                                + locator

                                + " | Attempt "

                                + attempts
                );

                WaitUtils.sleep(1000);
            }
        }

        throw new RuntimeException(

                "Unable To Click Element: "

                        + locator
        );
    }
}