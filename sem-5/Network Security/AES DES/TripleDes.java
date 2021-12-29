import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


class TripleDes {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            
            byte[] text = "Hi".getBytes();

            final IvParameterSpec spec = new IvParameterSpec(new byte[8]);

            cipher.init(1, secretKey, spec);
            byte[] encryptedText = cipher.doFinal(text);
            System.out.println(new String(encryptedText));

            cipher.init(2, secretKey, spec);
            byte[] decryptedText = cipher.doFinal(encryptedText);
            System.out.println(new String(decryptedText));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}