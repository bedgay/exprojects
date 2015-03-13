/**
 * 
 */
package jp.co.mti.mixjuke.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.web.external.request.BeginToken;
import jp.co.mti.mixjuke.web.external.request.EndToken;
import jp.co.mti.mixjuke.web.external.request.Subscribe;
import jp.co.mti.mixjuke.web.external.response.BeginTokenResponse;
import jp.co.mti.mixjuke.web.external.response.ResultResponse;
import jp.co.mti.mixjuke.web.external.response.SubscribeResponse;
import jp.co.mti.mixjuke.web.utils.MixjukeUtils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
public abstract class AbstractWeb {

    public static final String TOKEN_BEGIN = "IF5001";
    public static final String TOKEN_END = "IF5002";
    public static final String LOGIN = "IF5003";
    public static final String SIGUP = "IF5006";
    public static final String SUBCRIBE = "IF5008";
    public static final String RELEASE_SUBCRIBE = "IF5009";
    public static final String INQUIRY_MEMBER_ = "IF5015";

    public static final String COOKIE_SESSION_NAME = "iai_mtisess";
    public static final String COOKIE_TRANSACTION_NAME = "iai_cp_transactionid";
    public static final String COOKIE_MUID_NAME = "uid";

    public static final String SESSION_TOKEN_NAME = "token";

    public static final String MOPITA_CODE_NORMAL = "I000";
    public static final String MOPITA_CODE_SUBSCRIBED = "E011";

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CAPTCHA_VALIDATION = "captchaValidation";
    public static final String ACCOUNT_VALIDATION = "accountValidation";

    public static final String USER_NOT_LOGIN = "NULLGWDOCOMO";

	public static final String SERVICE_AUTO = "1";
    public static final String REGISTER_DEFAULT_VALUE = "1";
    public static final String SERVICE_REGIST_AUTO_URL = "/serviceRegist?service=free&auto=1";
    
    @Value("${base_url}")
    private String baseUrl;
    @Value("${login_url}")
    private String loginUrl;
    @Value("${iai_aver}")
    private String version;
    @Value("${access_key}")
    private String accessKey;
    @Value("${secret_key}")
    private String secretKey;
    @Value("${site_id}")
    private String siteID;
    @Value("${resource_id}")
    private String resourceID;
    @Value("${resigter_member_url}")
    private String registerUrl;
    @Value("${subscribe_member_url}")
    private String subscribeUrl;
    @Value("${release_subscribe_member_url}")
    private String releaseSubscribeUrl;
    @Value("${inquiry_member_url}")
    private String inquiryUrl;
    @Value("${token_begin_url}")
    private String beginTokenUrl;
    @Value("${token_end_url}")
    private String endTokenUrl;
    @Value("${cookie_domain}")
    private String cookieDomain;

    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl
     *            the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return the loginUrl
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * @param loginUrl
     *            the loginUrl to set
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * @param accessKey
     *            the accessKey to set
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * @return the secretKey
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * @param secretKey
     *            the secretKey to set
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * @return the siteID
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * @param siteID
     *            the siteID to set
     */
    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    /**
     * @return the resourceID
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID
     *            the resourceID to set
     */
    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * @return the registerUrl
     */
    public String getRegisterUrl() {
        return registerUrl;
    }

    /**
     * @param registerUrl
     *            the registerUrl to set
     */
    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    /**
     * @return the beginTokenUrl
     */
    public String getBeginTokenUrl() {
        return beginTokenUrl;
    }

    /**
     * @param beginTokenUrl
     *            the beginTokenUrl to set
     */
    public void setBeginTokenUrl(String beginTokenUrl) {
        this.beginTokenUrl = beginTokenUrl;
    }

    /**
     * @return the endTokenUrl
     */
    public String getEndTokenUrl() {
        return endTokenUrl;
    }

    /**
     * @param endTokenUrl
     *            the endToken to set
     */
    public void setEndTokenUrl(String endTokenUrl) {
        this.endTokenUrl = endTokenUrl;
    }

    /**
     * @return the inquiryUrl
     */
    public String getInquiryUrl() {
        return inquiryUrl;
    }

    /**
     * @param inquiryUrl
     *            the inquiryUrl to set
     */
    public void setInquiryUrl(String inquiryUrl) {
        this.inquiryUrl = inquiryUrl;
    }

    /**
     * @return the subscribeUrl
     */
    public String getSubscribeUrl() {
        return subscribeUrl;
    }

    /**
     * @param subscribeUrl
     *            the subscribeUrl to set
     */
    public void setSubscribeUrl(String subscribeUrl) {
        this.subscribeUrl = subscribeUrl;
    }

    /**
     * @return the cookieDomain
     */
    public String getCookieDomain() {
        return cookieDomain;
    }

    /**
     * @param cookieDomain
     *            the cookieDomain to set
     */
    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    /**
     * @return the releaseSubscribeUrl
     */
    public String getReleaseSubscribeUrl() {
        return releaseSubscribeUrl;
    }

    /**
     * @param releaseSubscribeUrl
     *            the releaseSubscribeUrl to set
     */
    public void setReleaseSubscribeUrl(String releaseSubscribeUrl) {
        this.releaseSubscribeUrl = releaseSubscribeUrl;
    }
    
    /**
     * Begin token with Mopita
     * @param client
     * @param ifId
     * @param request
     * @param response
     * @param om
     * @param transaction
     * @return
     * @throws IOException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws JsonParseException
     */
    protected BeginTokenResponse beginToken(Client client, String ifId, HttpServletRequest request,
			HttpServletResponse response, ObjectMapper om, String transaction)
			throws IOException, JsonGenerationException, JsonMappingException,
			UnsupportedEncodingException, InvalidKeyException,
			NoSuchAlgorithmException, JsonParseException {
		WebResource webResource;
		String sigBeginToken;
		String reqBeginToken;
		BeginTokenResponse tokenBeginResponse;
		MixjukeUtils.saveOrUpdateCookie(request, response,
		        this.getCookieDomain(), COOKIE_TRANSACTION_NAME,
		        transaction);
		BeginToken beginToken = new BeginToken(this.getVersion(),
		        this.getAccessKey(), MixjukeUtils.getTimeStampString(),
		        this.getSiteID(), transaction, ifId, null);

		String stringBeginToken = om.writeValueAsString(beginToken);
		reqBeginToken = MixjukeUtils
		        .getBase64URLEncoderString(stringBeginToken);
		sigBeginToken = MixjukeUtils.getURLEncoderString(MixjukeUtils
		        .getSignature(stringBeginToken, this.getSecretKey()));

		webResource = client.resource(this.getBaseUrl())
		        .path(this.getBeginTokenUrl())
		        .queryParam("iai_sig", sigBeginToken)
		        .queryParam("iai_req", reqBeginToken);

		String clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(
		        String.class);
		System.out
		        .println("Output from Mopita Server when try to begin token :\n*********\n"
		                + clientResponse.toString() + "\n*********\n");

		tokenBeginResponse = om.readValue(clientResponse,
		        BeginTokenResponse.class);
		return tokenBeginResponse;
	}
    
    /**
     * End token with Mopita
     * @param client
     * @param beginToken
     * @param om
     * @throws IOException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws JsonParseException
     */
    protected void endToken(Client client, String beginToken, ObjectMapper om)
			throws IOException, JsonGenerationException, JsonMappingException,
			UnsupportedEncodingException, InvalidKeyException,
			NoSuchAlgorithmException, JsonParseException {
		WebResource webResource;
		String sigEndToken;
		String reqEndToken;
		EndToken endToken = new EndToken(this.getVersion(),
		        this.getAccessKey(), MixjukeUtils.getTimeStampString(),
		        beginToken);

		String stringEndToken = om.writeValueAsString(endToken);
		reqEndToken = MixjukeUtils
		        .getBase64URLEncoderString(stringEndToken);
		sigEndToken = MixjukeUtils.getURLEncoderString(MixjukeUtils
		        .getSignature(stringEndToken, this.getSecretKey()));
		webResource = client.resource(this.getBaseUrl())
		        .path(this.getEndTokenUrl())
		        .queryParam("iai_sig", sigEndToken)
		        .queryParam("iai_req", reqEndToken);
		String clientResponse = webResource.type(MediaType.TEXT_PLAIN)
		        .get(String.class);
		System.out
		        .print("Output from Mopita Server when try to end token :\n*********\n"
		                + clientResponse.toString() + "\n*********\n");
		ResultResponse endResponse = om.readValue(clientResponse,
		        ResultResponse.class);

		if (endResponse == null
		        || !MOPITA_CODE_NORMAL.equals(endResponse.getResult()
		                .getCode())) {
		    System.out.println("Receive error from Mopita: "
		            + endResponse.getResult().getCode());
		}
	}

	/**
	 * Subscribe to register MixJuke user
	 * @param client
	 * @param iiaiMuid
	 * @param request
	 * @param response
	 * @param tokenBeginResponse
	 * @param om
	 * @param transaction
	 * @return
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws JsonParseException
	 */
	protected SubscribeResponse subscribeProcess(Client client, String iiaiMuid, HttpServletRequest request,
			HttpServletResponse response,
			BeginTokenResponse tokenBeginResponse, ObjectMapper om,
			String transaction) throws IOException, JsonGenerationException,
			JsonMappingException, UnsupportedEncodingException,
			InvalidKeyException, NoSuchAlgorithmException, JsonParseException {
		WebResource webResource;
		String sigSubscribe;
		String reqSubscribe;
		String clientResponse;
		Subscribe subscribe = new Subscribe();
		subscribe.setIaiAver(this.getVersion());
		subscribe.setIaiAkey(this.getAccessKey());
		subscribe.setIaiAtms(MixjukeUtils.getTimeStampString());
		subscribe.setIaiSiteID(this.getSiteID());
		subscribe.setIaiCpTransactionId(transaction);
		subscribe.setIaiToken(tokenBeginResponse.getToken());
		subscribe.setIiaiMuid(iiaiMuid);
		subscribe.setIaiRid(this.getResourceID());

		String stringSubcribe = om.writeValueAsString(subscribe);
		reqSubscribe = MixjukeUtils
		        .getBase64URLEncoderString(stringSubcribe);
		sigSubscribe = MixjukeUtils.getURLEncoderString(MixjukeUtils
		        .getSignature(stringSubcribe, this.getSecretKey()));

		webResource = client.resource(this.getBaseUrl())
		        .path(this.getSubscribeUrl())
		        .queryParam("iai_sig", sigSubscribe)
		        .queryParam("iai_req", reqSubscribe);

		clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(
		        String.class);
		System.out
		        .println("Output from Mopita Server when try to subscribe :\n*********\n"
		                + clientResponse.toString() + "\n*********\n");
		SubscribeResponse subscribeResponse = om.readValue(clientResponse,
		        SubscribeResponse.class);
		return subscribeResponse;
	}
	
}
