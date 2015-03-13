package javaz.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, ID extends Serializable> {

	public T save(T t);

	public T save(T t, boolean isFlush);

	public void update(T t);

	public void saveOrUpdate(T t);

	public T merge(T t);

	public void delete(T t);

	public void save(List<T> list);

	public T get(ID id);

	public T get(ID id, boolean lock);

	public T load(ID id);

	public T load(ID id, boolean lock);

	public List<T> findAll();

}
