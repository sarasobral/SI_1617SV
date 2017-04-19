package exercicio7.servlets.github;

import exercicio7.app.WebApp;
import exercicio7.entities.GitHubMilestones;
import exercicio7.https.*;
import exercicio7.utils.JsonGeneration;
import exercicio7.utils.MilestonesToTaskListConverter;
import javax.servlet.http.*;
import java.io.IOException;

public class GithubCallbackServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(WebApp.githubToken == null)WebApp.githubToken = AccessToken.getGitHubAccessToken(req.getParameter("code"));
        resp.setStatus(200);
        HttpMilestonesRequester issuesRequester = new HttpMilestonesRequester();
        GitHubMilestones[] issues = issuesRequester.getMilestonesFromAuthenticatedGitUser();
        String urlString = "https://www.googleapis.com/calendar/v3/users/me/calendarList?access_token="+ WebApp.googleToken.getToken()+"&state=all&filter=all";
        String getResponse = HttpRequests.sendGet(urlString, null, null);
        String id = JsonGeneration.StringToJsonObject(getResponse).get("items").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
        HttpCalenderPoster.post(MilestonesToTaskListConverter.convertList(issues),id);
        resp.sendRedirect("https://calendar.google.com/calendar");
    }
}
