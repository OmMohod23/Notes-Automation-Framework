package utils;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static String token;

    public static String noteId;

    public static Response response;

    public static void generateToken(String email,String password) {

        System.out.println(
                "\n===== GENERATING API TOKEN =====");

        RestAssured.baseURI ="https://practice.expandtesting.com/notes/api";

        //data sent to server -jsonpayload format
        String payload =
                "{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";

        //response object -Stores API response returned by server.
        response =
                given()

                        .log().all() //Prints complete request details in console
                        .header(//metadata sent with request
                                "Content-Type",
                                "application/json"  // I am sending JSON data
                        )
                        .body(payload)      //send email/password data to backend
                        .post("/users/login");
                        //Sends: POST request  to: users/login

        response.then().log().all();//Prints complete response details: status code response JSON headers token

        token =response.jsonPath()//JSON traversal mechanism
                        .getString("data.token");//Extracts token field inside data object

        System.out.println(
                "\nGenerated Token: " + token
        );
    }

    public static void createNote() {

        System.out.println("\n===== CREATING NOTE VIA API =====");
        //json request body
        String payload =
                "{\n" +
                        "  \"title\": \"API Note\",\n" +
                        "  \"description\": \"API Description\",\n" +
                        "  \"category\": \"Home\"\n" +
                        "}";

        response =
                given()

                        .log().all()

                        .header(   //Sends authentication token to backend , else 401 Unauthorized
                                "x-auth-token",//custom authentication header
                                token
                        )

                        .header(
                                "Content-Type",
                                "application/json"
                        )

                        .body(payload)
                        //Sends: HTTP POST request to: /notes POST means: create new resource/data
                        .post(
                                "https://practice.expandtesting.com/notes/api/notes"
                        );

        response.then().log().all();

        noteId =
                response.jsonPath()//Used to navigate/read JSON response.
                        .getString("data.id");//Extracts id field inside data object from response JSON and stores in noteId variable for later use.

        System.out.println(
                "\nCreated Note ID: " + noteId
        );
    }

    //fetch or retrieve notes from backend API
    public static void getNotes() {

        System.out.println(
                "\n===== FETCHING NOTES ====="
        );

        response =
                given() //Starts Rest Assured request builder. Used to configure request
                        .log().all()

                        .header(
                                "x-auth-token",token
                        )
                        .get(
                                "https://practice.expandtesting.com/notes/api/notes"
                        );

        response.then().log().all();
    }

    public static void deleteNote() {

        System.out.println(
                "\n===== DELETING NOTE ID: "+ noteId+ " ====="
        );

        response = given()
                        .log().all()
                        .header(
                                "x-auth-token",token
                        )
                        .delete(
                                "https://practice.expandtesting.com/notes/api/notes/"+ noteId
                        );

        response.then().log().all();
    }

    //PUT method
    public static void updateNote() {

        System.out.println(
                "\n===== UPDATING NOTE ====="
        );
        //Creates JSON request body containing updated note data
        String payload =
                "{\n" +
                        "  \"title\": \"Updated API Note\",\n" +
                        "  \"description\": \"Updated Description\",\n" +
                        "  \"completed\": false\n" +
                        "}";

        response =
                given()
                        .log().all()
                        .header(
                                "x-auth-token",
                                token
                        )
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .body(payload)//Attaches updated note data to request
                        .put(
                                "https://practice.expandtesting.com/notes/api/notes/"+ noteId
                        );

        response.then().log().all();
    }
}