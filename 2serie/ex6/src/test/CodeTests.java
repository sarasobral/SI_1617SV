package test;

import org.junit.Assert;
import org.junit.Test;
import code.*;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;

public class CodeTests {

    @Test
    public void easSecretKey() throws Exception {
        String plainTextMessage = "Hello group 6.";
        byte[] plainText = plainTextMessage.getBytes();
        SecretKey aesKey = AesSecretKey.generateKey();
        byte[] iv = new byte[12];
        new Random().nextBytes(iv);
        byte[] encryptedBytes = AesSecretKey.encript(plainText, aesKey, iv);
        byte[] decipheredBytes = AesSecretKey.decrypt(encryptedBytes, aesKey, iv);
       Assert.assertTrue(new String(plainText, "UTF-8").equals(new String(decipheredBytes, "UTF-8")));
    }

    @Test
    public void rsaKey() throws Exception {
        KeyPair rsaKeyPair = RsaKey.generateKeys();
        SecretKey aesKey = AesSecretKey.generateKey();
        byte[] encryptedBytes = RsaKey.encript(rsaKeyPair.getPublic(), aesKey);
        SecretKey decipheredKey = RsaKey.decrypt(rsaKeyPair.getPrivate(), encryptedBytes);
        Assert.assertTrue(aesKey.equals(decipheredKey));
    }

    @Test
    public void getPublicKey() throws Exception {
        PublicKey pk = GetPublicKey.start("SI_1617i_LI51N_SE1/CA1-int.cer");
        Assert.assertNotEquals(pk, null);
    }

    @Test
    public void getPrivateKey() throws Exception {
        char[] password = "changeit".toCharArray();
        String keystoreFile = "SI_1617i_LI51N_SE1/pfx/Bob_1.pfx";
        String keyStoreType = "PKCS12";
        KeyStore keystore = GetPrivateKey.createKeyStore(keyStoreType, password, keystoreFile);
        PrivateKey pk = GetPrivateKey.getPrivateKey(keystore, password);
        GetPrivateKey.saveKeyStore("myKeyStore.jks", keystore, password);
        Assert.assertNotEquals(pk, null);

    }


}
