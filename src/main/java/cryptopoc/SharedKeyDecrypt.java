package cryptopoc;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SharedKeyDecrypt {
	
	public static String decrypt(String message) {
		try {
			return _decrypt(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to decrypt message", e);
		}
	}
	private static String _decrypt(String sData) throws Exception {
		byte [] data = Base64.getDecoder().decode(sData.getBytes());
		byte [] salt = new byte[256/8];
		byte [] iv = new byte[salt.length/2];
		System.arraycopy(data, 0, salt, 0, salt.length);
		System.arraycopy(data, salt.length, iv, 0, iv.length);
		
		SecretKey key = CryptoHelper.getSecretKey("ChaveSecreta1234!", salt);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		
		int offset = salt.length+iv.length;
		int length = data.length - offset;
		byte[] message = cipher.doFinal(data, offset, length);
		
		return new String(message, "UTF-8");
	}

}
