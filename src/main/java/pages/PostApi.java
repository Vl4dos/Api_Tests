package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostApi {
  public Response createIssues (){

    Response response =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            body("{\n" +
                " \"fields\": {\n" +
                "    \"summary\": \"Main order flow broken\",\n" +
                "    \"issuetype\": {\n" +
                "      \"id\": \"10105\",\n" +
                "      \"name\":\"Task\"\n" +
                "    },\n" +
                "   \"project\": {\n" +
                "      \"id\": \"10508\"\n" +
                "    },\n" +
                "    \"reporter\": {\n" +
                "      \"name\": \"VladKryvenko\"\n" +
                "    }\n" +
                " }\n" +
                "}").
            when().
            post("https://jira.hillel.it/rest/api/2/issue").
            then().
            contentType(ContentType.JSON).
            statusCode(201).
            extract().response();
    response.print();
     String ticketId = response.path("id");
return response;
  }
}
