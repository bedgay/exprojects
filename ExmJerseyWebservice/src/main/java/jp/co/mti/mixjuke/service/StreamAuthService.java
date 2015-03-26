/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.StreamAuthentication;
import jp.co.mti.mixjuke.ws.request.MemberStatus;

/**
 * @author ntnxuan
 * 
 */
public interface StreamAuthService extends MixjukeService<StreamAuthentication> {

    List<StreamAuthentication> getListByIds(List<String> ids);
    
    /**
     * @param productId
     * @return
     */
    Boolean checkRuleOneByProduct(String productId);
    
    /**
     * @param productId
     * @param memberStatus
     * @return
     */
    Boolean checkRuleTwoByProductAndFlag(String productId, MemberStatus memberStatus);
    
    /**
     * @param playType
     * @param memberStatus
     * @return
     */
    Boolean checkRuleThree(Integer playType, MemberStatus memberStatus);
    
}
