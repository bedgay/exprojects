package jp.co.mti.mixjuke.service.impl;

import jp.co.mti.mixjuke.dao.PurchaseDao;
import jp.co.mti.mixjuke.dom.Purchase;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author natu
 * @date 2014-01-07
 */
@Service
@Transactional
public class PurchaseServiceImpl extends AbstractService<Purchase> implements PurchaseService {
	
	@Autowired
	private PurchaseDao purchaseDao;

    protected PurchaseServiceImpl() {
        super(Purchase.class);
    }

}
