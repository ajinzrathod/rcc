import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

class Aes {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            
            byte[] text = "Hi".getBytes();

            keyGenerator.init(128);
            cipher.init(1, secretKey);
            byte[] encryptedText = cipher.doFinal(text);
            System.out.println(new String(encryptedText));

            GCMParameterSpec spec = new GCMParameterSpec(128,cipher.getIV());
            cipher.init(2, secretKey, spec);
            byte[] decryptedText = cipher.doFinal(encryptedText);
            System.out.println(new String(decryptedText));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}