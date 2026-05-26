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
                        + System.currentTimeMillis();

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

        String responseBody =
                ApiUtils.response.asString();

        System.out.println(
                "\n===== VALIDATING UI API SYNC ====="
        );

        Assert.assertTrue(
                responseBody.contains(
                        TestDataStore.noteTitle
                )
        );

        List<String> titles =
                ApiUtils.response
                        .jsonPath()
                        .getList("data.title");

        List<String> ids =
                ApiUtils.response
                        .jsonPath()
                        .getList("data.id");

        for (int i = 0; i < titles.size(); i++) {

            if (titles.get(i)
                    .equals(TestDataStore.noteTitle)) {

                TestDataStore.noteId =
                        ids.get(i);

                break;
            }
        }

        ApiUtils.noteId =
                TestDataStore.noteId;

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

        boolean noteExists =
                notesPage.isNotePresent(
                        TestDataStore.noteTitle
                );

        Assert.assertFalse(noteExists);

        System.out.println(
                "\nDeleted Note Removed From UI Successfully"
        );
    }
}