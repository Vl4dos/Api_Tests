package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetApi {

  public Response getIssues(String ticketId) {
    Response response =
        given().
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            when().
            get(APIPathes.issue + ticketId).
            then().
            contentType(ContentType.JSON).
            statusCode(200).
            extract().response();
    return response;
  }

  public Response getDeleteIssues(String ticketId) {
    Response response =
        given().
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            when().
            get(APIPathes.issue + ticketId).
            then().
            statusCode(404).
            extract().response();
    return response;
  }
  public Response getDeleteComment (String ticketId ,String ticketIdComment ){
    Response getDeleteCommentResponse =
        given().
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            when().
            get(String.format(APIPathes.commentDelete, ticketId,ticketIdComment)).
            then().
            statusCode(404).
            extract().response();
    return getDeleteCommentResponse;
  }
}
