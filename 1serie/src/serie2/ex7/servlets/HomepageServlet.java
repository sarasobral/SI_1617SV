package code.serie2.ex7.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HomepageServlet extends HttpServlet {

    public static final String RESOURCES_HOMEPAGE_HTML = "code/serie2/ex7/resources/homepage.html";
    public static final String RESOURCES = "code/serie2/ex7/resources/";
    private Map<String,String> resources = new HashMap<>();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        String content, requestPath = "resources" + URLDecoder.decode(req.getRequestURI(), "UTF-8");
        if (requestPath.equals(RESOURCES)) {
            requestPath = RESOURCES_HOMEPAGE_HTML;
        }
        if (resources.containsKey(requestPath)) {
            content = resources.get(requestPath);
        }
        else {
            StringBuffer homePageContent = new StringBuffer();
            InputStream inputStream = HomepageServlet.class.getClassLoader().getResourceAsStream(requestPath);
            if (inputStream == null)
                inputStream = HomepageServlet.class.getClassLoader().getResourceAsStream(RESOURCES_HOMEPAGE_HTML);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
                String aux = br.readLine();
                while (aux != null){
                    homePageContent.append(aux);
                    aux = br.readLine();
                }
            }
            content = homePageContent.toString();
            resources.put(requestPath, content);
        }
        DataOutputStream wr = new DataOutputStream(resp.getOutputStream());
        wr.writeBytes(content.toString());
        wr.flush();
        wr.close();
        resp.setContentLength(content.length());
        resp.setStatus(200);
    }
}
