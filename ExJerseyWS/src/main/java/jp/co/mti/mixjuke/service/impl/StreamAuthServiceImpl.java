/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.List;

import jp.co.mti.mixjuke.dao.StreamAuthDao;
import jp.co.mti.mixjuke.dom.StreamAuthentication;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.StreamAuthService;
import jp.co.mti.mixjuke.ws.request.MemberStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author ntnxuan
 * 
 */
@Service
@Transactional
public class StreamAuthServiceImpl extends
		AbstractService<StreamAuthentication> implements StreamAuthService {

	private static final int PAYMENT_PLAY_TYPE = 0;
	private static final int TRIAL_PLAY_TYPE = 1;
	
	private static final String STATUS_FREE = "free";
	private static final String STATUS_PAYING = "paying";
	private static final String STATUS_TRIAL = "trial";

	private static final Integer[] PLAY_TYPES = new Integer[] { PAYMENT_PLAY_TYPE, TRIAL_PLAY_TYPE };

	@Autowired
	StreamAuthDao streamAuthDao;

	protected StreamAuthServiceImpl() {
		super(StreamAuthentication.class);
	}

	@Override
	public List<StreamAuthentication> getListByIds(List<String> ids) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.co.mti.mixjuke.service.StreamAuthService#checkRuleOne(java.lang.String
	 * )
	 */
	@Override
	public Boolean checkRuleOneByProduct(String productId) {
		return streamAuthDao.checkRuleOneByProduct(productId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.co.mti.mixjuke.service.StreamAuthService#checkRuleTwoByProductAndFlag(java.lang.String
	 * , short)
	 */
	@Override
	public Boolean checkRuleTwoByProductAndFlag(String productId, MemberStatus memberStatus) {
		String enableFlg = "";
		switch (memberStatus.getStatus()) {
		case 1:
			enableFlg = STATUS_FREE;
			break;
		case 2:
			enableFlg = STATUS_PAYING;
			break;
		case 3:
			enableFlg = STATUS_TRIAL;
			break;
		}

		if (StringUtils.isEmpty(enableFlg)) {
			return Boolean.FALSE;
		} else {
			return streamAuthDao.checkRuleTwoByProductAndFlag(productId, enableFlg);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.co.mti.mixjuke.service.StreamAuthService#checkRuleThree(java.lang.
	 * Integer, short)
	 */
	@Override
	public Boolean checkRuleThree(Integer playType, MemberStatus memberStatus) {
		if (CollectionUtils.arrayToList(PLAY_TYPES).indexOf(playType) < 0) {
			return Boolean.FALSE;
		} else if (playType == PAYMENT_PLAY_TYPE && memberStatus.equals(MemberStatus.FREE)) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

}
