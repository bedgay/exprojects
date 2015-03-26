package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.PurchaseOrderDao;
import jp.co.mti.mixjuke.dom.PurchaseOrder;

/**
 * @author natu
 * @date 2014-01-13
 */
@Repository("purchaseOrderDao")
public class PurchaseOrderDaoImpl extends AbstractDao<PurchaseOrder> implements PurchaseOrderDao  {

    public PurchaseOrderDaoImpl() {
        super(PurchaseOrder.class);
    }
    
}