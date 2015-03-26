/**
 * 
 */
package jp.co.mti.mixjuke;

import jp.co.mti.mixjuke.ws.response.AlbumListResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongListFreeResponse;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * @author ntnxuan
 * 
 */
public class AlbumWebServiceIT extends AbstractMixJukeJersey {
    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testArtistAlbum2001() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource
                .path("/artists/33333333/albums").queryParam("offset", "3")
                .queryParam("count", "10").get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: aid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testArtistAlbum2002() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource.path("/artists/ /albums")
                .queryParam("offset", "3").queryParam("count", "10")
                .queryParam("uid", "200103").get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: offset < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testArtistAlbum2003() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource
                .path("/artists/33333333/albums").queryParam("offset", "-3")
                .queryParam("count", "10").queryParam("uid", "200103")
                .get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: count < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testArtistAlbum2004() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource
                .path("/artists/33333333/albums").queryParam("offset", "3")
                .queryParam("count", "-10").queryParam("uid", "200103")
                .get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: uid is NULLGWDOCOMO
     * <p>
     * Case: Play fail
     */
    @Test
    public void testArtistAlbum2005() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource
                .path("/artists/33333333/albums").queryParam("offset", "3")
                .queryParam("count", "10").queryParam("uid", "NULLGWDOCOMO")
                .get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(),
                response.getResult());
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: uid not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testArtistAlbum2006() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource
                .path("/artists/33333333/albums").queryParam("offset", "3")
                .queryParam("count", "10").queryParam("uid", "200103333")
                .get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2101() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/albums/2201/songs")
                .queryParam("offset", "0").queryParam("count", "10")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: album is blank
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2102() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/albums/ /songs")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "10").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: offset < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2103() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/albums/2201/songs")
                .queryParam("uid", "200100").queryParam("offset", "-10")
                .queryParam("count", "10").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: count < 0
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2104() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/albums/2201/songs")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "-10").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: uid is canceled
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2104WithCanceledMenber() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/artists/2201/songs")
                .queryParam("uid", "90009").queryParam("offset", "0")
                .queryParam("count", "-10").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: uid is NULLGWDOCOMO
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2105() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/albums/2201/songs")
                .queryParam("uid", "NULLGWDOCOMO").queryParam("offset", "0")
                .queryParam("count", "10").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.NOT_LOGIN.getResultCode(),
                response.getResult());
    }
    
    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: uid is not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testAlbumSong2106() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource.path("/albums/2201/songs")
                .queryParam("uid", "200133333").queryParam("offset", "0")
                .queryParam("count", "10").get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

}
