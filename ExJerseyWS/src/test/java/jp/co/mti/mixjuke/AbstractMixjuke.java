package jp.co.mti.mixjuke;

import jp.co.mti.mixjuke.dom.Genre;
import jp.co.mti.mixjuke.service.ArtistService;
import jp.co.mti.mixjuke.service.GenreService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the abstraction for all UNIT TEST using inside Mixjuke.
 * 
 * @author Xuan Nguyen
 * 
 */

@Transactional
public class AbstractMixjuke {

    @Autowired
    protected SongService songService;

    @Autowired
    protected GenreService genreService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ArtistService artistService;

    protected void createDummyGenre(String genreId, String name) {
        Genre genre = new Genre();
        genre.setIconUrl("http://somewhere/mix/genre/00001.jpg");
        genre.setName(name);
        genre.setId(genreId);
        genreService.saveOrUpdate(genre);
    }

}