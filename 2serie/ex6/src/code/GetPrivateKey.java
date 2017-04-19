package code;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.KeySpec;
import java.util.Enumeration;

/**
 * Created by Utilizador on 03/11/2016.
 */
public class GetPrivateKey {

    public static KeyStore createKeyStore(String keyStoreType, char[] password, String pfxFile) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keystore = KeyStore.getInstance(keyStoreType);
        keystore.load(new FileInputStream(pfxFile), password);
        return keystore;
    }

    public static PrivateKey getPrivateKey(KeyStore keystore, char[] password) throws KeyStoreException, UnrecoverableEntryException, NoSuchAlgorithmException {
        Enumeration<String> aliases = keystore.aliases();
        String alias;
        KeyStore.ProtectionParameter protParam =  new KeyStore.PasswordProtection(password);

        while (aliases.hasMoreElements()){
            alias = aliases.nextElement();

            KeyStore.Entry entry = (KeyStore.Entry) keystore.getEntry(alias, protParam);

            Key key=keystore.getKey(alias,password);
            KeyPair keyPair = null;
            if(key instanceof PrivateKey) {
                Certificate cert=keystore.getCertificate(alias);
                PublicKey publicKey=cert.getPublicKey();
                keyPair = new KeyPair(publicKey,(PrivateKey)key);
            }
            return keyPair.getPrivate();
        }
        return null;
    }

    public static void saveKeyStore(String fileName, KeyStore keystore, char[] password) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        // store the keystore
        java.io.FileOutputStream fos = null;
        try {
            fos = new java.io.FileOutputStream(fileName); //alias+".key"
            keystore.store(fos, password);
        } finally {
            if (fos != null)
                fos.close();
        }
    }


}

