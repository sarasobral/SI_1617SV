package code;

import javax.crypto.*;
import java.io.IOException;
import java.security.*;

/**
 * Created by Utilizador on 27/10/2016.
 */
public class RsaKey {
    private static final int SIZE = 512;
    public static final String ALGORITHM = "RSA";


    public static KeyPair generateKeys() throws NoSuchAlgorithmException {
        // generate public and private keys
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(SIZE);
        return keyPairGenerator.genKeyPair(); //contain the public and private keys
    }

    public static byte[] encript(PublicKey kPublic, SecretKey kSecret) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        // Encrypt cipher
        // Cifrar a chave com a chave pública
        Cipher encryptCipher = Cipher.getInstance (ALGORITHM);
        encryptCipher.init (Cipher.WRAP_MODE, kPublic);
        //encryptCipher.init (Cipher.ENCRYPT_MODE, kPublic);
        byte[] chaveCifrada = encryptCipher.wrap(kSecret);
        return  chaveCifrada;
    }

    public static SecretKey decrypt(PrivateKey kPrivate, byte[] chaveCifrada) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        // Decrypt cipher
        // Decifrando a chave simétrica com a chave privada
        Cipher decryptCipher = Cipher.getInstance (ALGORITHM);
        //decryptCipher.init (Cipher.DECRYPT_MODE, kPrivate);
        decryptCipher.init (Cipher.UNWRAP_MODE, kPrivate);
        SecretKey chaveDecifrada = (SecretKey) decryptCipher.unwrap(chaveCifrada,AesSecretKey.ALGORITHM_KEY,Cipher.SECRET_KEY);
        return chaveDecifrada;
    }


}
