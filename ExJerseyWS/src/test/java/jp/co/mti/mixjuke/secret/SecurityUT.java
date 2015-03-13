package jp.co.mti.mixjuke.secret;

import java.security.PublicKey;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author natu
 * @date 2014-01-13
 */
public class SecurityUT {

	@Test
	public void case01VerifyPass() {
		PublicKey publicKey = Security
				.generatePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhqpOXsLd6p4cP6vZGbfqllZRD81KI9HxyK9LxdPzJtB6MvvV3BTmD8F4aBQljWsW6fGuNwAwpvRobpg+pSeztKoxQIl9EcE1G15gHN1u5rM4Mr+J39TcrAyhgTO2payYNnCQXoTfQ5vnS1wveqsHmI5fb9sD+4uC6pqvpE0+8Ln60f9vLaJrwbSVXSCdW2a0yLYYS3QlT6sR42eynw56fDvycIFa1blf1u8iYNPijWYC0USzfgfyseRzLR/6DsCCz6WHsz7XCRpiJ8b43JYoKKrgV8IYPhVTThFTW9onfJbW/5GzwO+2h10mpfP4z33tW7YDdaSV3EUazakqhFXFgwIDAQAB");
		String signedData = "{\"nonce\":-1159776891784657825,\"orders\":[{\"notificationId\":\"1896428543334488169\",\"orderId\":\"12999763169054705758.1359278713472160\",\"packageName\":\"jp.co.mti.mixjuke.personal_music_radio\",\"productId\":\"mixjuke_monthly_350\",\"purchaseTime\":1389355555119,\"purchaseState\":0,\"developerPayload\":\"iai_ordid=euH8uuaZzETgWj9XU8uVHIx4e%2BKDYoGlRGLYSOu0zNI%3D\",\"purchaseToken\":\"zmdxsqamtefktlrpgmolwukk\"}]}";
		String signature = "OfD/Tn+bCR0ysaB+9mWJUTqfoA+H7XNjlkma1h5aAGSNsxBXlml8R6GyXX3ksqyu/NYlx/UyeD2q2g5ke42WQcbr4T2JKAruBOcPyJdfWzpvBc0wavYeAWPzo0FYtgiUfH4gdIK8mRghJwCU4Xd1M1CPQkBkWcoOyXUVnywY98oUcBPnMFeb8e+y6s5Z6UClsW2HG/PuUNG/I0dAdFgPX0XGgHvVU7r1s3Jd/q2dvuWCvcCAUkyxO0F0BDBCd3hzbIyJNtumME4HfjeL+vIRX9JxH4zxMJLV+N/cDnGr26SRvcJx88sRV4TsAMhjH7bOcfLv2HRoPliNTiLA93N4eA==";
		Boolean out = Security.verify(publicKey, signedData, signature);
		assertEquals((Object) Boolean.TRUE, (Object) out);
	}

	@Test
	public void case02VerifyWrongSignedData() {
		PublicKey publicKey = Security
				.generatePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhqpOXsLd6p4cP6vZGbfqllZRD81KI9HxyK9LxdPzJtB6MvvV3BTmD8F4aBQljWsW6fGuNwAwpvRobpg+pSeztKoxQIl9EcE1G15gHN1u5rM4Mr+J39TcrAyhgTO2payYNnCQXoTfQ5vnS1wveqsHmI5fb9sD+4uC6pqvpE0+8Ln60f9vLaJrwbSVXSCdW2a0yLYYS3QlT6sR42eynw56fDvycIFa1blf1u8iYNPijWYC0USzfgfyseRzLR/6DsCCz6WHsz7XCRpiJ8b43JYoKKrgV8IYPhVTThFTW9onfJbW/5GzwO+2h10mpfP4z33tW7YDdaSV3EUazakqhFXFgwIDAQAB");
		String signedData = "=>{\"nonce\":-1159776891784657825,\"orders\":[{\"notificationId\":\"1896428543334488169\",\"orderId\":\"12999763169054705758.1359278713472160\",\"packageName\":\"jp.co.mti.mixjuke.personal_music_radio\",\"productId\":\"mixjuke_monthly_350\",\"purchaseTime\":1389355555119,\"purchaseState\":0,\"developerPayload\":\"iai_ordid=euH8uuaZzETgWj9XU8uVHIx4e%2BKDYoGlRGLYSOu0zNI%3D\",\"purchaseToken\":\"zmdxsqamtefktlrpgmolwukk\"}]}";
		String signature = "OfD/Tn+bCR0ysaB+9mWJUTqfoA+H7XNjlkma1h5aAGSNsxBXlml8R6GyXX3ksqyu/NYlx/UyeD2q2g5ke42WQcbr4T2JKAruBOcPyJdfWzpvBc0wavYeAWPzo0FYtgiUfH4gdIK8mRghJwCU4Xd1M1CPQkBkWcoOyXUVnywY98oUcBPnMFeb8e+y6s5Z6UClsW2HG/PuUNG/I0dAdFgPX0XGgHvVU7r1s3Jd/q2dvuWCvcCAUkyxO0F0BDBCd3hzbIyJNtumME4HfjeL+vIRX9JxH4zxMJLV+N/cDnGr26SRvcJx88sRV4TsAMhjH7bOcfLv2HRoPliNTiLA93N4eA==";
		Boolean out = Security.verify(publicKey, signedData, signature);
		assertEquals((Object) Boolean.FALSE, (Object) out);
	}

	@Test
	public void case03VerifyWrongSignature() {
		PublicKey publicKey = Security
				.generatePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhqpOXsLd6p4cP6vZGbfqllZRD81KI9HxyK9LxdPzJtB6MvvV3BTmD8F4aBQljWsW6fGuNwAwpvRobpg+pSeztKoxQIl9EcE1G15gHN1u5rM4Mr+J39TcrAyhgTO2payYNnCQXoTfQ5vnS1wveqsHmI5fb9sD+4uC6pqvpE0+8Ln60f9vLaJrwbSVXSCdW2a0yLYYS3QlT6sR42eynw56fDvycIFa1blf1u8iYNPijWYC0USzfgfyseRzLR/6DsCCz6WHsz7XCRpiJ8b43JYoKKrgV8IYPhVTThFTW9onfJbW/5GzwO+2h10mpfP4z33tW7YDdaSV3EUazakqhFXFgwIDAQAB");
		String signedData = "{\"nonce\":-1159776891784657825,\"orders\":[{\"notificationId\":\"1896428543334488169\",\"orderId\":\"12999763169054705758.1359278713472160\",\"packageName\":\"jp.co.mti.mixjuke.personal_music_radio\",\"productId\":\"mixjuke_monthly_350\",\"purchaseTime\":1389355555119,\"purchaseState\":0,\"developerPayload\":\"iai_ordid=euH8uuaZzETgWj9XU8uVHIx4e%2BKDYoGlRGLYSOu0zNI%3D\",\"purchaseToken\":\"zmdxsqamtefktlrpgmolwukk\"}]}";
		String signature = "=>OfD/Tn+bCR0ysaB+9mWJUTqfoA+H7XNjlkma1h5aAGSNsxBXlml8R6GyXX3ksqyu/NYlx/UyeD2q2g5ke42WQcbr4T2JKAruBOcPyJdfWzpvBc0wavYeAWPzo0FYtgiUfH4gdIK8mRghJwCU4Xd1M1CPQkBkWcoOyXUVnywY98oUcBPnMFeb8e+y6s5Z6UClsW2HG/PuUNG/I0dAdFgPX0XGgHvVU7r1s3Jd/q2dvuWCvcCAUkyxO0F0BDBCd3hzbIyJNtumME4HfjeL+vIRX9JxH4zxMJLV+N/cDnGr26SRvcJx88sRV4TsAMhjH7bOcfLv2HRoPliNTiLA93N4eA==";
		Boolean out = Security.verify(publicKey, signedData, signature);
		assertEquals((Object) Boolean.FALSE, (Object) out);
	}
}
