package code;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.util.Base64;
import java.util.Random;

/**
 * Created by Utilizador on 08/11/2016.
 */
public class Program {
    public static class Tag{
        private String alg;
        private String enc;
        public Tag(String algorithm, String encryptedBytes) {
            this.alg=algorithm;
            this.enc=encryptedBytes;
        }
        public String getTag(){
            return "{\"alg\":\""+alg+"\",\"enc\":\""+enc+"\"}";
        }
    }

    public static void main(String[] args) throws Exception {
        String plainTextMessage = "The true sign of intelligence is" +
                " not knowledge but imagination.";
        byte[] plainText = plainTextMessage.getBytes();

        /*o  Generate a random Content Encryption Key (CEK).*/
        SecretKey aesKey = AesSecretKey.generateKey();
        KeyPair rsaKeyPair = RsaKey.generateKeys();

       /*o  Encrypt the CEK with the recipient's public key using the RSAES-
        OAEP algorithm to produce the JWE Encrypted Key.*/
        byte[] encryptedKeyBytes = RsaKey.encript(rsaKeyPair.getPublic(), aesKey);

        /*o  Base64url-encode the JWE Encrypted Key.*/
        String base64EncKey = Base64.getEncoder().encodeToString(encryptedKeyBytes);

        /*o  Generate a random JWE Initialization Vector.*/
        byte[] iv = new byte[12];
        new Random().nextBytes(iv);

        /*o  Base64url-encode the JWE Initialization Vector.*/
        String base64EncIV = Base64.getEncoder().encodeToString(iv);

        /*o  Let the Additional Authenticated Data encryption parameter be
        ASCII(BASE64URL(UTF8(JWE Protected Header))).*/
        String protectedHeader = new Tag(RsaKey.ALGORITHM+"-OAEP",
                AesSecretKey.ALGORITHM_KEY.charAt(0)+""+AesSecretKey.SIZE+AesSecretKey.ALGORITHM_CIPHER).getTag();
        String base64ProtectedHeader = Base64.getEncoder().encodeToString(protectedHeader.getBytes());

        /*o  Perform authenticated encryption on the plaintext with the AES GCM
        algorithm using the CEK as the encryption key, the JWE Initialization Vector, */
        byte[] encryptedBytes = AesSecretKey.encript(plainText, aesKey, iv);

        /* and the Additional Authenticated Data
        value, requesting a 128-bit Authentication Tag output.*/
        byte[] tag = new byte[128/8];
        for (int i = 0; i < tag.length; i++) {
            tag[i]=encryptedBytes[i];
        }

        /*o  Base64url-encode the ciphertext.*/
        String base64EncText = Base64.getEncoder().encodeToString(encryptedBytes);

        /*o  Base64url-encode the Authentication Tag.*/
        String base64EncTag = Base64.getEncoder().encodeToString(tag);

        /*o  Assemble the final representation: The Compact
        Serialization of this result is the string*/
        String finalRepresentation =
                base64ProtectedHeader+".\n"+
                base64EncKey+".\n"+
                base64EncIV+".\n"+
                base64EncText+".\n"+
                base64EncTag;
        System.out.println(finalRepresentation);

        //   byte[] decipheredBytes = AesSecretKey.decrypt(encryptedBytes, aesKey, iv);
  //      SecretKey decipheredKey = RsaKey.decrypt(rsaKeyPair.getPrivate(), encryptedBytes);





    }
}
