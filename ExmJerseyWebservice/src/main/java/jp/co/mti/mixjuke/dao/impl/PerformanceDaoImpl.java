/**
 * 
 */
package jp.co.mti.mixjuke.dao.impl;

import org.springframework.stereotype.Repository;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.PerformanceDao;
import jp.co.mti.mixjuke.dom.Performance;

/**
 * @author ntnxuan
 * 
 */
@Repository("performanceDao")
public class PerformanceDaoImpl extends AbstractDao<Performance> implements
        PerformanceDao {

    public PerformanceDaoImpl() {
        super(Performance.class);
    }

}
