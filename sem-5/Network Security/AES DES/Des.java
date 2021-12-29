import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


class Des {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            
            byte[] text = "Hi".getBytes();

            cipher.init(1, secretKey);
            byte[] encryptedText = cipher.doFinal(text);
            System.out.println(new String(encryptedText));

            cipher.init(2, secretKey);
            byte[] decryptedText = cipher.doFinal(encryptedText);
            System.out.println(new String(decryptedText));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}