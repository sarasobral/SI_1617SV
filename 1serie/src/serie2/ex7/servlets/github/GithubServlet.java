package code.serie2.ex7.servlets.github;

import code.serie2.ex7.WebApplication;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GithubServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(WebApplication.githubToken != null) resp.sendRedirect("http://localhost:8080/githubcallback");
        resp.setStatus(302);
        resp.setHeader("Location", "https://github.com/login/oauth/authorize?" +
                "client_id=" + GithubData.CLIENT_ID +
                "&redirect_uri=http://localhost:8080/githubcallback" +
                "&scope=user:email,repo,read:org"+
                "&response_type=code");
    }
}

