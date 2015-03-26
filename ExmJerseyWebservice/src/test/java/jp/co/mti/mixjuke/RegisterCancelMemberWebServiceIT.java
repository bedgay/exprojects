package jp.co.mti.mixjuke;

import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.Purchase;
import jp.co.mti.mixjuke.util.MixjukeConstants;
import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.request.CancelationInput;
import jp.co.mti.mixjuke.ws.request.RegistrationInput;
import jp.co.mti.mixjuke.ws.response.MopitaResult;
import jp.co.mti.mixjuke.ws.util.URLUtils;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * User: naminh Date: 10/21/13 Time: 2:07 PM
 */
public class RegisterCancelMemberWebServiceIT extends AbstractMixJukeJersey {
	private static final String SIGNED_DATA = "{\"nonce\":-1159776891784657825,\"orders\":[{\"notificationId\":\"1896428543334488169\",\"orderId\":\"12999763169054705758.1359278713472160\",\"packageName\":\"jp.co.mti.mixjuke.personal_music_radio\",\"productId\":\"mixjuke_monthly_350\",\"purchaseTime\":1389355555119,\"purchaseState\":0,\"developerPayload\":\"iai_ordid=euH8uuaZzETgWj9XU8uVHIx4e%2BKDYoGlRGLYSOu0zNI%3D\",\"purchaseToken\":\"zmdxsqamtefktlrpgmolwukk\"}]}";
	private static final String SIGNATURE = "OfD/Tn+bCR0ysaB+9mWJUTqfoA+H7XNjlkma1h5aAGSNsxBXlml8R6GyXX3ksqyu/NYlx/UyeD2q2g5ke42WQcbr4T2JKAruBOcPyJdfWzpvBc0wavYeAWPzo0FYtgiUfH4gdIK8mRghJwCU4Xd1M1CPQkBkWcoOyXUVnywY98oUcBPnMFeb8e+y6s5Z6UClsW2HG/PuUNG/I0dAdFgPX0XGgHvVU7r1s3Jd/q2dvuWCvcCAUkyxO0F0BDBCd3hzbIyJNtumME4HfjeL+vIRX9JxH4zxMJLV+N/cDnGr26SRvcJx88sRV4TsAMhjH7bOcfLv2HRoPliNTiLA93N4eA==";
	
	/**
	 * Call test service to prepare data
	 */
	@Before
	public void preexecute() {
		WebResource webResource = resource();
		webResource.path("/test").queryParam("caseId", "24")
				.queryParam("act", "0").accept(MediaType.TEXT_PLAIN)
				.type(MediaType.TEXT_PLAIN).get(String.class);
		webResource.path("/test").queryParam("caseId", "25")
				.queryParam("act", "0").accept(MediaType.TEXT_PLAIN)
				.type(MediaType.TEXT_PLAIN).get(String.class);

	}

	@Test
	public void registerCase01CreateUserSuccessfully() {
		RegistrationInput input = new RegistrationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID));
		input.setCs("");
		input.setUid("24001");
		input.setAct(MixjukeConstants.MEMBER_ACT_REG);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void registerCase02CheckPremiumMember() {
		RegistrationInput input = new RegistrationInput();

		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setCs("");
		input.setUid("MTI_ID");
		input.setAct(MixjukeConstants.MEMBER_ACT_CHK);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registerCase03PremiumMemberRegister() {
		RegistrationInput input = new RegistrationInput();

		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setCs("");
		input.setUid("01111");
		input.setAct(MixjukeConstants.MEMBER_ACT_REG);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);

			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registerCase04NewPremiumMemberRegister() {
		RegistrationInput input = new RegistrationInput();

		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setCs("");
		input.setUid("24004");
		input.setAct(MixjukeConstants.MEMBER_ACT_REG);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void registerCase05CreateUserButItExist() {
		RegistrationInput input = new RegistrationInput();

		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID));
		input.setCs("");
		input.setUid("01111");
		input.setAct(MixjukeConstants.MEMBER_ACT_REG);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals(MopitaResult.NG.getCode(), response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registerCase06InvalidUid() {
		RegistrationInput input = new RegistrationInput();

		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID));
		input.setCs("");
		input.setUid("NULLGWDOCOMO");
		input.setAct(MixjukeConstants.MEMBER_ACT_REG);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals(MopitaResult.NG.getCode(), response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void registerCase07WrongInput() {
		try {
			ObjectMapper om = new ObjectMapper();
			WebResource webResource = resource();
			String data = om.writeValueAsString("");
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals(MopitaResult.NG.getCode(), response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registerCase08ExistsCanceledUser() {
		RegistrationInput input = new RegistrationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID));
		input.setCs("");
		input.setUid("24008");
		input.setAct(MixjukeConstants.MEMBER_ACT_REG);
		input.setIai_tms("20140110204639335");
		input.setIai_paytype(14);
		input.setIai_ordid("2014011052731038515bab62fc");
		input.setArg("");

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, RegistrationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			
			String response = webResource.path("/member/cb/regist").post(
					String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void cancelCase01DeleteFreeMembership() {
		CancelationInput input = new CancelationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID));
		input.setUid("25001");
		input.setAct("reg");
		input.setIai_tms("20101201000000000");
		input.setIai_paytype(14);

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, CancelationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			String response = webResource.path("/member/cb/cancel").post(String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cancelCase02Premium2Free() {
		CancelationInput input = new CancelationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setUid("25002");
		input.setAct("reg");
		input.setIai_tms("20101201000000000");
		input.setIai_paytype(14);

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, CancelationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			String response = webResource.path("/member/cb/cancel").post(
					String.class, data);
			Assert.assertEquals("OK", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cancelCase03PurchaseHistoryIsNull() {
		CancelationInput input = new CancelationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setUid("25003");
		input.setAct("reg");
		input.setIai_tms("20101201000000000");
		input.setIai_paytype(14);

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, CancelationInput.class);
			
			data = "uid=25003&ci=R000001433&act=reg&iai_inapp_sig_d=&iai_paytype=14&act=reg&iai_dl_market=&iai_cp_itemid=&iai_inapp_ps=&iai_inapp_oid=&iai_inapp_pid=&iai_inapp_sig=";
			String response = webResource.path("/member/cb/cancel").post(
					String.class, data);
			Assert.assertEquals("NG", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cancelCase04UserNotInDB() {
		CancelationInput input = new CancelationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setUid("25004");
		input.setAct("reg");
		input.setIai_tms("20101201000000000");
		input.setIai_paytype(14);

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, CancelationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			String response = webResource.path("/member/cb/cancel").post(
					String.class, data);
			Assert.assertEquals("NG", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cancelCase05UidIsInvalid() {
		CancelationInput input = new CancelationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID));
		input.setUid("NULLGWDOCOMO");
		input.setAct("reg");
		input.setIai_tms("20101201000000000");
		input.setIai_paytype(14);

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, CancelationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			String response = webResource.path("/member/cb/cancel").post(
					String.class, data);
			Assert.assertEquals("NG", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cancelCase06WrongInput() {
		try {
			ObjectMapper om = new ObjectMapper();
			WebResource webResource = resource();
			String data = om.writeValueAsString(new Purchase());
			String response = webResource.path("/member/cb/cancel").post(
					String.class, data);
			Assert.assertEquals("NG", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cancelCase07WithCanceledMember() {
		CancelationInput input = new CancelationInput();
		input.setCi(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID));
		input.setUid("25007");
		input.setAct("reg");
		input.setIai_tms("20101201000000000");
		input.setIai_paytype(14);

		ObjectMapper om = new ObjectMapper();
		Purchase history = null;

		try {
			history = om.readValue(SIGNED_DATA, Purchase.class);
			input.setIai_inapp_sig_d(history);
			input.setIai_inapp_sig(SIGNATURE);
			
			WebResource webResource = resource();
			String data = URLUtils.parseObjectTopostData(input, CancelationInput.class);
			
			data = data.replace("iai_inapp_sig_d=null", "iai_inapp_sig_d=" + SIGNED_DATA);
			String response = webResource.path("/member/cb/cancel").post(String.class, data);
			Assert.assertEquals("NG", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Clean data after testing using test service
	 */
	@After
	public void postexecute() {
		WebResource webResource = resource();
		webResource.path("/test").queryParam("caseId", "24")
				.queryParam("act", "1").accept(MediaType.TEXT_PLAIN)
				.type(MediaType.TEXT_PLAIN).get(String.class);
		webResource.path("/test").queryParam("caseId", "25")
				.queryParam("act", "1").accept(MediaType.TEXT_PLAIN)
				.type(MediaType.TEXT_PLAIN).get(String.class);

	}

}
