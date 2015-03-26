/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.ArtistGenreDao;
import jp.co.mti.mixjuke.dom.ArtistGenre;

/**
 * @author ntnxuan
 * 
 */
@Repository("artistGenreDao")
public class ArtistGenreDaoImpl extends AbstractDao<ArtistGenre> implements
        ArtistGenreDao {

    protected ArtistGenreDaoImpl() {
        super(ArtistGenre.class);
    }

}
