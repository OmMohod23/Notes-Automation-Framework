package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

import org.testng.Assert;

import pages.NotesPage;
import utils.ApiUtils;

import static io.restassured.RestAssured.given;

public class NegativeSteps {

    NotesPage notesPage =
            new NotesPage();

    Response response;

    String token;

    // ===============================
    // UI NEGATIVE TEST CASES
    // ===============================

    @When("user leaves title field empty")
    public void user_leaves_title_field_empty() {

        notesPage.leaveTitleFieldEmpty();
    }

    @Then("title validation message should display")
    public void title_validation_message_should_display() {

        Assert.assertTrue(

                notesPage.isTitleValidationDisplayed()
        );
    }

    @When("user leaves description field empty")
    public void user_leaves_description_field_empty() {

        notesPage.leaveDescriptionFieldEmpty();
    }

    @Then("description validation message should display")
    public void description_validation_message_should_display() {

        Assert.assertTrue(

                notesPage.isDescriptionValidationDisplayed()
        );
    }

    @When("user enters oversized title")
    public void user_enters_oversized_title() {

        notesPage.enterOversizedTitle();
    }

    @When("user enters oversized description")
    public void user_enters_oversized_description() {

        notesPage.enterOversizedDescription();
    }

    @Then("note should not be created successfully")
    public void note_should_not_be_created_successfully() {

        Assert.assertTrue(

                notesPage
                        .isOversizedTitleValidationDisplayed()
        );

        Assert.assertTrue(

                notesPage
                        .isOversizedDescriptionValidationDisplayed()
        );
    }

    // ===============================
    // API NEGATIVE TEST CASES
    // ===============================

    @When("user sends GET notes request with invalid token")
    public void user_sends_get_notes_request_with_invalid_token() {

        response =
                given()

                        .header(
                                "Authorization",
                                "Bearer invalid_token"
                        )

                        .when()

                        .get(
                                "https://practice.expandtesting.com/notes/api/notes"
                        );
    }

    @Then("unauthorized status code should be returned")
    public void unauthorized_status_code_should_be_returned() {

        Assert.assertEquals(

                response.getStatusCode(),
                401
        );
    }


    @When("user sends invalid payload to create note API")
    public void user_sends_invalid_payload_to_create_note_api() {

        response =
                given()

                        .header(
                                "x-auth-token",
                                ApiUtils.token
                        )

                        .contentType("application/json")

                        .body("{}")

                        .when()

                        .post(
                                "https://practice.expandtesting.com/notes/api/notes"
                        );

        response.then().log().all();
    }

    @Then("bad request status code should be returned")
    public void bad_request_status_code_should_be_returned() {

        Assert.assertEquals(

                response.getStatusCode(),
                400
        );
    }

    @When("user requests invalid note id")
    public void user_requests_invalid_note_id() {

        response =
                given()

                        .header(
                                "x-auth-token",
                                ApiUtils.token
                        )

                        .when()

                        .get(
                                "https://practice.expandtesting.com/notes/api/notes/507f1f77bcf86cd799439011"
                        );

        response.then().log().all();
    }

    @Then("not found status code should be returned")
    public void not_found_status_code_should_be_returned() {

        Assert.assertEquals(

                response.getStatusCode(),
                404
        );
    }
}