package ciphers;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;


public class RSA implements Ciphers {
    private KeyPair keypair = generateRSAKeyPair();

    public RSA() {
    }

    public static KeyPair generateRSAKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String cipher(String input) {
        try {
            byte[] inputToBytes = input.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, this.keypair.getPublic());
            byte[] output = cipher.doFinal(inputToBytes);
            return encode(output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decipher(String input) {
        try {
            byte[] encryptedBytes =decode(input);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, this.keypair.getPrivate());
            byte[] output = cipher.doFinal(encryptedBytes);
            return new String(output,"UTF8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    @Override
    public String toString(){return "RSA";}
}