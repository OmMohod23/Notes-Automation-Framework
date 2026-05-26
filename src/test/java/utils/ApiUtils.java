package utils;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static String token;

    public static String noteId;

    public static Response response;

    public static void generateToken(
            String email,
            String password
    ) {

        System.out.println(
                "\n===== GENERATING API TOKEN ====="
        );

        RestAssured.baseURI =
                "https://practice.expandtesting.com/notes/api";

        String payload =
                "{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";

        response =
                given()

                        .log().all()

                        .header(
                                "Content-Type",
                                "application/json"
                        )

                        .body(payload)

                        .post("/users/login");

        response.then().log().all();

        token =
                response.jsonPath()
                        .getString("data.token");

        System.out.println(
                "\nGenerated Token: " + token
        );
    }

    public static void createNote() {

        System.out.println(
                "\n===== CREATING NOTE VIA API ====="
        );

        String payload =
                "{\n" +
                        "  \"title\": \"API Note\",\n" +
                        "  \"description\": \"API Description\",\n" +
                        "  \"category\": \"Home\"\n" +
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

                        .body(payload)

                        .post(
                                "https://practice.expandtesting.com/notes/api/notes"
                        );

        response.then().log().all();

        noteId =
                response.jsonPath()
                        .getString("data.id");

        System.out.println(
                "\nCreated Note ID: " + noteId
        );
    }

    public static void getNotes() {

        System.out.println(
                "\n===== FETCHING NOTES ====="
        );

        response =
                given()

                        .log().all()

                        .header(
                                "x-auth-token",
                                token
                        )

                        .get(
                                "https://practice.expandtesting.com/notes/api/notes"
                        );

        response.then().log().all();
    }

    public static void deleteNote() {

        System.out.println(
                "\n===== DELETING NOTE ID: "
                        + noteId
                        + " ====="
        );

        response =
                given()

                        .log().all()

                        .header(
                                "x-auth-token",
                                token
                        )

                        .delete(
                                "https://practice.expandtesting.com/notes/api/notes/"
                                        + noteId
                        );

        response.then().log().all();
    }

    public static void updateNote() {

        System.out.println(
                "\n===== UPDATING NOTE ====="
        );

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

                        .body(payload)

                        .put(
                                "https://practice.expandtesting.com/notes/api/notes/"
                                        + noteId
                        );

        response.then().log().all();
    }
}