package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteApi {
  public Response  deleteIssues (String ticketId){
    Response response =
        given().
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            when().
            delete(APIPathes.issue + ticketId).
            then().
            statusCode(204).
            extract().response();
    response.print();
    return response;
  }
  public Response deleteComment(String ticketId,String ticketIdComment){
    Response deleteCommentResponse =
        given().
            auth().preemptive().basic(Credentials.username, Credentials.password).
            contentType(ContentType.JSON).
            when().
            delete(String.format(APIPathes.commentDelete, ticketId,ticketIdComment)).
            then().
            statusCode(204).
            extract().response();
    deleteCommentResponse.print();
    return deleteCommentResponse;
  }
}
