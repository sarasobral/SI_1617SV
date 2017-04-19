package exercicio7.servlets.github;

import exercicio7.app.WebApp;
import exercicio7.entities.GitHubMilestones;
import exercicio7.https.AccessToken;
import exercicio7.https.HttpMilestonesRequester;

import javax.servlet.http.*;
import java.io.IOException;

public class ShowGitHubCallbackServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(WebApp.githubToken == null)WebApp.githubToken = AccessToken.getGitHubAccessToken(req.getParameter("code"));
        HttpMilestonesRequester issuesRequester = new HttpMilestonesRequester();
        GitHubMilestones[] issues = issuesRequester.getMilestonesFromAuthenticatedGitUser();
            for (GitHubMilestones issue : issues) {
                System.out.println("+---------------------------------------------------------");
                System.out.println(issue);
            }
        resp.setStatus(200);
        resp.sendRedirect("http://localhost:8080/");
    }
}
