/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.List;

import jp.co.mti.mixjuke.dao.RatingDao;
import jp.co.mti.mixjuke.dom.Favorite;
import jp.co.mti.mixjuke.dom.Rating;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.FavoriteService;
import jp.co.mti.mixjuke.service.RatingService;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ntnxuan
 * 
 */
@Service
@Transactional
public class RatingServiceImpl extends AbstractService<Rating> implements
        RatingService {

    @Autowired
    private RatingDao ratingDao;

    @Autowired
    private FavoriteService favoriteService;

    protected RatingServiceImpl() {
        super(Rating.class);
    }

    @Override
    public Rating findByUidAndSid(String mjuid, String sid) {
        Criteria criteria = ratingDao.createCriteria();
        criteria.add(Restrictions.eq("user.id", mjuid));
        criteria.add(Restrictions.eq("song.id", sid));
        if (criteria.list().isEmpty())
            return null;
        return (Rating) criteria.list().get(0);
    }

    @Override
    public boolean updateRatingAndFavorite(Rating rating,
            List<Favorite> favoriteList) {
        try {
            if (rating != null) {
                saveOrUpdate(rating);
            }
            if (!CollectionUtils.isEmpty(favoriteList)) {
                favoriteService.addList(favoriteList);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private Criteria createCriteriaGetListByFlag(int sflag, String mjuid) {
        Criteria criteria = ratingDao.createCriteria();
        criteria.add(Restrictions.eq("sflag", sflag));
        criteria.add(Restrictions.eq("user.id", mjuid));
        return criteria;
    }

    @Override
    public Long getTotalSongListBySflag(String mjuid, int sflag) {
        Criteria criteria = createCriteriaGetListByFlag(sflag, mjuid);
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.list().get(0);
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getSongIdsByFlag(String mjuid, int sflag, int offset,
            int count) {
        Criteria criteria = createCriteriaGetListByFlag(sflag, mjuid);
        criteria.setProjection(Projections.property("song.id"));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        return criteria.list();
    }
}
