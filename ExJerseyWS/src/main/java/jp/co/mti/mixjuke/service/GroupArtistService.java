/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.GroupArtist;

/**
 * @author ntnxuan
 * 
 */
public interface GroupArtistService extends MixjukeService<GroupArtist> {
    /**
     * Get all group that specify artist belong to.
     * 
     * @param aids
     *            Artist id list.
     * @return group id list.
     */
    List<String> getGroupIdsByArtistIds(List<String> aids);

    /**
     * Get all artist that belong to specify group.
     * 
     * @param groupIds
     *            Group id list.
     * @return
     */
    List<GroupArtist> getArtistIdsByGroupIds(List<String> groupIds);
}
