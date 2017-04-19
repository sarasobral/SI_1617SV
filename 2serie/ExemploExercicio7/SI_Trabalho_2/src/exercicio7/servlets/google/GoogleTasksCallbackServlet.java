package exercicio7.servlets.google;

import exercicio7.app.WebApp;
import exercicio7.https.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleTasksCallbackServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(WebApp.googleToken == null)WebApp.googleToken = AccessToken.getGoogleTasksAccessToken(req.getParameter("code"));
        resp.setStatus(200);
        resp.sendRedirect("http://localhost:8080/githubmilestones");
    }
}
