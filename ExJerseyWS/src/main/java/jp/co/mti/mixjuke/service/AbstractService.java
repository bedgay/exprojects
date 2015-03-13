/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dao.MixjukeDao;
import jp.co.mti.mixjuke.dom.AbstractDomain;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xuan Nguyen
 * 
 */
@Transactional
public abstract class AbstractService<E extends AbstractDomain> implements
        MixjukeService<E>, ApplicationContextAware {

    protected MixjukeDao<E> mixjukeDao;

    protected ApplicationContext applicationContext;

    /**
     * The main entity class of this DAO.
     */
    private Class<E> entityClass;

    protected AbstractService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @SuppressWarnings("unchecked")
    protected MixjukeDao<E> findDao() {
        // Find the way to get name of E
        return (MixjukeDao<E>) applicationContext.getBean(StringUtils
                .uncapitalize(entityClass.getSimpleName()) + "Dao");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public E findById(String id) {
        return findDao().findById(id);
    }

    @Override
    public void saveOrUpdate(E e) {
        findDao().saveOrUpdate(e);
    }

    @Override
    public void save(E e) {
        findDao().save(e);
    }

    @Override
    public void merge(E e) {
        findDao().merge(e);
    }

    @Override
    public void delete(E e) {
        findDao().delete(e);
    }

    @Override
    public List<E> getList() {
        return findDao().getList();
    }

    @Override
    public List<E> getList(int offset, int count) {
        return findDao().getList(offset, count);
    }

    @Override
    public List<E> findByCriteria(Criterion criterion) {
        return findDao().findByCriteria(criterion);
    }

    @Override
    public boolean addList(List<E> list) {
        MixjukeDao<E> dao = findDao();
        try {
            for (E e : list) {
                dao.saveOrUpdate(e);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
