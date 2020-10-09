package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostApi {
  static String newIssuesJSON = JIRAJSONObject.newIssuesJSON();
  public Response createIssues() {


    Response response =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            body(newIssuesJSON).
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
