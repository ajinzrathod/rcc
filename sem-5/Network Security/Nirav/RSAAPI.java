import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAAPI {
    KeyPairGenerator keyPairGenerator;
    KeyPair keyPair;
    PrivateKey privateKey;
    PublicKey publicKey;

    public RSAAPI() throws NoSuchAlgorithmException {
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();

        try (FileOutputStream fos1 = new FileOutputStream("public.key");
                FileOutputStream fos2 = new FileOutputStream("private.key")) {
            fos1.write(publicKey.getEncoded());
            fos2.write(privateKey.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] plainTextBytes = plainText.getBytes();
        byte[] cipherTextBytes = encryptCipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encodeToString(cipherTextBytes);
    }

    public String decrypt(String cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainTextBytes = decryptCipher.doFinal(cipherTextBytes);
        String plainText = new String(plainTextBytes, StandardCharsets.UTF_8);
        return plainText;
    }

    public static void main(String[] args) {
        try {
            RSAAPI rsa = new RSAAPI();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Plain Text: ");
            String plainText = sc.nextLine();
            String cipherText = rsa.encrypt(plainText);
            String decrypted = rsa.decrypt(cipherText);

            System.out.println(String.format("Encrypted: %s, Decrypted: %s", cipherText, decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}