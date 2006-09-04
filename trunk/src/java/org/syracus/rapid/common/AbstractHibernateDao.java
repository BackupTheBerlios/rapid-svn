package org.syracus.rapid.common;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractHibernateDao extends HibernateDaoSupport implements IPersistanceDao {

	public List findByCriteria( DetachedCriteria criteria ) {
		return( getHibernateTemplate().findByCriteria( criteria ) );
	}
	
	public List findByCriteria( DetachedCriteria criteria, int first, int max ) {
		return( getHibernateTemplate().findByCriteria( criteria, first, max ) );
	}
	
	
}
