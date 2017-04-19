package code.serie1;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class KeyFromPassword {
    public static final int SIZE = 128; //128 192 256
    public static final String ALGORITHM_KEY = "AES";
    public static final String ALGORITHM_CIPHER = "GCM";
    public static final String ALGORITHM = ALGORITHM_KEY+"/"+ALGORITHM_CIPHER+"/NoPadding";

    private static final int ROUNDS = 1024;

    public static SecretKey generateKey(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        /*// Generate key
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM_KEY);
        kgen.init(SIZE);
        SecretKey aesKey = kgen.generateKey();
        return aesKey;*/
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec ks = new PBEKeySpec(password,salt,ROUNDS, SIZE);
        SecretKey s = f.generateSecret(ks);
        Key k = new SecretKeySpec(s.getEncoded(), ALGORITHM_KEY);
        return (SecretKey) k;
    }

    public static byte[] encript(byte[] message, SecretKey aesKey, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        // Encrypt cipher
        Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec myParams = new GCMParameterSpec(SIZE, iv);
        encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey, myParams);

        byte[] encryptedBytes = encryptCipher.doFinal(message);
        return encryptedBytes;
    }

    public static byte[] decrypt(byte[] encryptedBytes, SecretKey aesKey, byte[]iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        // Decrypt cipher
        Cipher decryptCipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec myParams = new GCMParameterSpec(SIZE, iv);
        decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, myParams);

        byte[] decipheredBuff = decryptCipher.doFinal(encryptedBytes);
        return decipheredBuff;
    }
}