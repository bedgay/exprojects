package jp.co.mti.mixjuke;

import jp.co.mti.mixjuke.ws.response.ArtistListResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongListChargeResponse;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
public class ArtistsWebServiceIT extends AbstractMixJukeJersey {
    
    /**
     * Test 4.SearchArtist API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testSearchArtist402() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists")
                .queryParam("offset", "0").queryParam("count", "7")
                .queryParam("name", "NOT").get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 4.SearchArtist API
     * <p>
     * Pre-condition: name is null
     * <p>
     * Case: Play true
     */
    @Test
    public void testSearchArtist403() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "7").get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }
    
    /**
     * Test 4.SearchArtist API
     * <p>
     * Pre-condition:offset  < 0
     * <p>
     * Case: Play true
     */
    @Test
    public void testSearchArtist404() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists")
                .queryParam("uid", "200100").queryParam("offset", "-10")
                .queryParam("count", "3").queryParam("name", "NOT")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }
    
    /**
     * Test 4.SearchArtist API
     * <p>
     * Pre-condition: count<0
     * <p>
     * Case: Play true
     */
    @Test
    public void testSearchArtist405() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "-7").queryParam("name", "")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    @Test
    public void testGetHotArtistsInvalidUid() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists/hot")
                .queryParam("uid", "NULLGWDOCOMO").queryParam("offset", "0")
                .queryParam("count", "3")
                .get(ArtistListResponse.class);
        Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(),
                response.getResult());
    }

    @Test
    public void testGetHotArtistsUidNotInDB() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists/hot")
                .queryParam("uid", "NoInDB").queryParam("offset", "0")
                .queryParam("count", "3")
                .get(ArtistListResponse.class);
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    @Test
    public void testGetHotArtistsWithNegativeNumber() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists/hot")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "-3")
                .get(ArtistListResponse.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }
    
    @Test
    public void testGetHotArtistsWithExistUid() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists/hot")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "3")
                .get(ArtistListResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
    }

    /**
     * Test 5.ArtistMIX API
     * <p>
     * Pre-condition: count > 30
     * <p>
     * Case: Play success
     */
    @Test
    public void testArtistMIX506() {
        WebResource webResource = resource();

        SongListChargeResponse response = webResource
                .path("/artists/11111111/songs").queryParam("count", "33")
                .queryParam("uid", "200100")//a3c2deab3b0799c310
                .get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());

    }
    
}
