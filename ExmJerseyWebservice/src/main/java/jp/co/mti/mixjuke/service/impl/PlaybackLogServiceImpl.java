package jp.co.mti.mixjuke.service.impl;

import java.util.List;

import jp.co.mti.mixjuke.dao.PlaybackLogDao;
import jp.co.mti.mixjuke.dom.PlaybackLog;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.PlaybackLogService;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * User: nhphuoc
 * Date: 11/28/13
 * Time: 3:26 PM
 */
@Service
@Transactional
public class PlaybackLogServiceImpl extends AbstractService<PlaybackLog> implements
        PlaybackLogService {
	
//    private static final int VALID_LOG_TYPE = 0;
//	private static final int VALID_PLAY_SEC = 0;
	
	@Autowired
    PlaybackLogDao playbackLogDao;
    public PlaybackLogServiceImpl(){
        super(PlaybackLog.class);
    }

//    /* (non-Javadoc)
//     * @see jp.co.mti.mixjuke.service.PlaybackLogService#isValidLogItem(java.lang.String, java.lang.String)
//     */
//    public boolean isValidLogItem(String sessionId, String productId){
//        Criteria criteria =playbackLogDao.createCriteria();
//        criteria.add(Restrictions.eq("sessionId", sessionId));
//        criteria.add(Restrictions.eq("productId", productId));
//        criteria.add(Restrictions.eq("logType", VALID_LOG_TYPE));
//        criteria.add(Restrictions.eq("playSec", VALID_PLAY_SEC));
//        List<PlaybackLog> logs = playbackLogDao.findByCriteria(criteria);
//        return !logs.isEmpty();
//    }
//    
//    /* (non-Javadoc)
//     * @see jp.co.mti.mixjuke.service.PlaybackLogService#isValidLogItem(java.lang.String, java.lang.String, java.lang.Integer)
//     */
//    public boolean isValidLogItem(String sessionId, String productId, Integer playType){
//        Criteria criteria =playbackLogDao.createCriteria();
//        criteria.add(Restrictions.eq("sessionId", sessionId));
//        criteria.add(Restrictions.eq("productId", productId));
//        criteria.add(Restrictions.eq("logType", playType));
//        criteria.add(Restrictions.eq("playSec", VALID_PLAY_SEC));
//        List<PlaybackLog> logs = playbackLogDao.findByCriteria(criteria);
//        return !logs.isEmpty();
//    }
    

	@Override
	public List<PlaybackLog> findLogByUID(String uid) {
        Criteria criteria =playbackLogDao.createCriteria();
        criteria.add(Restrictions.eq("uid", uid));
        return playbackLogDao.findByCriteria(criteria);
	}

	@Override
	public boolean isValidLogItem(String sessionId, String productId) {
		return Boolean.TRUE;
	}

	@Override
	public boolean isValidLogItem(String sessionId, String productId,
			Integer playType) {
		return Boolean.TRUE;
	}
    
    
}
