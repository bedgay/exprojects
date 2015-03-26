/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.AlbumDao;
import jp.co.mti.mixjuke.dom.Album;

/**
 * @author ntnxuan
 * 
 */
@Repository("albumDao")
public class AlbumDaoImpl extends AbstractDao<Album> implements AlbumDao {

    public AlbumDaoImpl() {
        super(Album.class);
    }

}
