/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import jp.co.mti.mixjuke.dom.Group;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.GroupService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ntnxuan
 * 
 */
@Service
@Transactional
public class GroupServiceImpl extends AbstractService<Group> implements
        GroupService {

    protected GroupServiceImpl() {
        super(Group.class);
    }

}
