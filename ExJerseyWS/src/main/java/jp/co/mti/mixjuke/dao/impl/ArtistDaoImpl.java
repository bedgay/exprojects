/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.ArtistDao;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.ModelList;
import jp.co.mti.mixjuke.dom.Performance;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * @author Xuan Nguyen
 * 
 */
@Repository("artistDao")
public class ArtistDaoImpl extends AbstractDao<Artist> implements ArtistDao {

    protected ArtistDaoImpl() {
        super(Artist.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Artist> getFavoritedArtistListByUid(String userid,
            List<String> favoriteArtistIds) {
        if(CollectionUtils.isEmpty(favoriteArtistIds)){
            return new ArrayList<Artist>();
        }
        Criteria criteria = getCurrentSession().createCriteria(Artist.class);
        getCurrentSession().enableFilter("favoriteFilter").setParameter(
                "userFavoriteFilterParam", userid);
        criteria.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("p.album", "album", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("favorites", "f", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.in("id", favoriteArtistIds));
        criteria.addOrder(Order.desc("f.addDatetime"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Artist>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Artist getArtistByArtistId(String userid, String aid) {
        Criteria criteria = getCurrentSession().createCriteria(Artist.class, "a");
        getCurrentSession().enableFilter("favoriteFilter").setParameter(
                "userFavoriteFilterParam", userid);
        criteria.createAlias("favorites", "favorite", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("favorite.user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
     // using exist clause to check artist valid
        DetachedCriteria sub = DetachedCriteria
                .forClass(Performance.class, "p");
        sub.add(Restrictions.eqProperty("p.artist.id", "a.id"));
        sub.setProjection(Projections.property("p.id"));
        criteria.add(Subqueries.exists(sub));
        criteria.createAlias("p.album", "album", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("id", aid));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Artist> l = criteria.list();
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Artist> getArtistListByArtistIds(String userid,
            List<String> artistIds, int offset, int count) {
        if(CollectionUtils.isEmpty(artistIds)){
            return new ArrayList<Artist>();
        }
        Criteria criteriaFetch = getCurrentSession().createCriteria(
                Artist.class);
        getCurrentSession().enableFilter("favoriteFilter").setParameter(
                "userFavoriteFilterParam", userid);
        criteriaFetch.createAlias("favorites", "favorites",
                JoinType.LEFT_OUTER_JOIN);
        criteriaFetch
                .createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteriaFetch.createAlias("p.album", "album", JoinType.LEFT_OUTER_JOIN);
        criteriaFetch.add(Restrictions.in("id", artistIds));
        criteriaFetch.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteriaFetch.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Artist> getArtistListByProperty(String propertyName,
            List<String> list, String mjuid) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<Artist>();
        }
        Criteria criteria = getCurrentSession().createCriteria(Artist.class);
        getCurrentSession().enableFilter("favoriteFilter").setParameter(
                "userFavoriteFilterParam", mjuid);
        criteria.createAlias("favorites", "favorites", JoinType.LEFT_OUTER_JOIN);
        // criteria.createAlias("favorites.user", "user",
        // JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.in(propertyName, list));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.dao.ArtistDao#getHotArtistListBySearch(java.lang.String, int, int)
	 */
	@Override
    @SuppressWarnings({"unchecked", "rawtypes"})
	public ModelList<Artist> getHotArtistListBySearch(String userid, int offset,
			int count) {

        String hql = "select count(a.id) from HotArtist h, Artist a where a.id=h.id";
        Query query = getCurrentSession().createQuery(hql);
		Long total = (Long)query.uniqueResult();
        if (total <= 0) {
            return new ModelList<Artist>();
        }

        hql = "select a.id from HotArtist h, Artist a where a.id=h.id";
        query = getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
		List ids = query.list();
        if (CollectionUtils.isEmpty(ids)) {
            return new ModelList<Artist>();
        }

        getCurrentSession().enableFilter("favoriteFilter").setParameter("userFavoriteFilterParam", userid);
        Criteria criteriaFetch = getCurrentSession().createCriteria(Artist.class);
        criteriaFetch.createAlias("favorites", "favorites", JoinType.LEFT_OUTER_JOIN);
        // JoinType.LEFT_OUTER_JOIN);
        criteriaFetch.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteriaFetch.createAlias("p.album", "album", JoinType.LEFT_OUTER_JOIN);
        criteriaFetch.add(Restrictions.in("id", ids));
        criteriaFetch.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Artist> list = criteriaFetch.list();
                
        ModelList<Artist> mList = new ModelList<Artist>();
        mList.setTotal(total);
        mList.setList(list);
        return mList;
	}
    
}
