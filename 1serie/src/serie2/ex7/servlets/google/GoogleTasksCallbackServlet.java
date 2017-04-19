package code.serie2.ex7.servlets.google;

import code.serie2.ex7.WebApplication;
import code.serie2.ex7.https.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleTasksCallbackServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(WebApplication.googleToken == null) WebApplication.googleToken = AccessToken.getGoogleTasksAccessToken(req.getParameter("code"));
        resp.setStatus(200);
        resp.sendRedirect("http://localhost:8080/githubissues");
    }
}
