package code.serie2.ex7.servlets.github;

import code.serie2.ex7.WebApplication;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GithubTokenServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(302);
        resp.setHeader("Location","https://api.github.com/user/issues?"
                +"access_token="+ WebApplication.githubToken.getToken()+
                "&state=all"+
                "&filter=all");
    }
}

