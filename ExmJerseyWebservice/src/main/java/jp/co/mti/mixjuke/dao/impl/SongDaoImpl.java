/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.SongDao;
import jp.co.mti.mixjuke.dom.Song;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * @author Xuan Nguyen
 * 
 */
@Repository("songDao")
public class SongDaoImpl extends AbstractDao<Song> implements SongDao {

    protected SongDaoImpl() {
        super(Song.class);
    }

    /**
     * Get Song with necessary Artist, Rating, Favorite, Album info belong to
     * specified user. Not include sort, limit,... condition.
     * 
     * @param mjuid
     *            mjuid
     * @param ids
     *            song id list.
     * @return Criteria object.
     */
    private Criteria createCriteriaCommon(String mjuid, List<String> ids) {
        Criteria criteria = getCurrentSession().createCriteria(Song.class);
        getCurrentSession().enableFilter("ratingFilter").setParameter(
                "userRatingFilterParam", mjuid);
        getCurrentSession().enableFilter("favoriteFilter").setParameter(
                "userFavoriteFilterParam", mjuid);

        criteria.createAlias("ratings", "ratings", JoinType.LEFT_OUTER_JOIN);
        // criteria.createAlias("ratings.user", "userRating",
        // JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("p.album", "album", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("p.artist", "artist", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("artist.favorites", "favorites",
                JoinType.LEFT_OUTER_JOIN);
        // criteria.createAlias("favorites.user", "user",
        // JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("artist.performances", "per",
                JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("per.album", "albumArtist",
                JoinType.LEFT_OUTER_JOIN);

        criteria.add(Restrictions.in("id", ids));
        // Distinct duplicate entity.
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Song> getSongListBySongIdsWithouRandomOrder(String mjuid,
            List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<Song>();
        }
        Criteria criteria = createCriteriaCommon(mjuid, ids);
        List<Song> songList = (List<Song>) criteria.list();
        return songList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Song> getSongListBySongIds(String mjuid, List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<Song>();
        }
        Criteria criteria = createCriteriaCommon(mjuid, ids);
        criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
        List<Song> songList = (List<Song>) criteria.list();
        return songList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Song findSongByProductId(String productId) {
        Criteria criteria = getCurrentSession().createCriteria(Song.class);
        criteria.add(Restrictions.eq("productId", productId));
        List<Song> l = criteria.list();
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Song> getSongListBySflag(String mjuid, List<String> songIdList) {
        if (CollectionUtils.isEmpty(songIdList)) {
            return new ArrayList<Song>();
        }
        Criteria criteriaFetchData = createCriteriaCommon(mjuid, songIdList);
        criteriaFetchData.addOrder(Order.desc("ratings.updateDatetime"));
        return criteriaFetchData.list();
    }

    private Criteria createCriteriaGroup() {
        Criteria criteria = getCurrentSession().createCriteria(Song.class);
        criteria.setProjection(Projections.property("id"));
        criteria.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("p.artist", "a", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("a.groups", "gr", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("gr.group", "group", JoinType.LEFT_OUTER_JOIN);
        return criteria;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getListIdByGenreId(String gid) {
        Criteria criteria = createCriteriaGroup();
        criteria.createAlias("group.genre", "genre", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("genre.id", gid));
        criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
        criteria.setProjection(Projections.distinct(Projections.property("id")));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getListIdByGroupIds(List<String> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return new ArrayList<String>();
        }
        Criteria criteria = createCriteriaGroup();
        criteria.add(Restrictions.in("group.id", groupIds));
        // criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
        criteria.setProjection(Projections.distinct(Projections.property("id")));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Song> getListByIdsWFetchArtistAuth(List<String> songIds) {
        if (CollectionUtils.isEmpty(songIds)) {
            return new ArrayList<Song>();
        }
        Criteria criteria = getCurrentSession().createCriteria(Song.class);
        criteria.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("p.artist", "a", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.in("id", songIds));
        criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
        // Distinct duplicate entity.
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
    
}
