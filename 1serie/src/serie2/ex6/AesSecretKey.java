package code.serie2.ex6;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AesSecretKey {
    public static final int SIZE = 128; //128 192 256
    public static final String ALGORITHM_KEY = "AES";
    public static final String ALGORITHM_CIPHER = "GCM";
    public static final String ALGORITHM = ALGORITHM_KEY+"/"+ALGORITHM_CIPHER+"/NoPadding";

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        // Generate key
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM_KEY);
        kgen.init(SIZE);
        SecretKey aesKey = kgen.generateKey();
        return aesKey;
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