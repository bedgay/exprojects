package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.PlaybackLog;

/**
 * User: nhphuoc
 * Date: 11/28/13
 * Time: 3:19 PM
 */
public interface PlaybackLogService extends MixjukeService<PlaybackLog> {
    /**
     * Check one log item is valid or not.
     * A valid log item is there is log item with same sessionId and productId
     * which is stored by API.19
     * @param sessionId the session id
     * @param productId the product id
     * @return true if log item is valid, false if log item is invalid.
     */
   public boolean isValidLogItem(String sessionId, String productId);
   
   /**
    * Check one log item is valid or not.
    * A valid log item is there is log item with same sessionId and productId
    * which is stored by API.19
    * @param sessionId the session id
    * @param productId the product id
    * @param playType the Play back log type
    * @return true if log item is valid, false if log item is invalid.
    */
  public boolean isValidLogItem(String sessionId, String productId, Integer playType);
  
  /**
   * Get first log by user id
   * @param uid the user id
   * @return list of log
   */
  public List<PlaybackLog> findLogByUID(String uid);
  
}
