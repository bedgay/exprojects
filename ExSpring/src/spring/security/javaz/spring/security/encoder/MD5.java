package javaz.spring.security.encoder;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * @author SUCCESS\tungo
 * @refrence-url: http://www.md5.cz/
 * 
 */
public class MD5 {

	private static final String MD5_INSTANCE = "MD5";

	/**
	 * @param input
	 * @return
	 */
	public static String encode(String input) {
		String output = null;

		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance(MD5_INSTANCE);
			byte[] bytes = md.digest(input.getBytes());

			BigInteger numbers = new BigInteger(1, bytes);
			output = numbers.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return output;
	}

	public static void main(String[] args) {
		encode("admin"); // 21232f297a57a5a743894a0e4a801fc3
	}

}
