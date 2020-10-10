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
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            body(newIssuesJSON).
            when().
            post(APIPathes.issue).
            then().
            contentType(ContentType.JSON).
            statusCode(201).
            extract().response();
    response.print();
    String ticketId = response.path("id");
    return response;
  }

  public Response newComment(String ticketId) {
    Response addCommentResponse =
        given().
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            body(newCommentJSON)
            .when().
            post(String.format(APIPathes.comment, ticketId)).
            then().
            contentType(ContentType.JSON).
            statusCode(201).
            time(lessThan(1L), TimeUnit.SECONDS).
            extract().response();
    addCommentResponse.print();
    return addCommentResponse;
  }
}
