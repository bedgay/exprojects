package jp.co.mti.mixjuke;

import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.request.MemberStatus;
import jp.co.mti.mixjuke.ws.request.UserInput;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;
import jp.co.mti.mixjuke.ws.response.UserResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * This is used for testing the calling Users Web-service.
 * <p>
 * Basically, this class will setup a simulate server and try to connect to this
 * server by using a real API.
 * 
 * @author Xuan Nguyen
 * 
 */
public class UsersWebServiceIT extends AbstractMixJukeJersey {
    /**
     * Call test service to prepare data
     */
    @Before
    public void preexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "800")
                .queryParam("act", "0").accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Clean data after testing using test service
     */
    @After
    public void postexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "800")
                .queryParam("act", "1").accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Test 8.UserProfile API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetUserProfile801() {
        WebResource webResource = resource();
        UserResponse response = webResource.path("/users").get(
                UserResponse.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 8.UserProfile API
     * <p>
     * Pre-condition: uid is blank
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetUserProfile802() {
        WebResource webResource = resource();
        UserResponse response = webResource.path("/users")
                .queryParam("uid", " ").get(UserResponse.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 8.UserProfile API
     * <p>
     * Pre-condition: uid is not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetUserProfile803() {
        WebResource webResource = resource();
        UserResponse response = webResource.path("/users")
                .queryParam("uid", "80000").get(UserResponse.class);
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 8.UserProfile API
     * <p>
     * Pre-condition: input valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testGetUserProfile804() {
        WebResource webResource = resource();
        UserResponse response = webResource.path("/users")
                .queryParam("uid", "000800").get(UserResponse.class);
        
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
        Assert.assertEquals(MemberStatus.FREE_TRIAL.getStatus(), response.getUser().getMemberStatus());
        Assert.assertTrue(response.getUser().isInitial());
    }

    /**
     * Test 9.UserProfileUpdate API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateUserProfile901() {
        WebResource webResource = resource();
        UserInput user = new UserInput();
        user.getUser().setName("Xuan");
        user.getUser().setGender(1);
        user.getUser().setAvatorUrl("http://somewhere/avator/00001.jpg");
        user.getUser().setBirthday("19870812");
        user.getUser().setShareProfile(true);
        
        ObjectMapper om = new ObjectMapper();
        SuccessResponse response;
		try {
			response = webResource.path("/users")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.TEXT_PLAIN)
			        .put(SuccessResponse.class, om.writeValueAsString(user));
	        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
	                response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * Test 9.UserProfileUpdate API
     * <p>
     * Pre-condition: uid is blank
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateUserProfile902() {
        WebResource webResource = resource();
        UserInput user = new UserInput();
        user.getUser().setName("Xuan");
        user.getUser().setGender(1);
        user.getUser().setAvatorUrl("http://somewhere/avator/00001.jpg");
        user.getUser().setBirthday("19870812");
        user.getUser().setShareProfile(true);

        ObjectMapper om = new ObjectMapper();
        try {
			SuccessResponse response = webResource.path("/users")
			        .queryParam("uid", " ").accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.TEXT_PLAIN)
			        .put(SuccessResponse.class, om.writeValueAsString(user));
			Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
			        response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * Test 9.UserProfileUpdate API
     * <p>
     * Pre-condition: uid is not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateUserProfile903() {
        WebResource webResource = resource();
        UserInput user = new UserInput();
        user.getUser().setName("Xuan");
        user.getUser().setGender(1);
        user.getUser().setAvatorUrl("http://somewhere/avator/00001.jpg");
        user.getUser().setBirthday("19870812");
        user.getUser().setShareProfile(true);

        ObjectMapper om = new ObjectMapper();
        try {
			SuccessResponse response = webResource.path("/users")
			        .queryParam("uid", "000801").accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.TEXT_PLAIN)
			        .put(SuccessResponse.class, om.writeValueAsString(user));
			Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
			        response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * Test 9.UserProfileUpdate API
     * <p>
     * Pre-condition: uid is NULLGWDOCOMO
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateUserProfile904() {
        WebResource webResource = resource();
        UserInput user = new UserInput();
        user.getUser().setName("Xuan");
        user.getUser().setGender(1);
        user.getUser().setAvatorUrl("http://somewhere/avator/00001.jpg");
        user.getUser().setBirthday("19870812");
        user.getUser().setShareProfile(true);

        ObjectMapper om = new ObjectMapper();
        try {
			SuccessResponse response = webResource.path("/users")
			        .queryParam("uid", "NULLGWDOCOMO")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.TEXT_PLAIN)
			        .put(SuccessResponse.class, om.writeValueAsString(user));
			Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(),
			        response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * Test 9.UserProfileUpdate API
     * <p>
     * Pre-condition: all input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testUpdateUserProfile905() {
        WebResource webResource = resource();

        UserInput user = new UserInput();
        user.getUser().setName("Xuan");
        user.getUser().setGender(1);
        user.getUser().setAvatorUrl("http://somewhere/avator/00001.jpg");
        user.getUser().setBirthday("19870812");
        user.getUser().setShareProfile(true);

        ObjectMapper om = new ObjectMapper();
        try {
			SuccessResponse response = webResource.path("/users")
			        .queryParam("uid", "000800").accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.TEXT_PLAIN)
			        .put(SuccessResponse.class, om.writeValueAsString(user));
			Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
			        response.getResult());
			
			 UserResponse res = webResource.path("/users")
		                .queryParam("uid", "000800").get(UserResponse.class);
		        
		        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
		                response.getResult());
		        Assert.assertTrue(!res.getUser().isInitial());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
