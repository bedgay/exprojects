/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.UserDao;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.UserService;

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
public class UserServiceImpl extends AbstractService<User> implements
        UserService {

    @Autowired
    UserDao userDao;

    protected UserServiceImpl() {
        super(User.class);
    }

    @Override
    public List<User> getUsersInList(List<String> uids) {
        if (CollectionUtils.isEmpty(uids)) {
            return new ArrayList<User>();
        }
        Criterion criterion = Restrictions.in("id", uids);
        return userDao.findByCriteria(criterion);
    }

    @Override
    public User findByUidWFetchFavorite(String uid) {
        Criteria criteria = userDao.createCriteria();
        criteria.add(Restrictions.eq("uid", uid));
        criteria.createAlias("favorites", "favorites", JoinType.LEFT_OUTER_JOIN);
        if (userDao.findByCriteria(criteria).isEmpty()) {
            return null;
        }
        return userDao.findByCriteria(criteria).get(0);
    }

    @Override
    public User findByUid(String uid) {
        Criterion criterion = Restrictions.eq("uid", uid);
        List<User> list = userDao.findByCriteria(criterion);
        if (list.isEmpty())
            return null;
        return list.get(0);
    }
}
