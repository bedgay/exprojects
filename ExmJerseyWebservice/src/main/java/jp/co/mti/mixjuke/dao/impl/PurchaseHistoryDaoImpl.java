package jp.co.mti.mixjuke.dao.impl;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.PurchaseHistoryDao;
import jp.co.mti.mixjuke.dom.PurchaseHistory;

import org.springframework.stereotype.Repository;

/**
 * @author natu
 * @date 2014-01-07
 */
@Repository("purchaseHistoryDao")
public class PurchaseHistoryDaoImpl extends AbstractDao<PurchaseHistory> implements PurchaseHistoryDao  {

    public PurchaseHistoryDaoImpl() {
        super(PurchaseHistory.class);
    }
    
}
