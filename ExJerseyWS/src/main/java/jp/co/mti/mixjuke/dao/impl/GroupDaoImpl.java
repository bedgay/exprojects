/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.GroupDao;
import jp.co.mti.mixjuke.dom.Group;

/**
 * @author ntnxuan
 *
 */
@Repository("groupDao")
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    protected GroupDaoImpl() {
        super(Group.class);
    }

}
