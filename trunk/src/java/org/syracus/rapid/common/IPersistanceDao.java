package org.syracus.rapid.common;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IPersistanceDao {

	public List findByCriteria( DetachedCriteria criteria );
	public List findByCriteria( DetachedCriteria criteria, int first, int max );
}
