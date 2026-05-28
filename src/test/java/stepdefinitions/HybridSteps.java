package stepdefinitions;

import utils.ApiUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

import pages.NotesPage;

import utils.TestDataStore;

import java.util.List;

public class HybridSteps {

    NotesPage notesPage =
            new NotesPage();

    @When("user creates note from UI")
    public void user_creates_note_from_ui() {


        TestDataStore.noteTitle =
                "Hybrid Note "
                        + System.currentTimeMillis();//returns current timestamp in milliseconds.
        //output-Hybrid Note 1748347283748

        TestDataStore.noteDescription =
                "Hybrid API Sync";

        TestDataStore.noteCategory =
                "Home";

        notesPage.clickAddNoteButton();

        notesPage.selectCategory(
                TestDataStore.noteCategory
        );

        notesPage.enterTitle(
                TestDataStore.noteTitle
        );

        notesPage.enterDescription(
                TestDataStore.noteDescription
        );

        notesPage.clickCreateButton();

        System.out.println(
                "\n===== UI NOTE CREATED ====="
        );

        System.out.println(
                "Title: "
                        + TestDataStore.noteTitle
        );
    }

    @When("user fetches notes using API")
    public void user_fetches_notes_using_api() {

        ApiUtils.generateToken(
                "ommohod50@gmail.com",
                "omMohod"
        );

        ApiUtils.getNotes();
    }

    @Then("created UI note should exist in API response")
    public void created_ui_note_should_exist_in_api_response() {
        //Converts complete API response into plain String format
        String responseBody = ApiUtils.response.asString();

        System.out.println(
                "\n===== VALIDATING UI API SYNC ====="
        );

        Assert.assertTrue(//Checks whether API response contains title of note created from UI
                responseBody.contains(
                        TestDataStore.noteTitle
                )
        );
        List<String> titles =
                ApiUtils.response
                        .jsonPath()
                        .getList("data.title");//Extracts all note titles from API JSON response


        List<String> ids =
                ApiUtils.response
                        .jsonPath()
                        .getList("data.id");//Extracts all note IDs from API response

        for (int i = 0; i < titles.size(); i++) {
            //Checks whether current title matches UI-created note title
            if (titles.get(i).equals(TestDataStore.noteTitle)) {

                TestDataStore.noteId =
                        ids.get(i);

                break;
            }
        }

        ApiUtils.noteId =TestDataStore.noteId;//Copies extracted note ID into ApiUtils for later use in delete and update operations

        System.out.println(
                "UI Created Note Found In API Response"
        );

        System.out.println(
                "Stored Note ID: "
                        + TestDataStore.noteId
        );
    }



    @Then("deleted note should not appear in UI")
    public void deleted_note_should_not_appear_in_ui() {

        notesPage.refreshPage();

        //Checks whether deleted note title is still present in notes list
        //true → note still visible
        //false → note removed - intended
        boolean noteExists =notesPage.isNotePresent(
                        TestDataStore.noteTitle
                );

        //deleted note should NOT appear on UI
        Assert.assertFalse(noteExists);

        System.out.println(
                "\nDeleted Note Removed From UI Successfully"
        );
    }
}