import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Hash {
    public static String generateHash(String algorithm, String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] messageDigest = md.digest(plainText.getBytes());
        return DatatypeConverter.printHexBinary(messageDigest);
        // BigInteger number = new BigInteger(1, messageDigest);
        // String hashText = number.toString(16);
        // while (hashText.length() < 32)
        // hashText = "0" + hashText;
        // return hashText;
    }

    static void print(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String plainText = "Network Security";
        String md5 = generateHash("MD5", plainText);
        print("---------- MD5 ----------");
        print(String.format("Plain: %s\nDigest: %s", plainText, md5));

        print("");

        String sha1 = generateHash("SHA1", plainText);
        print("---------- SHA1 ----------");
        print(String.format("Plain: %s\nDigest: %s", plainText, sha1));
    }
}
