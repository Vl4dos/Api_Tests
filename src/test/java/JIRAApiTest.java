import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DeleteApi;
import pages.GetApi;
import pages.JiraAPISteps;
import pages.PostApi;

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


//    Response getIssueResponse = new GetApi().getIssues(ticketId);
//    assertEquals(getIssueResponse.path("fields.summary"), "Main order flow broken");
//    assertEquals(getIssueResponse.path("fields.reporter.name"), "VladKryvenko");
//
//    Response deleteIssueResponse = new DeleteApi().deleteIssues(ticketId);
//
//   Response checkIfIssuesDeleteResponse = new GetApi().getDeleteIssues(ticketId);


  }


  @Test
  public void comment() {

    Response createIssueResponse = new PostApi().createIssues();
    String ticketId = createIssueResponse.path("id");
    assertTrue(createIssueResponse.path("key").toString().contains("WEBINAR-"));


    Response addCommentResponse = new PostApi().newComment(ticketId);
    String ticketIdComment = addCommentResponse.path("id");
    String commentURL = addCommentResponse.path("self");

    Response deleteCommentResponse = new DeleteApi().deleteComment(ticketId,ticketIdComment);

    Response getDeleteCommentResponse = new GetApi().getDeleteComment(ticketId,ticketIdComment);

    Response getIssueResponse = new GetApi().getIssues(ticketId);
    Assert.assertFalse(getIssueResponse.toString().contains(commentURL));

    Response deleteIssueResponse = new DeleteApi().deleteIssues(ticketId);

    Response checkIfIssuesDeleteResponse = new GetApi().getDeleteIssues(ticketId);
  }

}
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));







