package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class IfIssueFixedTests extends TestBase{
    private int myId;

    @BeforeMethod
    public void getIssueId() throws IOException {
        Set<Issue> Issues = getIssues();
        Issue myissue1 = Issues.iterator().next();
        myId = myissue1.getId();
    }


    @Test
    public void testGetStatusIssues() throws IOException {
        Set<Issue> Issues = getIssues();
        for (Issue myissue : Issues) {
            int issueid = myissue.getId();
            String state = getIssueState(issueid);
            System.out.println("Status of the issue " + issueid + " = " + state);
        }
    }

    @Test()
    private void testGetSkipIssues() throws IOException {
        System.out.println("Id = " + myId);
        skipIfNotFixed(myId);
    }

    @Test
    public void testNotFixedIssue() throws IOException {
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        skipIfNotFixed(issueId);
    }

    private String getIssueState(int issueid) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%d.json", issueid))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonArray issues1 = issues.getAsJsonArray();
        String state_name = null;

        for (JsonElement issue1 : issues1) {
            state_name = issue1.getAsJsonObject().get("state_name").getAsString();
        }

        return state_name;
    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json?limit=500").bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()), new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private boolean fixIssue(Issue fixedIssue) throws IOException {
        int issueId1 = fixedIssue.getId();
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues" + issueId1 + ".json").bodyForm(new BasicNameValuePair("method", "update"), new BasicNameValuePair("issue%5Bstate%5D", "2"))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        int state = parsed.getAsJsonObject().get("state").getAsInt();
        if (state == 2) {
            return true;
        } else {
            return false;
        }
    }
}
