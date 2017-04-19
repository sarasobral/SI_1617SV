package exercicio7.servlets;

import exercicio7.servlets.github.*;
import exercicio7.servlets.google.GoogleTasksCallbackServlet;
import exercicio7.servlets.google.GoogleTasksServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class HttpsServer {

    public static final int LISTEN_PORT = 8080;
    private Server server;
    private ServletHandler handler;

    public HttpsServer(){
        server = new Server(LISTEN_PORT);
        handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(HomepageServlet.class, "/");
        handler.addServletWithMapping(ShowGitHubServlet.class, "/showgithubmilestones");
        handler.addServletWithMapping(ShowGitHubCallbackServlet.class, "/githubcallback/show");
        handler.addServletWithMapping(GoogleTasksServlet.class, "/importmilestones");
        handler.addServletWithMapping(GoogleTasksServlet.class, "/gtasks");
        handler.addServletWithMapping(GoogleTasksCallbackServlet.class, "/gtaskscallback");
        handler.addServletWithMapping(GithubServlet.class, "/githubmilestones");
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
