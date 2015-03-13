/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.User;

/**
 * @author Xuan Nguyen
 * 
 */
public interface UserService extends MixjukeService<User> {

    /**
     * Get Users in specified array of id.
     * 
     * @param uids
     *            list of user id.
     * @return List of User object
     */
    public List<User> getUsersInList(List<String> uids);

    /**
     * Find specify User and fetch Favorite list. (Not use findById function
     * because it does fetch Favorite list).
     * 
     * @param uid
     *            User ID.
     * @return User object.
     */
    public User findByUidWFetchFavorite(String uid);

    /**
     * Find User by uid NOT mjuid.
     * 
     * @param uid
     *            User ID.
     * @return User object.
     */
    public User findByUid(String uid);
}
