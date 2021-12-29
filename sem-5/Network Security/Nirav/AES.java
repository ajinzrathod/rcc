import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class AES {
	private String mode;
	private static final String INIT_VECTOR = "encryptionInitVc";
	private SecretKey secretKey;

	public AES(String mode, String key) throws Exception {
		this.mode = mode;
		secretKey = KeyGenerator.getInstance("AES").generateKey();
	}
	
	public String encrypt(String plainText) throws Exception {
		Cipher cipher = Cipher.getInstance(String.format("AES/%s/PKCS5Padding", this.mode));
		IvParameterSpec iv = null;
		if(!this.mode.equals("ECB")) {
			iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		} else cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] originalBytes = plainText.getBytes(UTF_8);
		byte[] encryptedBytes = cipher.doFinal(originalBytes);
		byte[] encodedBytes = Base64.getEncoder().encode(encryptedBytes);
		return new String(encodedBytes);
	}
	
	public String decrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(String.format("AES/%s/PKCS5Padding", this.mode));
		IvParameterSpec iv = null;
		if(!this.mode.equals("ECB")) {
			iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		} else cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] originalBytes = cipherText.getBytes(UTF_8);
		byte[] decodedBytes = Base64.getDecoder().decode(originalBytes);
		byte[] decryptedBytes = cipher.doFinal(decodedBytes);
		return new String(decryptedBytes);
	}
	
	public static void main(String[] args) throws Exception {
		AES aes = new AES("ECB", "aesEncryptionKey");
		String plain = "Nirav Chavda";
		String cipher = aes.encrypt(plain);
		System.out.println("Cipher: " + cipher);
		String dc = aes.decrypt(cipher);
		System.out.println("Decrypted: " + dc);
	}
}