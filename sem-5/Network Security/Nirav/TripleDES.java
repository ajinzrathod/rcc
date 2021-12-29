import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.security.*;

public class TripleDES {
	private static final String INIT_VECTOR = "football";
	private final String mode;
	private SecretKey secretKey;
	
	public TripleDES(String mode, String key) throws Exception {
		this.mode = mode;
		secretKey = SecretKeyFactory.getInstance("TripleDES").generateSecret(new DESedeKeySpec(key.getBytes()));	
		// secretKey = KeyGenerator.getInstance("DESede").generateKey();
	}
	
	public String encrypt(String plainText) throws Exception {
		Cipher cipher = Cipher.getInstance(String.format("DESede/%s/PKCS5Padding", this.mode));

		IvParameterSpec iv = null;
		if(!this.mode.equals("ECB")) {
			iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		} else cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] originalBytes = plainText.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedBytes = cipher.doFinal(originalBytes);
		byte[] encodedBytes = Base64.getEncoder().encode(encryptedBytes);
		return new String(encodedBytes);
	}
	
	public String decrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(String.format("DESede/%s/PKCS5Padding", this.mode));
		
		IvParameterSpec iv = null;
		if(!this.mode.equals("ECB")) {
			iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		} else cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] originalBytes = cipherText.getBytes();
		byte[] decodedBytes = Base64.getDecoder().decode(originalBytes);
		byte[] decryptedBytes = cipher.doFinal(decodedBytes);
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}
	
	public static void main(String[] args) throws Exception {
		TripleDES des = new TripleDES("ECB", "unofficialprofootball123");
		String enc = des.encrypt("Nirav Chavda");
		System.out.println("Encrypted: " + enc);
		
		String dec = des.decrypt(enc);
		System.out.println("Decrypted: " + dec);
	}
}