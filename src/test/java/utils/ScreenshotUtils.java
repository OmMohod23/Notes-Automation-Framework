package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class ScreenshotUtils {

    public static void captureScreenshot(

            WebDriver driver,
            String screenshotName
    ) {

        try {

            File sourceFile =

                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            File destinationFile =

                    new File(
                            "screenshots/"
                                    + screenshotName
                                    + ".png"
                    );

            destinationFile
                    .getParentFile()
                    .mkdirs();

            Files.copy(

                    sourceFile.toPath(),
                    destinationFile.toPath(),

                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(

                    "\nScreenshot Saved: "
                            + destinationFile.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(

                    "\nFailed To Capture Screenshot"
            );

            e.printStackTrace();
        }
    }

    public static void attachScreenshotToAllure(

            WebDriver driver
    ) {

        byte[] screenshot =

                ((TakesScreenshot) driver)
                        .getScreenshotAs(
                                OutputType.BYTES
                        );

        Allure.addAttachment(

                "Failure Screenshot",

                new ByteArrayInputStream(
                        screenshot
                )
        );
    }
}