/**
 * 
 */
package jp.co.mti.mixjuke.dao;

import java.util.List;

import jp.co.mti.mixjuke.dom.AbstractDomain;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

/**
 * @author Xuan Nguyen
 * 
 */
public interface MixjukeDao<E extends AbstractDomain> {

    /**
     * Find an object by specify id
     * 
     * @param id
     * @return Object
     */
    E findById(String id);

    /**
     * Save object if did exist Update object if exist already
     * 
     * @param specify
     *            object
     */
    void saveOrUpdate(E e);

    /**
     * Delete specify object
     * 
     * @param specify
     *            object
     */

    void save(E e);

    void merge(E e);

    void delete(E e);

    /**
     * Find by specify criteria
     * 
     * @param criterion
     *            indicate where clause.
     * @return
     */
    List<E> findByCriteria(Criterion criterion);

    /**
     * Get all object in DB
     * 
     * @return List object
     */
    List<E> getList();

    List<E> getList(int offset, int count);

    /**
     * create Criteria from DAO.
     * 
     * @return Criteria object.
     */
    Criteria createCriteria();

    /**
     * create Criteria with specific alias
     * 
     * @param alias
     * @return Criteria object.
     */
    Criteria createCriteria(String alias);

    /**
     * Find by specify criteria
     * 
     * @param criteria
     *            indicate where clause and fetch mode (if need).
     * @return
     */
    List<E> findByCriteria(Criteria critera);
}
