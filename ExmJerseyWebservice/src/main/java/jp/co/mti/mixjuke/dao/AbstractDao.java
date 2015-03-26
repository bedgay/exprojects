package jp.co.mti.mixjuke.dao;

import java.util.List;

import jp.co.mti.mixjuke.dom.AbstractDomain;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<E extends AbstractDomain> implements
        MixjukeDao<E> {
    /**
     * The main entity class of this DAO.
     */
    private Class<E> entityClass;

    protected AbstractDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public E findById(String id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

    public void save(E e) {
        getCurrentSession().save(e);
    }

    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    public void merge(E e) {
        getCurrentSession().merge(e);
    }

    @SuppressWarnings("unchecked")
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<E> getList() {
        String hql = "from " + entityClass.getSimpleName();
        List<E> list = (List<E>) getCurrentSession().createQuery(hql).list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<E> getList(int offset, int count) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.setFirstResult(offset);
        criteria.setMaxResults(count);
        return criteria.list();
    }

    public Criteria createCriteria() {
        return getCurrentSession().createCriteria(entityClass);
    }

    @Override
    public Criteria createCriteria(String alias) {
        return getCurrentSession().createCriteria(entityClass, alias);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByCriteria(Criteria critera) {
        return critera.list();
    }
}
