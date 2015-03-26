package jp.co.mti.mixjuke.dom;

import jp.co.mti.mixjuke.ws.response.ArtistInfo;

import org.junit.Assert;
import org.junit.Test;

public class ArtistUTTest extends AbstractDom{
    @Test
    public void testToArtistInfoWithNonFavorite() {
        String uID = "111";
        Artist artist = createNewArtist("222", "artist name", null);
        ArtistInfo artistInfo = artist.toArtistInfo(uID);

        Assert.assertTrue(!artistInfo.isFavorited());

        printResult(artistInfo);
    }

    @Test
    public void testToArtistInfoWithFavorite() {
        String uID = "111";
        String aID = "111";
        User user = createNewUser(uID, "222");
        Artist artist = createNewArtist(aID, "artist name", user);
        ArtistInfo artistInfo = artist.toArtistInfo(uID);

        Assert.assertTrue(artistInfo.isFavorited());

        printResult(artistInfo);
    }
}
