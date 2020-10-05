import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        InputStream file = null;
        try {
            file = new FileInputStream(new File("src/main/resources/config.yaml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        Map<String,Object> result = (Map<String,Object>)yaml.load(file);

        System.out.println(result.toString());


    }
}
















//        JSONObject newIssueJSON = new JSONObject();
//        JSONObject fields = new JSONObject();
//        fields.put("summary", "Test Summary");
//        JSONObject issueType = new JSONObject();
//        issueType.put("id", "10107");
//        JSONObject project = new JSONObject();
//        project.put("id", "11400");
//        JSONObject assignee = new JSONObject();
//        assignee.put("name", "webinar5");
//
//        fields.put("issuetype", issueType);
//        fields.put("project", project);
//        fields.put("assignee", assignee);
//
//        newIssueJSON.put("fields", fields);
//        System.out.println(newIssueJSON.toJSONString());
