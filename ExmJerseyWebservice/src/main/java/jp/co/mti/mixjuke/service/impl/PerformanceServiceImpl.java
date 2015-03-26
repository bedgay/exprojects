/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.List;

import jp.co.mti.mixjuke.dao.PerformanceDao;
import jp.co.mti.mixjuke.dom.GroupArtist;
import jp.co.mti.mixjuke.dom.Performance;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.PerformanceService;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
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
public class PerformanceServiceImpl extends AbstractService<Performance>
        implements PerformanceService {
    @Autowired
    PerformanceDao performanceDao;

    public PerformanceServiceImpl() {
        super(Performance.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Performance> getListBySongId(String sid) {
        Criteria criteria = performanceDao.createCriteria();
        criteria.createAlias("artist", "artist", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("song", "s");
        criteria.add(Restrictions.eq("s.id", sid));
        return criteria.list();
    }

    private Criteria createCriteriaGetListByAlbumId(String albumid) {
        Criteria criteria = performanceDao.createCriteria();
        criteria.add(Restrictions.eq("album.id", albumid));
        return criteria;
    }

    @Override
    public Long getTotalPerformanceListByAlbumId(String albumid) {
        Criteria criteria = createCriteriaGetListByAlbumId(albumid);
        criteria.setProjection(Projections.distinct(Projections
                .property("song.id")));
        return (long) criteria.list().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getSongIdsByAlbumId(String albumId, int offset,
            int count) {
        Criteria criteria = createCriteriaGetListByAlbumId(albumId);
        criteria.setProjection(Projections.distinct(Projections
                .property("song.id")));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        return criteria.list();
    }

    private Criteria createCriteriaGetListByArtistName(String[] artistName) {
        Criteria criteria = performanceDao.createCriteria("p");
        criteria.setProjection(Projections.property("ar.id"));
        criteria.createAlias("artist", "ar", JoinType.LEFT_OUTER_JOIN);
        Disjunction[] re = new Disjunction[artistName.length];
        for (int i = 0; i < artistName.length; i++) {
            String name = artistName[i];
            re[i] = Restrictions
                    .or(Restrictions.ilike("ar.name", name, MatchMode.ANYWHERE),
                            Restrictions.ilike("ar.nameKana", name,
                                    MatchMode.ANYWHERE), Restrictions.ilike(
                                    "ar.nameAlpha", name, MatchMode.ANYWHERE),
                            Restrictions.ilike("ar.freeword", name,
                                    MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.or(re));
        // artist must be in Artist_Group table
        DetachedCriteria sub = DetachedCriteria.forClass(GroupArtist.class,
                "ga");
        sub.add(Restrictions.eqProperty("ga.artist.id", "p.artist.id"));
        sub.setProjection(Projections.property("ga.artist.id"));
        criteria.add(Subqueries.exists(sub));

        criteria.setProjection(Projections.distinct(Projections
                .property("ar.id")));
        return criteria;
    }

    @Override
    public Long getTotalPerformanceByArtistName(String[] artistName) {
        Criteria criteria = createCriteriaGetListByArtistName(artistName);
        return (long) criteria.list().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getArtistIdByAritstName(String[] artistName,
            int offset, int count) {
        Criteria criteria = createCriteriaGetListByArtistName(artistName);
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        return criteria.list();
    }

}
