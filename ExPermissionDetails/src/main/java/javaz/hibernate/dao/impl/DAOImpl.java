package javaz.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javaz.hibernate.dao.DAO;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:15:59 AM
 * @param <T>
 * @param <ID>
 */
@SuppressWarnings("unchecked")
public abstract class DAOImpl<T, ID extends Serializable> implements DAO<T, ID> {
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> model;

	protected Class<T> getModel() {
		if (this.model == null) {
			this.model = (Class<T>) ((ParameterizedType) (getClass()
					.getGenericSuperclass())).getActualTypeArguments()[0];
		}
		return this.model;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria getCriteria() {
		return getSession().createCriteria(getModel());
	}

	protected List<T> findByCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getModel());

		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}

		return criteria.list();
	}

	protected List<T> findByHql(String hql) {
		return getSession().createQuery(hql).list();
	}
	
	protected T getByHql(String hql) {
		List<T> list = findByHql(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public T save(T t) {
		return load((ID) getSession().save(t));
	}

	public T save(T t, boolean isFlush) {
		t = load((ID) getSession().save(t));
		if (isFlush) {
			getSession().flush();
			getSession().refresh(t);
		}
		return t;
	}

	public void update(T t) {
		getSession().update(t);
	}

	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	public T merge(T t) {
		return (T) getSession().merge(t);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void save(List<T> list) {
		for (T t : list) {
			save(t);
		}
	}

	public T get(ID id) {
		return (T) getSession().get(getModel(), id);
	}

	public T get(ID id, boolean lock) {
		return (T) getSession().get(getModel(), id, LockOptions.UPGRADE);
	}

	public T load(ID id) {
		return (T) getSession().load(getModel(), id);
	}

	public T load(ID id, boolean lock) {
		return (T) getSession().load(getModel(), id, LockOptions.UPGRADE);
	}

	public List<T> findAll() {
		return findByCriteria();
	}

}
