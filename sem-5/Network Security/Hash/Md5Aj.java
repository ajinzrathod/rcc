import java.security.MessageDigest;
import java.math.BigInteger;

class Md5Aj {
    public static void main(String[] args) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("sha-1");
            byte[] text = md5.digest("Hi vraj".getBytes());
            BigInteger big = new BigInteger(1, text);
            System.out.println(big.toString(16));    
        } catch (Exception e) {
            //TODO: handle exception
        }   
    }
}