package jp.co.mti.mixjuke;

import com.sun.jersey.api.client.WebResource;
import jp.co.mti.mixjuke.ws.response.PlaybackTokenRespone;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

/**
 * User: naminh
 * Date: 10/24/13
 * Time: 11:48 AM
 */
public class PlaybackTokenWebServiceIT extends AbstractMixJukeJersey {
    /**
     * Call test service to prepare data
     */
    @Before
    public void preexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "17").queryParam("act", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=null, uid=""
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase01() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "").queryParam("dev_unique_id","DEV001").queryParam("take","1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=NULLGWDOCOMO
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase02() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "NULLGWDOCOMO").queryParam("dev_unique_id","DEV001").queryParam("take","1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(response.getResult(), ResultCode.NOT_LOGIN.getResultCode());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,take=5
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase03() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("dev_unique_id","DEV001").queryParam("take","5").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,take=-1
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase04() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("dev_unique_id","DEV001").queryParam("take","-1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,devid=null
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase05() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("take","1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,take=0,devid=null
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase06() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("take","0").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,take=0,devid=devid
     * Case: Play normal(1)
     */
    @Test
    public void playbackTokenCase07() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("take","0").queryParam("dev_unique_id","DEV001").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,take=0,devid!=devid
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase08() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("take","0").queryParam("dev_unique_id","DEV").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.ALREADY_DEVICE.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid=valid,devid=null
     * Case: Play normal(2)
     */
    @Test
    public void playbackTokenCase09() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01115").queryParam("dev_unique_id","DEV001").queryParam("take","1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(), response.getResult());
    }

    /**
     * Test 17. PlaybackToken API
     * Pre-condition: User with uid is not in db
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase10() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "01116").queryParam("dev_unique_id","DEV001").queryParam("take","1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(), response.getResult());
    }

    /**
     * Test 11. PlaybackToken API
     * Pre-condition: User with uid is canceled
     * Case: Play fail
     */
    @Test
    public void playbackTokenCase11() {
        WebResource webResource = resource();
        PlaybackTokenRespone response;
        response = webResource.path("/playbacktoken")
                .queryParam("uid", "90009").queryParam("dev_unique_id","DEV001").queryParam("take","1").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(PlaybackTokenRespone.class);
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(), response.getResult());
    }


    /**
     * Clean data after testing using test service
     */
    @After
    public void postexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "17").queryParam("act", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }
}
