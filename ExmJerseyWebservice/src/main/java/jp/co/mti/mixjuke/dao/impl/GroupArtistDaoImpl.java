/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.GroupArtistDao;
import jp.co.mti.mixjuke.dom.GroupArtist;

/**
 * @author ntnxuan
 * 
 */
@Repository("groupArtistDao")
public class GroupArtistDaoImpl extends AbstractDao<GroupArtist> implements
        GroupArtistDao {

    protected GroupArtistDaoImpl() {
        super(GroupArtist.class);
    }

}
