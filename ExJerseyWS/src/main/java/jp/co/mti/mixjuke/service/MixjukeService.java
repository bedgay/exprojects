/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import org.hibernate.criterion.Criterion;

import jp.co.mti.mixjuke.dom.AbstractDomain;

/**
 * @author Xuan Nguyen
 * 
 */
public interface MixjukeService<E extends AbstractDomain> {
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
     * Save specify object
     * 
     * @param specify
     *            object
     */

    void save(E e);

    /**
     * Merge specify object
     * 
     * @param specify
     *            object
     */

    void merge(E e);

    /**
     * Delete specify object
     * 
     * @param specify
     *            object
     */

    void delete(E e);

    /**
     * Merge specify object
     * 
     * @param specify
     *            object
     */

    /**
     * get all object in DB
     * 
     * @return list object
     */
    List<E> getList();

    List<E> getList(int offset, int count);

    /**
     * Find list base on specify criteria
     * 
     * @param criterion
     * @return list object
     */
    List<E> findByCriteria(Criterion criterion);

    boolean addList(List<E> list);
}
