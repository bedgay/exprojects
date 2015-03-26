package jp.co.mti.mixjuke.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.StreamDownloadDao;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamDownload;

/**
 * @author natu
 * @date 2014-01-16
 */
@Repository("streamDownloadDao")
public class StreamDownloadDaoImpl extends AbstractDao<StreamDownload> implements StreamDownloadDao {

    protected StreamDownloadDaoImpl() {
        super(StreamDownload.class);
    }

	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.dao.StreamDownloadDao#findBySongs(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StreamDownload> findBySongs(List<Song> songs) {
    	if (!CollectionUtils.isEmpty(songs)) {
	    	String hql = "select st from StreamDownload st, Song s where s.id in (:ids) and ((st.strRegionId=s.id and st.id = s.productId) or (st.dlRegionId=s.id and st.id = s.productId))";
	    	Query query = getCurrentSession().createQuery(hql);
	    	List<String> ids = new ArrayList<String>();
	    	for (Song song : songs) {
	    		ids.add(song.getId());
	    	}
	    	query.setParameterList("ids", ids);
			return query.list();
    	}
    	return null;
	}

}
