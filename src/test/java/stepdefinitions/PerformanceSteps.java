package stepdefinitions;

import utils.ApiUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import org.testng.Assert;

public class PerformanceSteps {

    long postTime;

    long getTime;

    long putTime;

    long deleteTime;

    @Given("valid authentication token is generated")
    public void valid_authentication_token_is_generated() {

        ApiUtils.generateToken(
                "ommohod50@gmail.com",
                "omMohod"
        );
    }

    @When("user sends performant POST notes request")
    public void user_sends_performant_post_notes_request() {

        ApiUtils.createNote();

        postTime =
                ApiUtils.response.time();

        System.out.println(
                "POST Response Time: "
                        + postTime
                        + " ms"
        );
    }

    @And("user sends performant GET notes request")
    public void user_sends_performant_get_notes_request() {

        ApiUtils.getNotes();

        getTime =
                ApiUtils.response.time();

        System.out.println(
                "GET Response Time: "
                        + getTime
                        + " ms"
        );
    }

    @And("user sends performant PUT notes request")
    public void user_sends_performant_put_notes_request() {

        ApiUtils.updateNote();

        putTime =
                ApiUtils.response.time();

        System.out.println(
                "PUT Response Time: "
                        + putTime
                        + " ms"
        );
    }

    @And("user sends performant DELETE notes request")
    public void user_sends_performant_delete_notes_request() {

        ApiUtils.deleteNote();

        deleteTime =
                ApiUtils.response.time();

        System.out.println(
                "DELETE Response Time: "
                        + deleteTime
                        + " ms"
        );
    }

    @Then("all API response times should be under 2 seconds")
    public void all_api_response_times_should_be_under_2_seconds() {

        Assert.assertTrue(postTime < 2000);

        Assert.assertTrue(getTime < 2000);

        Assert.assertTrue(putTime < 2000);

        Assert.assertTrue(deleteTime < 2000);

        System.out.println(
                "\nAll API Response Times Are Under 2 Seconds"
        );
    }
}