package cryptopoc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoHelper {
	private static final String storePassword = "abcd1234";
	
	
	static KeyStore loadKeyStore() {
		try {
			KeyStore store = KeyStore.getInstance("PKCS12");
			try (InputStream stream = new FileInputStream("keystore.pfx")) {
				store.load(stream, storePassword.toCharArray());
				return store;
			}
		} catch(Exception e) {
			throw new RuntimeException("Failed to load the keystore.", e);
		}
	}
	
	static PublicKey getPublicKey() {
		KeyStore store = loadKeyStore();
		try {
			return store.getCertificate("SecretKey").getPublicKey();
		} catch (Exception e) {
			throw new RuntimeException("Failed to load the public key.", e);
		}
	}

	static PrivateKey getPrivateKey() {
		KeyStore store = loadKeyStore();
		try {
			return (PrivateKey) store.getKey("SecretKey", storePassword.toCharArray());
		} catch (Exception e) {
			throw new RuntimeException("Failed to load the private key.", e);
		}
	}
	
	// generate AES256 key
	static SecretKey getSecretKey(String password, byte [] salt) {
		int bits = salt.length*8;
		try {
			/* Derive the key, given password and salt. */
			// SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA"+bits);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, bits);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			return secret;
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate a secrt key.", e);
		}
	}

	static byte [] randomBytes(int len) {
		byte [] bytes = new byte[len];
		new SecureRandom().nextBytes(bytes);
		return bytes;
	}
}
