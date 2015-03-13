/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import jp.co.mti.mixjuke.ws.response.SongInfo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ntnxuan
 *
 */
public class SongUTTest extends AbstractDom {
    @Test
    public void testToSongInfoWithGetStreamIsTrue() {
        String jkimg = "jacket image";
        User user = createNewUser("123", "321");
        Song song = createNewSong("111", "song file name", "222", user);
        Set<Performance> performances = new HashSet<Performance>();
        performances.add(createNewPerformance(
                createNewArtist("111", "artist name", user), song,
                createNewAlbum("111", "album name", user, song, jkimg)));
        song.setPerformances(performances);
        
        SongInfo songInfo = song.toSongInfo(user, true, null);

        assertTrue(songInfo.getStreamUrl().contains(song.getFileName()));
        assertTrue(songInfo.getTrialUrl().contains(song.getFileName()));
        assertTrue(songInfo.getAlbumArtUrl().contains(jkimg));
        assertTrue(songInfo.getDetailUrl().contains(song.getId()));
        assertTrue(songInfo.getDetailUrl().contains(song.getRealId()));
        
        printResult(songInfo);
    }

    @Test
    public void testToSongInfoWithGetStreamIsFalse() {
        User user = createNewUser("123", "321");
        Song song = createNewSong("111", null, "222", user);

        SongInfo songInfo = song.toSongInfo(user, false, null);

        Assert.assertEquals(null, songInfo.getStreamUrl());
        Assert.assertEquals(null, songInfo.getTrialUrl());
        
        printResult(songInfo);
    }
}
