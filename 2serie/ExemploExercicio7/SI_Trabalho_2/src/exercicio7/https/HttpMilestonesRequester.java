package exercicio7.https;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import exercicio7.app.WebApp;
import exercicio7.entities.GitHubMilestones;
import exercicio7.utils.JsonGeneration;

import java.io.IOException;
import java.util.LinkedList;

public class HttpMilestonesRequester {
    JsonParser jsonParser = new JsonParser();

    /**
     *  Obtains all the users milestones from Github
     * @return User Milestones
     * @throws IOException caused by a wrong Get request
     */
    public static GitHubMilestones[] getMilestonesFromAuthenticatedGitUser() throws IOException {
            String urlString = "https://api.github.com/user/repos?access_token="+ WebApp.githubToken.getToken()+"&state=all&filter=all";
        String gitHubResponse = HttpRequests.sendGet(urlString, null, null);
        LinkedList<GitHubMilestones> miles = new LinkedList<>();
        JsonArray milestonesArray = JsonGeneration.StringToJsonArray(gitHubResponse);
        for(JsonElement milestone : milestonesArray)
        {
            String url = milestone.getAsJsonObject().get("url").toString().replace("\"", "")+ "/milestones?access_token="+ WebApp.githubToken.getToken()+"&state=all&filter=all";
            String milestonesResp;
                try {
                    milestonesResp = HttpRequests.sendGet(url, null, null);
                } catch (IOException e) {//Ignores the failed request.
                    continue;
                }
                for(JsonElement m : JsonGeneration.StringToJsonArray(milestonesResp))
                {
                    miles.add(new GitHubMilestones(m.getAsJsonObject()));
                }
        }
        return miles.toArray(new  GitHubMilestones[miles.size()]);

    }
}
