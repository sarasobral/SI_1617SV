package exercicio6;

import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Scanner;

public class Program {

    public static void main(String[]hostname){
        try {
            Scanner scn = new Scanner(System.in);
            for (int i = 0; i < hostname.length; i++){
                HttpsURLConnection urlconn=Utils.getConnection(hostname[i]);
                urlconn.connect();
                Certificate[] certificates= urlconn.getServerCertificates();
                X509Certificate[] cert = Utils.createX509Certificates(certificates);
                Utils.printCertificates(cert);
                urlconn.disconnect();

            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

}


