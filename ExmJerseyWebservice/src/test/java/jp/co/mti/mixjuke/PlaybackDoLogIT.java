package jp.co.mti.mixjuke;

import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.request.PlaybackLogInput;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * Unit testing for PlaybackDoLogImpl
 * @author natu
 *
 */
public class PlaybackDoLogIT extends AbstractMixJukeJersey {

    /**
     * Call test service to prepare data
     */
    @Before
    public void preexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "18").queryParam("act", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }
    
    @Test
    public void case01InvalidUid() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void case02NotLogin() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=NULLGWDOCOMO";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void case03NotAMember() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=notamember";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void case04CanNotReadJson() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=01118";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void case05EncodingDataSuccess() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=01118";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.NORMAL.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void case06EncodingDataSuccessWithSeedArtist() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22seed_artist_id%22%3A%20%2211111%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=01118";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.NORMAL.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void case07EncodingDataSuccessWithSeedGenre() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22seed_genre_id%22%3A%20%2233333%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=01118";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.NORMAL.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void case08EncodingDataWithCanceledMember() {    	
    	try {
			String jsonWithoutPlayType = "data=%7B%0D%0A%20%20%20%20%22device_cd%22%3A%20%22android%22%2C%0D%0A%20%20%20%20%22dev_unique_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%22device_nm%22%3A%20%22NEXUS%205%22%2C%0D%0A%20%20%20%20%22course_id%22%3A%201%2C%0D%0A%20%20%20%20%22log%22%3A%20%5B%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22seed_genre_id%22%3A%20%2233333%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162241%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22rj29erf9erhg9g%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2050%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%203%0D%0A%20%20%20%20%20%20%20%20%7D%2C%0D%0A%20%20%20%20%20%20%20%20%7B%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22streaming_dt%22%3A%20%2220140324162312%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22prod_id%22%3A%20%22adwqe3234fref%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_sec%22%3A%2029%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_session_id%22%3A%20%22%3Ca%20uuid%3E%22%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22play_type%22%3A%201%2C%0D%0A%20%20%20%20%20%20%20%20%20%20%20%20%22log_type%22%3A%2010%0D%0A%20%20%20%20%20%20%20%20%7D%0D%0A%20%20%20%20%5D%0D%0A%7D%0D%0A&uid=90009";
			
			WebResource webResource = resource();
			SuccessResponse response = webResource.path("/playbacklog")
			        .accept(MediaType.APPLICATION_JSON)
			        .type(MediaType.APPLICATION_FORM_URLENCODED).post(SuccessResponse.class, jsonWithoutPlayType); 
			Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(), response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test 
    public void case09ParseJson() {
    	ObjectMapper om = new ObjectMapper();
    	String json = "{\"log\":[{\"streaming_dt\":\"20140123160915\",\"play_session_id\":\"a9892c3c-e386-4dd5-ac29-67d50569aa3a\",\"log_type\":3,\"play_type\":0,\"play_sec\":24,\"prod_id\":\"aaa14fa8\"}],\"device_cd\":\"android\",\"dev_unique_id\":\"42f669124eb2bfef\",\"device_nm\":\"GT-I9300\"}";
    	PlaybackLogInput input = null;
    	try {
			input =  om.readValue(json, PlaybackLogInput.class);
		} catch (Exception e) {}
    	Assert.assertNotNull(input);
    }
    
    /**
     * Clean data after testing using test service
     */
    @After
    public void postexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "18").queryParam("act", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

}
