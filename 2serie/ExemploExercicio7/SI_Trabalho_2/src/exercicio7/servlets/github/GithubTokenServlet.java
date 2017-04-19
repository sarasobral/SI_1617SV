package exercicio7.servlets.github;

import exercicio7.app.WebApp;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GithubTokenServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(302);
        resp.setHeader("Location","https://api.github.com/user/issues?"
                +"access_token="+ WebApp.githubToken.getToken()+
                "&state=all"+
                "&filter=all");
    }
}

