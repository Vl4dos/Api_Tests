package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteApi {
  public Response  deleteIssues (String ticketId){
    Response response =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            when().
            delete("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
            then().
            statusCode(204).
            extract().response();
    response.print();
    return response;
  }
}
