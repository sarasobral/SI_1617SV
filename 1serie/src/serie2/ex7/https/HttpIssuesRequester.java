package code.serie2.ex7.https;

import code.serie2.ex7.entities.GitHubIssues;
import code.serie2.ex7.utils.HttpRequests;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import code.serie2.ex7.WebApplication;
import code.serie2.ex7.utils.JsonGeneration;

import java.io.IOException;
import java.util.LinkedList;

public class HttpIssuesRequester {
    JsonParser jsonParser = new JsonParser();

    /**
     *  Obtains all the users milestones from Github
     * @return User Milestones
     * @throws IOException caused by a wrong Get request
     */
    public static GitHubIssues[] getIssuesFromAuthenticatedGitUser() throws IOException {
            String urlString = "https://api.github.com/user/repos?access_token="+
                    WebApplication.githubToken.getToken()+"&state=all&filter=all";
        String gitHubResponse = HttpRequests.sendGet(urlString, null, null);
        LinkedList<GitHubIssues> issiesList = new LinkedList<>();
        JsonArray issuesArray = JsonGeneration.StringToJsonArray(gitHubResponse);
        for(JsonElement issue : issuesArray) {
            String url = issue.getAsJsonObject().get(
                    "url").toString().replace("\"", "")+
                    "/issues?access_token="+ WebApplication.githubToken.getToken()+
                    "&state=all&filter=all";
            String issuesResp;
                try {
                    issuesResp = HttpRequests.sendGet(url, null, null);
                } catch (IOException e) {//Ignores the failed request.
                    continue;
                }
                for(JsonElement m : JsonGeneration.StringToJsonArray(issuesResp)) {
                    issiesList.add(new GitHubIssues(m.getAsJsonObject()));
                }
        }
        return issiesList.toArray(new GitHubIssues[issiesList.size()]);

    }
}
