package cryptopoc;

public class MainPOC {

	public static void main(String[] args) {
		String message = "Hello World! ;-)";
		System.out.println("The message is: "+message);
		runRSA(message);
		runAES(message);
	}

	private static void runRSA(String message) {
		System.out.println("10 RSA runs with key loaded from keystore.pfx");
		for(int i = 0; i < 10; i++) {
			String encrypted = KeyPairEncrypt.encrypt(message);
			System.out.println("Message: '"+encrypted+"'");
			String decrypted = KeyPairDecrypt.decrypt(encrypted);
			System.out.println(decrypted);
		}
	}

	private static void runAES(String message) {
		System.out.println("10 AES256 runs with well known password.");
		for(int i = 0; i < 10; i++) {
			String encrypted = SharedKeyEncrypt.encrypt(message);
			System.out.println("Message: '"+encrypted+"'");
			String decrypted = SharedKeyDecrypt.decrypt(encrypted);
			System.out.println(decrypted);
		}
	}

}
