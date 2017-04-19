package exercicio7.app;

import exercicio7.servlets.HttpsServer;
import exercicio7.https.AccessToken;

public class WebApp {

    public static volatile AccessToken githubToken, googleToken;
    public static void main(String[] args){
        HttpsServer server = new HttpsServer();
        System.setProperty("java.net.useSystemProxies", "true");
        try {
            server.start();
            System.out.println("Server is started");
            while(googleToken==null);
            System.in.read();
            server.stop();
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
        System.out.println("Server is stopped");
    }

}
