import java.math.BigInteger;
import java.security.MessageDigest;


public class Md5 {
    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] text = md.digest("Jai Swamminarayan".getBytes());
            BigInteger num = new BigInteger(1, text);
            System.out.println(num.toString(16));
        } catch (Exception e) {}
    }
}