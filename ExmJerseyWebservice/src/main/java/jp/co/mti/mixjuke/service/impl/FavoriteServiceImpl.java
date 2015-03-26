/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.List;

import jp.co.mti.mixjuke.dao.FavoriteDao;
import jp.co.mti.mixjuke.dom.Favorite;
import jp.co.mti.mixjuke.dom.Performance;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.FavoriteService;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ntnxuan
 * 
 */
@Service
@Transactional
public class FavoriteServiceImpl extends AbstractService<Favorite> implements
        FavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    public FavoriteServiceImpl() {
        super(Favorite.class);
    }

    @Override
    public Favorite findByUidAnfAid(String mjuid, String aid) {
        Criteria criteria = favoriteDao.createCriteria();
        criteria.add(Restrictions.eq("user.id", mjuid));
        criteria.add(Restrictions.eq("artist.id", aid));
        if (criteria.list().isEmpty())
            return null;
        return (Favorite) criteria.list().get(0);
    }

    @Override
    public Long getTotalFavoriteListByUid(String mjuid) {
        Criteria criteria = favoriteDao.createCriteria("f");
        criteria.add(Restrictions.eq("user.id", mjuid));
        // using exist clause to check artist valid
        DetachedCriteria sub = DetachedCriteria
                .forClass(Performance.class, "p");
        sub.add(Restrictions.eqProperty("p.artist.id", "f.artist.id"));
        sub.setProjection(Projections.property("p.id"));
        criteria.add(Subqueries.exists(sub));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.list().get(0);
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Favorite> getListByUid(String mjuid, int offset, int count) {
        Criteria criteria = favoriteDao.createCriteria("f");
        criteria.add(Restrictions.eq("user.id", mjuid));
        criteria.createAlias("artist", "artist", JoinType.LEFT_OUTER_JOIN);
        // using exist clause to check artist valid
        DetachedCriteria sub = DetachedCriteria
                .forClass(Performance.class, "p");
        sub.add(Restrictions.eqProperty("p.artist.id", "f.artist.id"));
        sub.setProjection(Projections.property("p.id"));
        criteria.add(Subqueries.exists(sub));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getFavoriteArtistIds(String mjuid, int offset, int count) {
        Criteria criteria = favoriteDao.createCriteria("f");
        criteria.setProjection(Projections.property("artist.id"));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        // using exist clause to check artist valid
        DetachedCriteria sub = DetachedCriteria
                .forClass(Performance.class, "p");
        sub.add(Restrictions.eqProperty("p.artist.id", "f.artist.id"));
        sub.setProjection(Projections.property("p.id"));
        criteria.add(Subqueries.exists(sub));
        criteria.add(Restrictions.eq("user.id", mjuid));
        return criteria.list();
    }
}
