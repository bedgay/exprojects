package jp.co.mti.mixjuke.dom;

import static org.junit.Assert.assertTrue;
import jp.co.mti.mixjuke.ws.response.AlbumInfo;

import org.junit.Test;

public class AlbumUTTest extends AbstractDom {
    @Test
    public void testAlbumWithSongURL() {
        String uID = "111";
        String sID = "111";
        String alID = "111";
        User user = createNewUser(uID, "321");
        Song song = createNewSong(sID, "song file name", "222", user);
        Album album = createNewAlbum(alID, "album name", user, song, "jacket img");
        AlbumInfo albumInfo = album.toAlbumInfo();

        assertTrue(albumInfo.getSongsUrl().contains(album.getId()));
        assertTrue(albumInfo.getAlbumArtUrl().contains(album.getJacketImg()));
        
        printResult(albumInfo);
    }
}
