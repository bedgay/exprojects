package jp.co.mti.mixjuke.dao.impl;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.PurchaseDao;
import jp.co.mti.mixjuke.dom.Purchase;

import org.springframework.stereotype.Repository;

/**
 * @author natu
 * @date 2014-01-07
 */
@Repository("purchaseDao")
public class PurchaseDaoImpl extends AbstractDao<Purchase> implements PurchaseDao  {

    public PurchaseDaoImpl() {
        super(Purchase.class);
    }
    
}
