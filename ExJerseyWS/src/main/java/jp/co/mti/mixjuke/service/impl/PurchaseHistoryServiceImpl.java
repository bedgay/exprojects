package jp.co.mti.mixjuke.service.impl;

import jp.co.mti.mixjuke.dao.PurchaseHistoryDao;
import jp.co.mti.mixjuke.dom.PurchaseHistory;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.PurchaseHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author natu
 * @date 2014-01-07
 */
@Service
@Transactional
public class PurchaseHistoryServiceImpl extends AbstractService<PurchaseHistory> implements PurchaseHistoryService {
	
	@Autowired
	private PurchaseHistoryDao purchaseHistoryDao;

    protected PurchaseHistoryServiceImpl() {
        super(PurchaseHistory.class);
    }

}
