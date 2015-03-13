/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.StreamAuthDao;
import jp.co.mti.mixjuke.dom.StreamAuthentication;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author ntnxuan
 * 
 */
@Repository("streamAuthenticationDao")
public class StreamAuthDaoImpl extends AbstractDao<StreamAuthentication>
		implements StreamAuthDao {

	protected StreamAuthDaoImpl() {
		super(StreamAuthentication.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.co.mti.mixjuke.dao.StreamAuthDao#checkRuleOneByProduct(java.lang.String)
	 */
	public Boolean checkRuleOneByProduct(String productId) {
		Criteria criteria = getCurrentSession().createCriteria(StreamAuthentication.class);
		criteria.createAlias("song", "s");
		criteria.add(Restrictions.like("s.productId", productId));

        // Rule 1: Start time = MAX ( CONTENT_RELEASE_DATE + WINDOW_DAYS, ANDROID_RELEASE_START_DATETIME )
        //         End time = ANDROID_RELEASE_END_DATETIME
		criteria.add(Restrictions.or(Restrictions.sqlRestriction("now() >= adddate(content_release_date, window_days)"),
				Restrictions.sqlRestriction("now() >= android_release_start_datetime")));
		criteria.add(Restrictions.sqlRestriction("now() <= android_release_end_datetime"));
		
		criteria.setProjection(Projections.rowCount());
		return ((Long)criteria.uniqueResult()).intValue() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.co.mti.mixjuke.dao.StreamAuthDao#checkRuleTwoByProductAndFlag(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean checkRuleTwoByProductAndFlag(String productId, String enableFlg) {
		Criteria criteria = getCurrentSession().createCriteria(StreamAuthentication.class);
		criteria.createAlias("song", "s");
		criteria.add(Restrictions.like("s.productId", productId));

        // Rule 2: Availability = ANDROID_{MEMBER_STATUS}_ENABLE_FLG | ANDROID_DELIVERY_ENABLE_FLG
		criteria.add(Restrictions.sqlRestriction(String.format("(android_delivery_enable_flg is true or android_%s_enable_flg is true)", enableFlg)));
		criteria.setProjection(Projections.rowCount());
		return ((Long)criteria.uniqueResult()).intValue() > 0;
	}

}
