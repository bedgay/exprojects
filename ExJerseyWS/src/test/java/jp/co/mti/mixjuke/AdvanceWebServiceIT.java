/**
 * 
 */
package jp.co.mti.mixjuke;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.request.LocalArtistInput;
import jp.co.mti.mixjuke.ws.response.AlbumListResponse;
import jp.co.mti.mixjuke.ws.response.ArtistInfo;
import jp.co.mti.mixjuke.ws.response.ArtistListResponse;
import jp.co.mti.mixjuke.ws.response.ArtistResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongInfo;
import jp.co.mti.mixjuke.ws.response.SongListChargeResponse;
import jp.co.mti.mixjuke.ws.response.SongListFreeResponse;
import jp.co.mti.mixjuke.ws.response.SongResponse;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;
import jp.co.mti.mixjuke.ws.response.UserResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
public class AdvanceWebServiceIT extends AbstractMixJukeJersey {
    /**
     * Call test service to prepare data -> should be dumper DB from server for
     * fully data.
     */
    @Before
    public void preexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "200")
                .queryParam("act", "0").accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Clean data after testing using test service
     */
    // @After
    // public void postexecute() {
    // WebResource webResource = resource();
    // webResource.path("/test").queryParam("caseId", "200")
    // .queryParam("act", "1").accept(MediaType.TEXT_PLAIN)
    // .type(MediaType.TEXT_PLAIN).get(String.class);
    // }

    private void printResult(Object o) {
        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println(om.writeValueAsString(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test 10.UploadLocalArtist API
     * <p>
     * Pre-condition: input valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testUploadLocalArtist1006() {
        WebResource webResource = resource();

        LocalArtistInput artist = new LocalArtistInput();
        List<String> array = new ArrayList<String>();
        String arname = "Artist7";
        array.add("The Foundations");
        array.add("Michael Jacson");
        array.add("Artist4");
        array.add(arname);
        artist.setArtists(array);
        ObjectMapper om = new ObjectMapper();
        String input = null;
        try {
            input = om.writeValueAsString(artist);
        } catch (Exception e) {
        }
        SuccessResponse response = webResource.path("/artists/favorites")
                .queryParam("uid", "200100").accept(MediaType.APPLICATION_JSON)
                .put(SuccessResponse.class, input);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
        
        ArtistListResponse res = webResource.path("/artists/favorites")
                .queryParam("uid", "200100")
                .queryParam("offset", "0").queryParam("count", "12")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                res.getResult());

        boolean flag = false;
        for (ArtistInfo ar : res.getArtists()) {
            if(ar.getName().equals(arname)){
                flag = true;
            }
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * Test 2.GenreMIX API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testGenreMIXWebService208() {
        WebResource webResource = resource();

        SongListChargeResponse response = webResource.path("/songs")
                .queryParam("gid", "10").queryParam("uid", "200100")// e0401bb6df4811e8c1
                .queryParam("count", "5").get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 5.ArtistMIX API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testArtistMIX506() {
        WebResource webResource = resource();

        SongListChargeResponse response = webResource
                .path("/artists/11111111/songs").queryParam("count", "10")
                .queryParam("uid", "200100")// a3c2deab3b0799c310
                .get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 14.SongList API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testSongListWebService141() {
        WebResource webResource = resource();

        SongListChargeResponse response = webResource.path("/songs")
                .queryParam("offset", "1").queryParam("uid", "200100")
                .queryParam("count", "3").queryParam("sflag", "2")
                .get(SongListChargeResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 22.SongInfo API
     * <p>
     * Pre-condition: sid not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetSongInfo2203() {
        WebResource webResource = resource();

        SongResponse response = webResource.path("/songs/00002113")
                .queryParam("uid", "200100").get(SongResponse.class);

        Assert.assertEquals(ResultCode.CAN_FOUND_SPEC_RESOURCE.getResultCode(),
                response.getResult());
    }

    /**
     * Test 22.SongInfo API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testGetSongInfo2204() {
        WebResource webResource = resource();

        SongResponse response = webResource.path("/songs/10002003")
                .queryParam("uid", "200103").get(SongResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 11.FavoriteArtists API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testFavoriteArtist1104() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists/favorites")
                .queryParam("uid", "200103")
                // a3c2deab3b0799c310
                .queryParam("offset", "0").queryParam("count", "10")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 13.RemoveFavoriteArtist
     * <p>
     * Pre-condition: aid is valid, uid valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testRemoveFavoriteArtist1308() {
        WebResource webResource = resource();
        String aID = "11111111";
        SuccessResponse response = webResource
                .path("/artists/favorites/" + aID)
                .queryParam("uid", "200100").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).delete(SuccessResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
        
        ArtistListResponse res = webResource.path("/artists/favorites")
                .queryParam("uid", "200103")
                .queryParam("offset", "0").queryParam("count", "12")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                res.getResult());

        boolean flag = true;
        for (ArtistInfo ar : res.getArtists()) {
            if(ar.getAid().equals(aID)){
                flag = false;
            }
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * Test 23.ArtistInfo API
     * <p>
     * Pre-condition: uid,aid is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testArtistInfo2306() {
        WebResource webResource = resource();
        ArtistResponse response = webResource.path("/artists/11111111")
                .queryParam("uid", "200100").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).get(ArtistResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }
    /**
     * Test 23.ArtistInfo API
     * <p>
     * Pre-condition: aid is invalid(there is no performance)
     * <p>
     * Case: Play success
     */
    @Test
    public void testArtistInfo2307() {
        WebResource webResource = resource();
        ArtistResponse response = webResource.path("/artists/16161616")
                .queryParam("uid", "200100").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).get(ArtistResponse.class);
        Assert.assertEquals(ResultCode.CAN_FOUND_SPEC_RESOURCE.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: sid not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testUpdateSong1506() {
        WebResource webResource = resource();

        SuccessResponse response = webResource.path("/songs/000000")
                .queryParam("sflag", "2").queryParam("uid", "200103")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.CAN_FOUND_SPEC_RESOURCE.getResultCode(),
                response.getResult());
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: rating(uid, sid) haven't exist before -> OK: insert rating
     * <p>
     * Case: Play success
     */
    @Test
    public void testUpdateSong1507() {
        WebResource webResource = resource();
        String sid = "10002007";
        SuccessResponse response = webResource.path("/songs/" + sid)
                .queryParam("sflag", "2").queryParam("uid", "200103")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        SongListFreeResponse res = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("uid", "200103")
                .queryParam("count", "6").queryParam("sflag", "2")
                .get(SongListFreeResponse.class);

        boolean flag = false;
        for (SongInfo s : res.getSongs()) {
            if (s.getSid().equals(sid)) {
                flag = true;
            }
        }

        Assert.assertTrue(flag);

        printResult(res);
    }

    /**
     * Test 15.UpdateSong API
     * <p>
     * Pre-condition: rating(uid, sid) have exist before -> OK: update rating
     * <p>
     * Case: Play success
     */
    @Test
    public void testUpdateSong1508() {
        WebResource webResource = resource();
        String sid = "10002003";
        SuccessResponse response = webResource.path("/songs/" + sid)
                .queryParam("sflag", "2").queryParam("uid", "200103")
                .put(SuccessResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        SongListFreeResponse res = webResource.path("/songs")
                .queryParam("offset", "0").queryParam("uid", "200103")
                .queryParam("count", "6").queryParam("sflag", "2")
                .get(SongListFreeResponse.class);

        boolean flag = false;
        for (SongInfo s : res.getSongs()) {
            if (s.getSid().equals(sid)) {
                flag = true;
            }
        }

        Assert.assertTrue(flag);

        printResult(res);
    }

    /**
     * Test 20.ArtistAlbum API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testArtistAlbum2000() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource.path("/artists/33333333/albums")
                .queryParam("uid", "200103")//3c97756e9903f8e407
                .queryParam("offset", "0").queryParam("count", "10")
                .get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 21.AlbumSong API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testAlbumSong2100() {
        WebResource webResource = resource();

        SongListFreeResponse response = webResource
                .path("/albums/2201/songs")
                .queryParam("uid", "200103")
                .queryParam("offset", "0").queryParam("count", "13")
                .get(SongListFreeResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 3.ArtistList API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testArtistList301() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists")
                .queryParam("uid", "200103")//849769ad6093c8d64c
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 4.SearchArtist API
     * <p>
     * Pre-condition: input is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testSearchArtist401() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "10").queryParam("name", "Artist")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());

        printResult(response);
    }

    /**
     * Test 12.AddFavoriteArtist API
     * <p>
     * Pre-condition: aid is duplicate in DB
     * <p>
     * Case: Play success
     */
    @Test
    public void testAddFavoriteArtist1207() {
        WebResource webResource = resource();
        SuccessResponse response = webResource
                .path("/artists/favorites/11111111")
                .queryParam("uid", "200100").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).put(SuccessResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
    }

    /**
     * Test 12.AddFavoriteArtist API
     * <p>
     * Pre-condition: aid is valid, uid valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testAddFavoriteArtist1208() {
        WebResource webResource = resource();
        String aID = "11111111";
        SuccessResponse response = webResource
                .path("/artists/favorites/" + aID)
                .queryParam("uid", "200103").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).put(SuccessResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
        
        ArtistListResponse res = webResource.path("/artists/favorites")
                .queryParam("uid", "200103")
                .queryParam("offset", "0").queryParam("count", "12")
                .get(ArtistListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                res.getResult());

        boolean flag = false;
        for (ArtistInfo ar : res.getArtists()) {
            if(ar.getAid().equals(aID)){
                flag = true;
            }
        }
        Assert.assertEquals(flag, true);
        
    }

    /**
     * Test 13.RemoveFavoriteArtist
     * <p>
     * Pre-condition: aid is not in DB
     * <p>
     * Case: Play success
     */
    @Test
    public void testRemoveFavoriteArtist1306() {
        WebResource webResource = resource();
        SuccessResponse response = webResource
                .path("/artists/favorites/00000000")
                .queryParam("uid", "200103").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).delete(SuccessResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
    }

    @Test
    public void testGetHotArtistsSuccess() {
        WebResource webResource = resource();

        ArtistListResponse response = webResource.path("/artists/hot")
                .queryParam("uid", "200100").queryParam("offset", "0")
                .queryParam("count", "3").get(ArtistListResponse.class);
        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
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
    public void testArtistAlbum2002WithCanceledMenber() {
        WebResource webResource = resource();

        AlbumListResponse response = webResource.path("/artists/33333333/albums")
                .queryParam("offset", "3").queryParam("count", "10")
                .queryParam("uid", "90009").get(AlbumListResponse.class);

        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }


    /**
     * Test 8.UserProfile API
     * <p>
     * Pre-condition: uid is canceled
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetUserProfile805() {
        WebResource webResource = resource();
        UserResponse response = webResource.path("/users")
                .queryParam("uid", "90009").get(UserResponse.class);
        
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }
    
}
