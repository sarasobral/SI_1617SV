package code.serie2.ex7.servlets.github;

import code.serie2.ex7.WebApplication;
import code.serie2.ex7.entities.GitHubIssues;
import code.serie2.ex7.https.*;
import code.serie2.ex7.utils.JsonGeneration;
import code.serie2.ex7.utils.IssuesToTaskListConverter;
import javax.servlet.http.*;
import java.io.IOException;

public class GithubCallbackServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(WebApplication.githubToken == null) WebApplication.githubToken = AccessToken.getGitHubAccessToken(req.getParameter("code"));
        resp.setStatus(200);
        HttpIssuesRequester issuesRequester = new HttpIssuesRequester();
        GitHubIssues[] issues = issuesRequester.getIssuesFromAuthenticatedGitUser();
        //todo: url
        String urlString = "https://www.googleapis.com?access_token="+ WebApplication.googleToken.getToken()+"&state=all&filter=all";
        String getResponse = HttpRequests.sendGet(urlString, null, null);
        String id = JsonGeneration.StringToJsonObject(getResponse).get("items").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
        HttpTaskPoster.post(IssuesToTaskListConverter.convertList(issues),id);
        //todo
        resp.sendRedirect("https://calendar.google.com");
    }
}
