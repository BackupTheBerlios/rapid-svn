package org.syracus.rapid.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IGenericHibernateDao<T, ID extends Serializable> extends IGenericDao<T,ID> {

	public List<T> findByExample( T instance );
	public List<T> findByExample( T instance, String[] excludeProperties );
	public List<T> findByCriteria( DetachedCriteria criteria );
	public List<T> findByCriteria( DetachedCriteria criteria, int first, int max );
	public DetachedCriteria createCriteria();
}
