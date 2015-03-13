/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.FavoriteDao;
import jp.co.mti.mixjuke.dom.Favorite;

/**
 * @author ntnxuan
 *
 */
@Repository("favoriteDao")
public class FavoriteDaoImpl extends AbstractDao<Favorite> implements
        FavoriteDao {

    protected FavoriteDaoImpl() {
        super(Favorite.class);
    }

 }
