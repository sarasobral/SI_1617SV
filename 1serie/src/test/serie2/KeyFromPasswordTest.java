package test.serie2;

import code.serie1.KeyFromPassword;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.util.Random;

/**
 * Created by Utilizador on 01/12/2016.
 */
public class KeyFromPasswordTest {

    @Test
    public void easSecretKey() throws Exception {
        String palaraPasse = "password";
        char[] password = palaraPasse.toCharArray();

        byte[] salt = new byte[4];
        new Random().nextBytes(salt);; //4
        String plainTextMessage = "Hello group 6.";
        byte[] plainText = plainTextMessage.getBytes();
        SecretKey aesKey = KeyFromPassword.generateKey(password, salt);
        byte[] iv = new byte[12];
        new Random().nextBytes(iv);
        byte[] encryptedBytes = KeyFromPassword.encript(plainText, aesKey, iv);
        byte[] decipheredBytes = KeyFromPassword.decrypt(encryptedBytes, aesKey, iv);
        Assert.assertTrue(new String(plainText, "UTF-8").equals(new String(decipheredBytes, "UTF-8")));

    }

    


}
