/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.ArtistDao;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.ModelList;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.ArtistService;
import jp.co.mti.mixjuke.service.FavoriteService;
import jp.co.mti.mixjuke.service.PerformanceService;
import jp.co.mti.mixjuke.ws.response.ArtistInfo;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xuan Nguyen
 * 
 */
@Service
@Transactional
public class ArtistServiceImpl extends AbstractService<Artist> implements
        ArtistService {

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private PerformanceService performanceService;

    protected ArtistServiceImpl() {
        super(Artist.class);
    }

    @Override
    public List<Artist> getArtistListByProperty(String propertyName,
            List<String> list, String mjuid) {
        return artistDao.getArtistListByProperty(propertyName, list, mjuid);
    }

    @Override
    public List<Artist> getArtistListByPropertyWFetchFavorite(
            String propertyName, List<String> list) {

        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<Artist>();
        }
        Criterion criterion = Restrictions.in(propertyName, list);
        Criteria criteria = artistDao.createCriteria();
        criteria.createAlias("favorites", "favorites", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("performances", "p", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("p.album", "album", JoinType.LEFT_OUTER_JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(criterion);
        return artistDao.findByCriteria(criteria);
    }

    @Override
    public List<Artist> getFavoritedArtistListByUid(String userid, int offset,
            int count) {
        List<String> favoriteArtistIds = favoriteService.getFavoriteArtistIds(
                userid, offset, count);
        return artistDao.getFavoritedArtistListByUid(userid, favoriteArtistIds);
    }

    @Override
    public Artist getArtistByArtistId(String userid, String aid) {
        return artistDao.getArtistByArtistId(userid, aid);
    }

    @Override
    public ModelList<Artist> getArtistListBySearchName(String userid,
            String[] artistName, int offset, int count) {
        ModelList<Artist> list = new ModelList<Artist>();
        list.setTotal(performanceService
                .getTotalPerformanceByArtistName(artistName));
        List<String> artistIds = performanceService.getArtistIdByAritstName(
                artistName, offset, count);
        list.setList(artistDao.getArtistListByArtistIds(userid, artistIds,
                offset, count));
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jp.co.mti.mixjuke.service.ArtistService#getHotArtistListBySearch(java
     * .lang.String, int, int)
     */
    @Override
    public ModelList<Artist> getHotArtistListBySearch(String userid,
            int offset, int count) {
    	if (offset < 0 || count  <= 0) {
	        ModelList<Artist> mList = new ModelList<Artist>();
	        mList.setTotal(0L);
	        mList.setList(new ArrayList<Artist>());
	        return mList;
    	}
        return artistDao.getHotArtistListBySearch(userid, offset, count);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jp.co.mti.mixjuke.service.ArtistService#getArtistInfoList(java.lang.String
     * , java.util.List)
     */
    @Override
    public List<ArtistInfo> getArtistInfoList(String userId,
            List<Artist> reArtist) {
        List<ArtistInfo> artistInfos = new ArrayList<ArtistInfo>();
        for (Artist artist : reArtist) {
            artistInfos.add(artist.toArtistInfo(userId));
        }
        return artistInfos;
    }

}
