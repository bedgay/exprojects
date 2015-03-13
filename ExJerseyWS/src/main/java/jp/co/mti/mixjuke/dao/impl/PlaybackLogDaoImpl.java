package jp.co.mti.mixjuke.dao.impl;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.PlaybackLogDao;
import jp.co.mti.mixjuke.dom.PlaybackLog;
import org.springframework.stereotype.Repository;

/**
 * User: nhphuoc
 * Date: 11/28/13
 * Time: 6:05 PM
 */
@Repository("playbackLogDao")
public class PlaybackLogDaoImpl extends AbstractDao<PlaybackLog> implements
        PlaybackLogDao {
    public PlaybackLogDaoImpl(){
        super(PlaybackLog.class);
    }
}
