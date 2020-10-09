package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PostApi {
  static String newIssuesJSON = JIRAJSONObject.newIssuesJSON();
static String newCommentJSON = JIRAJSONObject.commentJSON();
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
  public Response newComment(String ticketId){
    Response addCommentResponse =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            body(newCommentJSON)
            .when().
            post("https://jira.hillel.it/rest/api/2/issue/" + ticketId + "/comment/").
            then().
            contentType(ContentType.JSON).
            statusCode(201).
            time(lessThan(4L), TimeUnit.SECONDS).
            extract().response();
    addCommentResponse.print();
    return addCommentResponse;
  }
}
