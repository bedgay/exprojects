/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import static org.junit.Assert.assertTrue;
import jp.co.mti.mixjuke.ws.response.GenreInfo;

import org.junit.Test;

/**
 * @author ntnxuan
 * 
 */
public class GenreUTTest extends AbstractDom {
    @Test
    public void testToGenreInfo() {
        Genre genre = new Genre();
        String id = "111";
        genre.setId(id);

        GenreInfo genreInfo = genre.toGenreInfo();
        assertTrue(genreInfo.getMixUrl().contains(genre.getId()));
    }
}
