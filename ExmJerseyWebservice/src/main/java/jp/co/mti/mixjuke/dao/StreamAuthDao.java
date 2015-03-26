/**
 * 
 */
package jp.co.mti.mixjuke.dao;

import jp.co.mti.mixjuke.dom.StreamAuthentication;

/**
 * @author ntnxuan
 *
 */
public interface StreamAuthDao extends MixjukeDao<StreamAuthentication> {
	
	/**
	 * @param productId
	 * @return
	 */
	Boolean checkRuleOneByProduct(String productId);
	
	/**
	 * @param productId
	 * @param enableFlg
	 * @return
	 */
	Boolean checkRuleTwoByProductAndFlag(String productId, String enableFlg);
	
}
