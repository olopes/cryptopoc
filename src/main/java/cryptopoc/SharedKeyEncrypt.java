package cryptopoc;

import java.security.AlgorithmParameters;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SharedKeyEncrypt {
	
	public static String encrypt(String message) {
		try {
			return _encrypt(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to encrypt message", e);
		}
	}
	private static String _encrypt(String message) throws Exception {
		byte [] salt = CryptoHelper.randomBytes(256/8);
		SecretKey key = CryptoHelper.getSecretKey("ChaveSecreta1234!", salt);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters params = cipher.getParameters();
		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] ciphertext = cipher.doFinal(message.getBytes("UTF-8"));
		
		byte [] data = new byte[salt.length+iv.length+ciphertext.length];
		System.arraycopy(salt, 0, data, 0, salt.length);
		System.arraycopy(iv, 0, data, salt.length, iv.length);
		System.arraycopy(ciphertext, 0, data, salt.length+iv.length, ciphertext.length);
		return new String(Base64.getEncoder().encode(data));
	}

}
