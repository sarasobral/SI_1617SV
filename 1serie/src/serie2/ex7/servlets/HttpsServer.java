package code.serie2.ex7.servlets;

import code.serie2.ex7.servlets.github.*;
import code.serie2.ex7.servlets.google.GoogleTasksCallbackServlet;
import code.serie2.ex7.servlets.google.GoogleTasksServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Created by Utilizador on 05/12/2016.
 */
public class HttpsServer {
    public static final int LISTEN_PORT = 8080;
    private Server server;
    private ServletHandler handler;

    public HttpsServer(){
        server = new Server(LISTEN_PORT);
        handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(HomepageServlet.class, "/");
        handler.addServletWithMapping(HomepageServlet.class, "/login");
        handler.addServletWithMapping(ShowGitHubServlet.class, "/showgithubissues");
        handler.addServletWithMapping(ShowGitHubCallbackServlet.class, "/githubcallback/show");
        handler.addServletWithMapping(GoogleTasksServlet.class, "/importissues"); //
        handler.addServletWithMapping(GoogleTasksServlet.class, "/gtasks");
        handler.addServletWithMapping(GoogleTasksCallbackServlet.class, "/gtaskscallback");
        handler.addServletWithMapping(GithubServlet.class, "/githubissues");
        handler.addServletWithMapping(GithubCallbackServlet.class, "/githubcallback");
        handler.addServletWithMapping(GithubTokenServlet.class, "/githubtoken");
    }

    /**
     * Begin server
     */
    public void start()  {
        try {
            server.start();
        } catch (Exception e) {
            System.out.println("ERROR: start");
        }
    }
    /**
     * Stop server
     */
    public void stop(){
        try {
            server.stop();
        } catch (Exception e) {
            System.out.println("ERROR: stop");
        }
    }
}
