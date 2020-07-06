package cryptopoc;

import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class KeyPairDecrypt {
	
	public static String decrypt(String encryptedMessage) {
		try {
			return _decrypt(encryptedMessage);
		} catch (Exception e) {
			throw new RuntimeException("Failed to decrypt message", e);
		}
	}
	
	private static String _decrypt(String encryptedMessage) throws Exception {
		PublicKey key = CryptoHelper.getPublicKey();
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte [] cipherData = Base64.getDecoder().decode(encryptedMessage.getBytes());
		byte [] messageData = cipher.doFinal(cipherData);
		// ignore salt
		return new String(messageData, 16, messageData.length-16, "UTF-8");
	}
}
