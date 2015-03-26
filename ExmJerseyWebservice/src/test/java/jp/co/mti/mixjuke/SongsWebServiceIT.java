package jp.co.mti.mixjuke;

import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongListChargeResponse;
import jp.co.mti.mixjuke.ws.response.SongListFreeResponse;
import jp.co.mti.mixjuke.ws.response.SongListResponse;
import jp.co.mti.mixjuke.ws.response.SongResponse;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
public class SongsWebServiceIT extends AbstractMixJukeJersey {
	
    /**
     * Test 2.GenreMIX API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGenreMIXWebService201() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("gid", "2001").queryParam("count", "10")
                .get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 2.GenreMIX API
     * <p>
     * Pre-condition: gid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGenreMIXWebService202() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("uid", "000100").queryParam("count", "10")
                .get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 2.GenreMIX API
     * <p>
     * Pre-condition: uid is NULLGWDOCOMO
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGenreMIXWebService207() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("gid", "200011").queryParam("uid", "NULLGWDOCOMO")
                .queryParam("count", "10").get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(),
                response.getResult());
    }

    /**
     * Test 22.SongInfo API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetSongInfo2200() {
        WebResource webResource = resource();

        SongResponse response = webResource.path("/songs/00002003").get(
        		SongResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 22.SongInfo API
     * <p>
     * Pre-condition: sid is blank
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetSongInfo2201() {
        WebResource webResource = resource();

        SongResponse response = webResource.path("/songs/ ")
                .queryParam("uid", "200100").get(SongResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 22.SongInfo API
     * <p>
     * Pre-condition: uid not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetSongInfo2202() {
        WebResource webResource = resource();

        SongResponse response = webResource.path("/songs/00002003")
                .queryParam("uid", "200111").get(SongResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSongListWebService142() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("count", "6")
                .queryParam("sflag", "1").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: offset < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSongListWebService143() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("offset", "-2").queryParam("uid", "200100")
                .queryParam("count", "6").queryParam("sflag", "1")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: count < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSongListWebService144() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("uid", "200100")
                .queryParam("count", "-6").queryParam("sflag", "1")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: sflag < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSongListWebService145() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("uid", "200100")
                .queryParam("count", "6").queryParam("sflag", "-3")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: uid is NULLGWDOCOMO
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSongListWebService146() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("uid", "NULLGWDOCOMO")
                .queryParam("count", "6").queryParam("sflag", "1")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(),
                response.getResult());
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: uid not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSongListWebService147() {
        WebResource webResource = resource();

        SongListResponse response = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("uid", "00000")
                .queryParam("count", "6").queryParam("sflag", "1")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: sflag < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateSong1501() {
        WebResource webResource = resource();

        SuccessResponse response = webResource.path("/songs/00002003")
                .queryParam("sflag", "-2").queryParam("uid", "200103")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: sflag > 2
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateSong1502() {
        WebResource webResource = resource();

        SuccessResponse response = webResource.path("/songs/00002003")
                .queryParam("sflag", "3").queryParam("uid", "200103")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateSong1503() {
        WebResource webResource = resource();

        SuccessResponse response = webResource.path("/songs/00002003")
                .queryParam("sflag", "2").put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: sid is blank
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateSong1504() {
        WebResource webResource = resource();

        SuccessResponse response = webResource.path("/songs/ ")
                .queryParam("sflag", "2").queryParam("uid", "200103")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: uid not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateSong1505() {
        WebResource webResource = resource();

        SuccessResponse response = webResource.path("/songs/00000000")
                .queryParam("sflag", "2").queryParam("uid", "200333")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }
}
