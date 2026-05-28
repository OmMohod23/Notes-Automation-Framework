package stepdefinitions;

import utils.ApiUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator
        .matchesJsonSchemaInClasspath;

public class NotesAPISteps {

    @Given("user generates API authentication token")
    public void user_generates_api_authentication_token() {

        ApiUtils.generateToken(
                "ommohod50@gmail.com",
                "omMohod"
        );
    }

    @Then("authentication token should be generated")
    public void authentication_token_should_be_generated() {

        System.out.println(
                "\n===== VALIDATING TOKEN ====="
        );

        Assert.assertNotNull(ApiUtils.token);

        System.out.println(
                "Token Generated Successfully"
        );
    }

    @When("user creates note using API")
    public void user_creates_note_using_api() {

        ApiUtils.createNote();
    }

    @Then("created note response status should be 200")
    public void created_note_response_status_should_be_200() {

        System.out.println(
                "\n===== VALIDATING CREATE NOTE STATUS ====="
        );

        ApiUtils.response
                .then()
                .statusCode(200);

        System.out.println(
                "Create Note Status Code Validation Passed"
        );
    }

    @When("user sends GET notes API request")
    public void user_sends_get_notes_api_request() {

        ApiUtils.getNotes();
    }

    @Then("API response status should be 200")
    public void api_response_status_should_be_200() {

        System.out.println(
                "\n===== VALIDATING GET NOTES STATUS ====="
        );

        ApiUtils.response
                .then()//Starts response validation section in Rest Assured
                .statusCode(200);

        System.out.println(
                "GET Notes Status Code Validation Passed"
        );
    }

    @And("notes response should match JSON schema")
    public void notes_response_should_match_json_schema() {

        System.out.println(
                "\n===== VALIDATING JSON SCHEMA ====="
        );

        ApiUtils.response//Uses stored GET Notes API response
                .then()
                .assertThat()
                .body(
                        matchesJsonSchemaInClasspath(//inbuilt method-Rest Assured schema validator method
                                "schemas/notesSchema.json"
                        )
                );

        System.out.println(
                "JSON Schema Validation Passed"
        );
    }

    @When("user deletes created note using API")
    public void user_deletes_created_note_using_api() {

        ApiUtils.deleteNote();
    }

    @Then("delete API response status should be 200")
    public void delete_api_response_status_should_be_200() {

        System.out.println(
                "\n===== VALIDATING DELETE STATUS ====="
        );

        ApiUtils.response
                .then()
                .statusCode(200);

        System.out.println(
                "Delete Note Status Code Validation Passed"
        );
    }
}