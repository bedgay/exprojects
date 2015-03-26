/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.UserDao;
import jp.co.mti.mixjuke.dom.User;

import org.springframework.stereotype.Repository;

/**
 * @author Xuan Nguyen
 * 
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

}
