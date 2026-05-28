package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import pages.NotesPage;
import utils.ConfigReader;

public class NotesUISteps {

    LoginPage loginPage = new LoginPage();

    DashboardPage dashboardPage =
            new DashboardPage();

    NotesPage notesPage =
            new NotesPage();

    String createdTitle;

    @Given("user is logged into notes application")
    public void user_is_logged_into_notes_application() {

        loginPage.clickLoginHomeButton();

        loginPage.enterEmail(
                ConfigReader.getProperty("email")
        );

        loginPage.enterPassword(
                ConfigReader.getProperty("password")
        );

        loginPage.clickSignInButton();

        Assert.assertTrue(
                dashboardPage.isDashboardLoaded()
        );
    }

    @When("user clicks add note button")
    public void user_clicks_add_note_button() {

        notesPage.clickAddNoteButton();
    }

    @And("user selects category {string}")
    public void user_selects_category(String category) {

        notesPage.selectCategory(category);
    }

    @And("user enters note title {string}")
    public void user_enters_note_title(String title) {

        createdTitle = title;

        notesPage.enterTitle(title);
    }

    @And("user enters note description {string}")
    public void user_enters_note_description(String description) {

        notesPage.enterDescription(description);
    }

    @And("user clicks create note button")
    public void user_clicks_create_note_button() {

        notesPage.clickCreateButton();
    }

    @Then("newly created note should appear in notes list")
    public void newly_created_note_should_appear_in_notes_list() {

        Assert.assertTrue(
                notesPage.isCreatedNoteDisplayed(createdTitle)
        );
    }

    // Filter Test Steps

    @When("user clicks {string} category filter")
    public void user_clicks_category_filter(String category) {

        notesPage.clickCategoryFilter(category);
    }

    @Then("only {string} category notes should display")
    public void only_category_notes_should_display(String category) {

        Assert.assertTrue(
                notesPage.isFilteredCategoryDisplayed(category)
        );
    }

    @Then("create note modal should display correctly")
    public void create_note_modal_should_display_correctly() {

        Assert.assertTrue(
                notesPage.isCreateNoteModalDisplayed()
        );
    }

    @When("user clicks edit note button")
    public void user_clicks_edit_note_button() {

        notesPage.clickEditButton();
    }

    @When("user updates note title to {string}")
    public void user_updates_note_title_to(String updatedTitle) {

        notesPage.updateTitle(updatedTitle);
    }

    @When("user updates note description to {string}")
    public void user_updates_note_description_to(String updatedDescription) {

        notesPage.updateDescription(updatedDescription);
    }

    @When("user clicks save note button")
    public void user_clicks_save_note_button() {

        notesPage.clickSaveButton();
    }

    @Then("updated note should appear in notes list")
    public void updated_note_should_appear_in_notes_list() {

        Assert.assertTrue(
                notesPage.isUpdatedNoteDisplayed()
        );
    }

}