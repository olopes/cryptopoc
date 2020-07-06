package cryptopoc;

import java.security.PrivateKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class KeyPairEncrypt {
	
	public static String encrypt(String message) {
		try {
			return _encrypt(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to encrypt message", e);
		}
	}
	
	private static String _encrypt(String message) throws Exception {
		PrivateKey key = CryptoHelper.getPrivateKey();
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte [] salt = CryptoHelper.randomBytes(16);
		byte [] messageData = message.getBytes("UTF-8");

		// update() result can be ignore because salt is smaller that the block size and therefore no data is returned.
		cipher.update(salt);
		byte[] cipherData = cipher.doFinal(messageData);
		
		return new String(Base64.getEncoder().encode(cipherData));
	}
}
