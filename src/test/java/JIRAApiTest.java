import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.Matcher;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;
import pages.DeleteApi;
import pages.GetApi;
import pages.PostApi;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JIRAApiTest {

  //  @Test
//  public void getExistingIssue() {
//
//    Response response = given().
//        auth().preemptive().basic("webinar5", "webinar5").
//        when().
//        get("http://jira.hillel.it/rest/api/2/issue/WEBINAR-9060").
//        then().
//        contentType(ContentType.JSON).
//        statusCode(200).
//        extract().response();
//
////        Cookies coockies = response.getDetailedCookies();
//
////    assertEquals(response.statusCode(), 200);
//    assertEquals("WEBINAR-9060", response.path("key"));
//  }
  @Test
  public void createIssuesRaw() {

Response createIssueResponse = new PostApi().createIssues();
    String ticketId = createIssueResponse.path("id");

    //Get issues
    Response getIssueResponse =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            when().
            get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
            then().
            contentType(ContentType.JSON).
            statusCode(200).
            extract().response();
    getIssueResponse.print();
    assertEquals(getIssueResponse.path("fields.summary"), "Main order flow broken");
    assertEquals(getIssueResponse.path("fields.reporter.name"), "VladKryvenko");

//Delete issues

    Response deleteIssuesResponse =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            when().
            delete("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
            then().
    statusCode(204).
            extract().response();
    deleteIssuesResponse.print();

    //     get Delete issues

    Response checkIfIssuesDeleteResponse =
        given().
            auth().preemptive().basic("VladKryvenko", "VladKryvenko").
            contentType(ContentType.JSON).
            when().
            get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
            then().
            statusCode(404).
            extract().response();
  }


  @Test
  public void createIssue() {
    Response response = JiraAPISteps.createIssue();
    assertEquals(response.statusCode(),201);
    response.then().extract().path("key");
  }
}
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));


//Response getIssueResponse = new GetApi().getIssues(ticketId);
//    assertEquals(getIssueResponse.path("fields.summary"), "Main order flow broken");
//    assertEquals(getIssueResponse.path("fields.reporter.name"), "VladKryvenko");

//    Response deleteIssueResponse = new DeleteApi().deleteIssues(ticketId);

//    Response checkIfIssuesDeleteResponse = new GetApi().getDeleteIssues(ticketId);
