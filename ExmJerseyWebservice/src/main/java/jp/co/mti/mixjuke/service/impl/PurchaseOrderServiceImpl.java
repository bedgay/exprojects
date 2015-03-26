package jp.co.mti.mixjuke.service.impl;

import jp.co.mti.mixjuke.dao.PurchaseOrderDao;
import jp.co.mti.mixjuke.dom.PurchaseOrder;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.PurchaseOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author natu
 * @date 2014-01-13
 */
@Service
@Transactional
public class PurchaseOrderServiceImpl extends AbstractService<PurchaseOrder> implements PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;

    protected PurchaseOrderServiceImpl() {
        super(PurchaseOrder.class);
    }
}
