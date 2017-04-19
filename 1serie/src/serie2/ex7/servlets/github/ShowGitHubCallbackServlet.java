package code.serie2.ex7.servlets.github;

import code.serie2.ex7.WebApplication;
import code.serie2.ex7.entities.GitHubIssues;
import code.serie2.ex7.https.AccessToken;
import code.serie2.ex7.https.HttpIssuesRequester;

import javax.servlet.http.*;
import java.io.IOException;

public class ShowGitHubCallbackServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(WebApplication.githubToken == null)
            WebApplication.githubToken = AccessToken.getGitHubAccessToken(req.getParameter("code"));
        HttpIssuesRequester issuesRequester = new HttpIssuesRequester();
        GitHubIssues[] issues = issuesRequester.getIssuesFromAuthenticatedGitUser();
            for (GitHubIssues issue : issues) {
                System.out.println("+---------------------------------------------------------");
                System.out.println(issue);
            }
        resp.setStatus(200);
        resp.sendRedirect("http://localhost:8080/");
    }
}
