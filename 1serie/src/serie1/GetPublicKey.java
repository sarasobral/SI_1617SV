package code.serie1;

import java.io.*;
import java.security.cert.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * Created by Utilizador on 02/11/2016.
 */
public class GetPublicKey {

    public static PublicKey start(String certeficatePath) throws Exception {
        PublicKey pk = null;
        FileInputStream fin = new FileInputStream(certeficatePath);
        CertificateFactory f = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) f.generateCertificate(fin);
        if(verifyCerteficate(certificate)){
            pk = getPublicKey(certificate);
            System.out.println();
            //System.out.println(pk);
        }
        return pk;
    }

    public static boolean verifyCerteficate(Certificate certificate) throws CertificateNotYetValidException {
        //System.out.println("Certificate is: " + certificate);
        if(certificate instanceof X509Certificate) {
            try {
                ( (X509Certificate) certificate).checkValidity();
                //System.out.println("Certificate is active for current date");
            } catch(CertificateExpiredException cee) {
                //System.out.println("Certificate is expired");
                return false;
            }
        }
        return true;
    }

    public static PublicKey getPublicKey(Certificate certificate) {
        return certificate.getPublicKey();
    }

}
