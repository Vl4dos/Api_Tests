package pages;

import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import org.json.simple.JSONObject;

public class JIRAJSONObject {
  public static String newIssuesJSON() {

    JSONObject newIssuesJSON = new JSONObject();
    JSONObject fields = new JSONObject();
    JSONObject reporter = new JSONObject();

    JSONObject issuetype = new JSONObject();
    issuetype.put("id", "10105");
    issuetype.put("name", "Task");
    reporter.put("name", "VladKryvenko");
    JSONObject project = new JSONObject();
    project.put("id", "10508");

    fields.put("summary", "New Bug");
    fields.put("issuetype", issuetype);
    fields.put("reporter", reporter);
    fields.put("project", project);
    newIssuesJSON.put("fields", fields);
    return newIssuesJSON.toJSONString();
  }

  public static String commentJSON(){
    JSONObject commentJSON = new JSONObject();
    commentJSON.put("body","new Comment");
    return commentJSON.toJSONString();
  }

}
