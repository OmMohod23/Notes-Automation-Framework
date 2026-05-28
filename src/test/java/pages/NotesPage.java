package pages;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

import java.util.List;

public class NotesPage {

    WebDriver driver;

    public NotesPage() {

        driver =
                DriverFactory.getDriver();
    }

    // Create Note Locators

    private final By addNoteButton =
            By.cssSelector("[data-testid='add-new-note']");

    private final By categoryDropdown =
            By.cssSelector("[data-testid='note-category']");

    private final By titleField =
            By.cssSelector("[data-testid='note-title']");

    private final By descriptionField =
            By.cssSelector("[data-testid='note-description']");

    private final By titleValidationMessage =
            By.className("invalid-feedback");

    private final By createButton =
            By.cssSelector("[data-testid='note-submit']");

    // Note Card Locators

    private final By noteCardTitle =
            By.cssSelector("[data-testid='note-card-title']");

    // Filter Button Locators

    private final By allFilterButton =
            By.cssSelector("[data-testid='category-all']");

    private final By homeFilterButton =
            By.cssSelector("[data-testid='category-home']");

    private final By workFilterButton =
            By.cssSelector("[data-testid='category-work']");

    private final By personalFilterButton =
            By.cssSelector("[data-testid='category-personal']");

    // Modal Locators

    private final By createNoteModal =
            By.className("modal-content");

    private final By modalCategoryDropdown =
            By.cssSelector("[data-testid='note-category']");

    private final By modalTitleField =
            By.cssSelector("[data-testid='note-title']");

    private final By modalDescriptionField =
            By.cssSelector("[data-testid='note-description']");

    private final By modalCreateButton =
            By.cssSelector("[data-testid='note-submit']");

    // Edit Button Locator

    private final By editButton =
            By.cssSelector("[data-testid='note-edit']");



    private final By descriptionValidationMessage =
            By.className("invalid-feedback");




    // Create Note Actions
    public void clickAddNoteButton() {
        //wait until Add Note button becomes visible
        WebElement button =WaitUtils.waitForElementVisible(addNoteButton);

        //Converts WebDriver into JavaScriptExecutor.Allows execution of JavaScript inside browser
        JavascriptExecutor js =(JavascriptExecutor) driver;

        //Scrolls page until button comes into visible viewport
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});", //center=place element into center of the screen
                button
        );

        WaitUtils.sleep(1000);

        //click button now
        js.executeScript(
                "arguments[0].click();",
                button
        );
    }

    public void selectCategory(String category) {
        //using select for dropdown option
        Select select =new Select(
                        WaitUtils.waitForElementVisible(
                                categoryDropdown
                        )
                );
        //3 ways to select
        select.selectByVisibleText(category);
    }

    public void enterTitle(String title) {

        WebElement element = WaitUtils.waitForElementVisible(titleField);

        element.clear();

        element.sendKeys(title);
    }

    public void enterDescription(String description) {

        WebElement element = WaitUtils.waitForElementVisible(descriptionField);

        element.clear();

        element.sendKeys(description);
    }

    public void clickCreateButton() {

        WebElement button =WaitUtils.waitForElementVisible(createButton);
        JavascriptExecutor js =(JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button
        );

        WaitUtils.sleep(1000);

        js.executeScript(
                "arguments[0].click();",
                button
        );
    }

    public boolean isCreatedNoteDisplayed(String title) {

        WaitUtils.sleep(2000);

        //store all note card title into list , here noteCardTitle is locator which will find all title element and store in list
        List<WebElement> noteTitles =driver.findElements(noteCardTitle);

        for (WebElement note : noteTitles) {
            //Checks whether current note text matches expected title.
            if (note.getText().trim().equals(title)) {

                return true;
            }
        }

        return false;
    }

    // Filter Actions

    public void clickCategoryFilter(String category) {
        WebElement element = null;
        switch (category) {
            case "All":
                element =
                        WaitUtils.waitForElementVisible(
                                allFilterButton
                        );
                break;

            case "Home":
                element =
                        WaitUtils.waitForElementVisible(
                                homeFilterButton
                        );
                break;

            case "Work":
                element =
                        WaitUtils.waitForElementVisible(
                                workFilterButton
                        );
                break;

            case "Personal":
                element =
                        WaitUtils.waitForElementVisible(
                                personalFilterButton
                        );
                break;
        }

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        WaitUtils.sleep(1000);

        js.executeScript(
                "arguments[0].click();",
                element
        );

        WaitUtils.sleep(1000);
    }

    //Method validates whether all currently visible notes belong to selected category.
    public boolean isFilteredCategoryDisplayed(String category) {

        WaitUtils.sleep(2000);

        List<WebElement> categoryBadges =
                driver.findElements(

                        By.cssSelector(
                                "[data-testid='note-card-category']"
                        )
                );

        for (WebElement badge : categoryBadges) {

            //Gets text from current category badge.
            String actualCategory = badge.getText().trim();

            if (!actualCategory.equalsIgnoreCase(category)) {
                return false;
            }
        }

        return true;
    }

    // Modal Validation
    public boolean isCreateNoteModalDisplayed() {

        return WaitUtils
                .waitForElementVisible(createNoteModal)
                .isDisplayed()

                && WaitUtils
                .waitForElementVisible(modalCategoryDropdown)
                .isDisplayed()

                && WaitUtils
                .waitForElementVisible(modalTitleField)
                .isDisplayed()

                && WaitUtils
                .waitForElementVisible(modalDescriptionField)
                .isDisplayed()

                && WaitUtils
                .waitForElementVisible(modalCreateButton)
                .isDisplayed();
    }

    // Edit Note Actions
    public void clickEditButton() {

        WaitUtils
                .waitForElementClickable(editButton)
                .click();
    }

    public void updateTitle( String updatedTitle) {

        WebElement title =
                WaitUtils.waitForElementVisible(
                        titleField
                );

        title.clear();

        title.sendKeys(updatedTitle);
    }

    public void updateDescription(String updatedDescription) {

        WebElement description =
                WaitUtils.waitForElementVisible(
                        descriptionField
                );

        description.clear();

        description.sendKeys(updatedDescription);
    }

    public void clickSaveButton() {
        //here create button is termed as save button
        WebElement saveButton =
                WaitUtils.waitForElementVisible(
                        createButton
                );

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                saveButton
        );
    }


    //Method checks whether updated note content appears on page
    public boolean isUpdatedNoteDisplayed() {

        WaitUtils.sleep(2000);

        return driver
                .getPageSource()//returns complete HTML source code of current page as String
                .contains("Updated");//data-testid="note-card-updated-at" this will be found
    }

    // Utility Methods
    //Method checks whether given note title exists anywhere on current page
    public boolean isNotePresent( String noteTitle) {

        return DriverFactory
                .getDriver()
                .getPageSource()
                .contains(noteTitle);//Checks whether page source contains provided note title text
    }

    public void refreshPage() {

        driver.navigate().refresh();
    }

    public void leaveTitleFieldEmpty() {

        WebElement title =
                WaitUtils.waitForElementVisible(
                        titleField
                );

        title.clear();
    }

    //true → validation message displayed
    public boolean isTitleValidationDisplayed() {

        WaitUtils.sleep(1000);

        //Creates list to store validation message elements
        List<WebElement> validationMessages = driver.findElements(titleValidationMessage);

        for (WebElement message : validationMessages) {

            //Gets visible text from validation element and checks whether it contains: Title is required
            if (message.isDisplayed() && message.getText().contains("Title is required")) {
                return true;
            }
        }

        return false;
    }




    public void leaveDescriptionFieldEmpty() {

        WebElement description =
                WaitUtils.waitForElementVisible(descriptionField);

        description.clear();
    }

    public boolean isDescriptionValidationDisplayed() {

        WaitUtils.sleep(1000);

        List<WebElement> validationMessages = driver.findElements(descriptionValidationMessage);

        for (WebElement message : validationMessages) {

            if (message.isDisplayed() && message.getText().contains("Description is required")) {

                return true;
            }
        }

        return false;
    }

    public void enterOversizedTitle() {

        String largeTitle =
                "A".repeat(300);

        enterTitle(largeTitle);
    }

    public void enterOversizedDescription() {

        String largeDescription =
                "B".repeat(5000);

        enterDescription(largeDescription);
    }

    public boolean isOversizedTitleValidationDisplayed() {

        WaitUtils.sleep(1000);

        List<WebElement> validationMessages =
                driver.findElements(
                        titleValidationMessage
                );

        for (WebElement message : validationMessages) {

            if (message.isDisplayed() && message.getText().contains("Title should be between 4 and 100 characters")) {
                return true;
            }
        }

        return false;
    }

    public boolean isOversizedDescriptionValidationDisplayed() {

        WaitUtils.sleep(1000);

        List<WebElement> validationMessages =
                driver.findElements(
                        descriptionValidationMessage
                );

        for (WebElement message : validationMessages) {
            if (message.isDisplayed()&& message.getText().contains("Description should be between 4 and 1000 characters")) {

                return true;
            }
        }

        return false;
    }

}