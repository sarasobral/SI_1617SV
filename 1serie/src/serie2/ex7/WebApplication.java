package code.serie2.ex7;

import code.serie2.ex7.https.AccessToken;
import code.serie2.ex7.servlets.HttpsServer;
/**
 * Created by Utilizador on 05/12/2016.
 */
public class WebApplication {
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
