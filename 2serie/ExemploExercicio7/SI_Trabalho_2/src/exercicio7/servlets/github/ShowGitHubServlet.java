package exercicio7.servlets.github;

import exercicio7.app.WebApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowGitHubServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(WebApp.githubToken != null) resp.sendRedirect("http://localhost:8080/githubcallback/show");
        resp.setStatus(302);
        resp.setHeader("Location", "https://github.com/login/oauth/authorize?" +
                "client_id=" + GithubData.CLIENT_ID +
                "&redirect_uri=http://localhost:8080/githubcallback/show" +
                "&scope=user:email,repo,read:org"+
                "&response_type=code");
    }
}


