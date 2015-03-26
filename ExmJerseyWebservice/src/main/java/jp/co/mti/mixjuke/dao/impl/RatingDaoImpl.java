/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.RatingDao;
import jp.co.mti.mixjuke.dom.Rating;

/**
 * @author ntnxuan
 * 
 */
@Repository("ratingDao")
public class RatingDaoImpl extends AbstractDao<Rating> implements RatingDao {

    public RatingDaoImpl() {
        super(Rating.class);
    }
}
