package exercicio6;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Utils {


    public static HttpsURLConnection getConnection(String urlname) {
        try {
            URL myURL = new URL(urlname);
            HttpsURLConnection myURLConnection = (HttpsURLConnection) myURL.openConnection();
            myURLConnection.connect();
            return myURLConnection;
        } catch (MalformedURLException e){
            System.out.println("Error: "+ e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
        return null;
    }

    public static X509Certificate[] createX509Certificates(Certificate[] certificates) {
        X509Certificate[] x509Certificate = new X509Certificate[certificates.length];
        for (int i = 0, j=0; i <certificates.length ; i++) {
            if (certificates[i] instanceof X509Certificate) {
                x509Certificate[j]=(X509Certificate) certificates[i];
                ++j;
            }
        }
        return x509Certificate;
    }

    public static void printCertificates(X509Certificate[] certificates) {
        int i = 0;
        for (X509Certificate c : certificates) {
            System.out.println("+----------------------------------------------------------------------------------------------+");
            System.out.println("Certificate : " + (i++));
            System.out.println("Issuer : " + c.getIssuerDN().getName() + "\nSerial Number:" + c.getSerialNumber());
            System.out.println("Version : " + c.getVersion());
            System.out.println("Date Expiration : " + earliestExpirationCertificate(c)+ "\n");
            printHostNameValidity(c);
        }
    }

    public static Date earliestExpirationCertificate(X509Certificate c) {
        X509Certificate earliestExpirationDateCert = null;
        if (earliestExpirationDateCert == null)
            earliestExpirationDateCert = c;
        else {
            if (earliestExpirationDateCert.getNotAfter().after(c.getNotAfter()))
                earliestExpirationDateCert = c;
        }

        return earliestExpirationDateCert.getNotAfter();
    }

    public static void printHostNameValidity(X509Certificate cert) {

        try {
            Collection<List<?>> alternativeNames = cert.getSubjectAlternativeNames();
            if(alternativeNames!= null) {
                System.out.println("Names for which this certificate is valid: ");
                for (List<?> s : alternativeNames) {
                    System.out.println(s.get(1).toString());
                }
                System.out.println();
            }

        } catch (CertificateParsingException e) {
            System.out.println("Error parsing certificate.");
        }
    }
}