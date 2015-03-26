package jp.co.mti.mixjuke;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.mti.mixjuke.ws.response.BillingEventRFirst;
import jp.co.mti.mixjuke.ws.response.BillingResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author natu
 * @date 2014-01-10
 */
public class BillingSendEventIT extends AbstractMixJukeJersey {
	private static final String SIGNED_DATA = "{\"nonce\":-1159776891784657825,\"orders\":[{\"notificationId\":\"1896428543334488169\",\"orderId\":\"12999763169054705758.1359278713472160\",\"packageName\":\"jp.co.mti.mixjuke.personal_music_radio\",\"productId\":\"mixjuke_monthly_350\",\"purchaseTime\":1389355555119,\"purchaseState\":0,\"developerPayload\":\"iai_ordid=euH8uuaZzETgWj9XU8uVHIx4e%2BKDYoGlRGLYSOu0zNI%3D\",\"purchaseToken\":\"zmdxsqamtefktlrpgmolwukk\"}]}";
	private static final String SIGNATURE = "OfD/Tn+bCR0ysaB+9mWJUTqfoA+H7XNjlkma1h5aAGSNsxBXlml8R6GyXX3ksqyu/NYlx/UyeD2q2g5ke42WQcbr4T2JKAruBOcPyJdfWzpvBc0wavYeAWPzo0FYtgiUfH4gdIK8mRghJwCU4Xd1M1CPQkBkWcoOyXUVnywY98oUcBPnMFeb8e+y6s5Z6UClsW2HG/PuUNG/I0dAdFgPX0XGgHvVU7r1s3Jd/q2dvuWCvcCAUkyxO0F0BDBCd3hzbIyJNtumME4HfjeL+vIRX9JxH4zxMJLV+N/cDnGr26SRvcJx88sRV4TsAMhjH7bOcfLv2HRoPliNTiLA93N4eA==";

	/**
	 * Call test service to prepare data
	 */
	@Before
	public void preexecute() {
		WebResource webResource = resource();
		webResource.path("/test").queryParam("caseId", "26")
				.queryParam("act", "0").accept(MediaType.TEXT_PLAIN)
				.type(MediaType.TEXT_PLAIN).get(String.class);
	}

	@Test
	public void billingCase01WrongInput() {
		try {
			ObjectMapper om = new ObjectMapper();
			WebResource webResource = resource();
			String response = webResource.path("/iabilling/sendevent").post(String.class, "");
			BillingResponse billingResponse = om.readValue(response, BillingResponse.class);
			Assert.assertEquals(ResultCode.SYSTEM_ERROR.getResultCode(), billingResponse.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void billingCase02EmptySignature() {
		try {
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("sig", "");
			queryParams.add("data", SIGNED_DATA);
			
			WebResource webResource = resource();
			String response = webResource.path("/iabilling/sendevent").post(String.class, queryParams);

			ObjectMapper om = new ObjectMapper();
			BillingResponse billingResponse = om.readValue(response, BillingResponse.class);
			Assert.assertEquals(ResultCode.SYSTEM_ERROR.getResultCode(), billingResponse.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void billingCase03EmptyData() {
		try {
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("sig", SIGNATURE);
			queryParams.add("data", "");
			
			WebResource webResource = resource();
			String response = webResource.path("/iabilling/sendevent").post(String.class, queryParams);

			ObjectMapper om = new ObjectMapper();
			BillingResponse billingResponse = om.readValue(response, BillingResponse.class);
			Assert.assertEquals(ResultCode.SYSTEM_ERROR.getResultCode(), billingResponse.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void billingCase04PremiumMemberRegister() {
		try {
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("sig", SIGNATURE);
			queryParams.add("data", SIGNED_DATA);

			ObjectMapper om = new ObjectMapper();
			WebResource webResource = resource();
			String response = webResource.path("/iabilling/sendevent").post(String.class, queryParams);
			BillingResponse billingResponse = om.readValue(response, BillingResponse.class);
			Assert.assertEquals(ResultCode.SYSTEM_ERROR.getResultCode(), billingResponse.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void parseJson() {
		String json = "{\"purchaselist\":[{\"purchaseinfo\":{\"notificationid\":\"1031958969963215735\",\"orderid\":\"12999763169054705758.1323660570763124\",\"purchaseToken\":\"kfeiaiqfkswfxcfhksopmvne\",\"receptiontype\":\"1\",\"result\":{\"code\":\"E016\",\"args\":[\"\",\"ã‚³ãƒ¼ãƒ«ãƒ�ãƒƒã‚¯çµ�æžœOKä»¥å¤–\"]}}}],\"result\":{\"code\":\"E071\",\"args\":[]}}";

		ObjectMapper om = new ObjectMapper();
		BillingEventRFirst first = null;
		try {
			first = om.readValue(json, BillingEventRFirst.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotEquals(first, null);
	}

	/**
	 * Clean data after testing using test service
	 */
	@After
	public void postexecute() {
		WebResource webResource = resource();
		webResource.path("/test").queryParam("caseId", "26")
				.queryParam("act", "1").accept(MediaType.TEXT_PLAIN)
				.type(MediaType.TEXT_PLAIN).get(String.class);
	}

}