/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.AlbumDao;
import jp.co.mti.mixjuke.dom.Album;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.AlbumService;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
public class AlbumServiceImpl extends AbstractService<Album> implements
        AlbumService {
    @Autowired
    private AlbumDao albumDao;

    public AlbumServiceImpl() {
        super(Album.class);
    }

    private Criteria createAlbumArtistCriteria(String artistID) {
        Criteria criteria = albumDao.createCriteria();
        criteria.createAlias("performances", "per", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("per.artist", "ar", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("ar.id", artistID));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @Override
    public Long getTotalAlbumByArtistId(String artistID) {
        Criteria criteria = createAlbumArtistCriteria(artistID);
        criteria.setProjection(Projections.distinct(Projections.property("id")));
        return (long) criteria.list().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> getAlbumListByArtistId(String artistID, int offset,
            int count) {
        Criteria criteria = createAlbumArtistCriteria(artistID);
        criteria.setProjection(Projections.distinct(Projections.property("id")));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        List<String> albumIds = criteria.list();
        if (albumIds.isEmpty()) {
            return new ArrayList<Album>();
        }
        Criteria criteriaAlbum = albumDao.createCriteria();
        criteriaAlbum.createAlias("performances", "per",
                JoinType.LEFT_OUTER_JOIN);
        criteriaAlbum.createAlias("per.artist", "ar", JoinType.LEFT_OUTER_JOIN);
        criteriaAlbum.add(Restrictions.in("id", albumIds));
        criteriaAlbum.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteriaAlbum.list();
    }
}
