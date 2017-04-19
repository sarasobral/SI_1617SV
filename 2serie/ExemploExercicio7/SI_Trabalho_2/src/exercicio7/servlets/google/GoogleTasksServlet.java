package exercicio7.servlets.google;


import exercicio7.app.WebApp;
import exercicio7.https.HttpRequests;
import exercicio7.utils.JsonGeneration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoogleTasksServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(WebApp.googleToken != null)resp.sendRedirect("http://localhost:8080/gtaskscallback");
        String getConfig = HttpRequests.sendGet("https://accounts.google.com/.well-known/openid-configuration",null,null);
        String url = JsonGeneration.StringToJsonObject(getConfig).get("authorization_endpoint").getAsString().replace("\"","");

        resp.setStatus(302);
        resp.setHeader("Location",url+"?"+
                "client_id="+GoogleData.CLIENT_ID+
                "&redirect_uri=http://localhost:8080/gtaskscallback"+
                "&scope=openid%20https://www.googleapis.com/auth/calendar"+
                "&response_type=code"+
                "&state=dummyName");

    }
}
