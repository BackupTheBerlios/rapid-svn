package org.syracus.rapid.issues.dao;

import java.util.List;

import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.issues.IssuePriority;

public class HibernateIssuePriorityDao extends AbstractHibernateDao implements
		IIssuePriorityDao {

	public void create(IssuePriority priority) {
		getHibernateTemplate().save( priority );
	}

	public void delete(IssuePriority priority) {
		getHibernateTemplate().delete( priority );
	}

	public IssuePriority find(Long id) {
		return( (IssuePriority)getHibernateTemplate().get( IssuePriority.class, id) );
	}

	@SuppressWarnings("unchecked")
	public List<IssuePriority> findAll() {
		return( (List<IssuePriority>)getHibernateTemplate().find(
				"FROM IssuePriority"
		) );
	}

	public void update(IssuePriority priority) {
		getHibernateTemplate().update( priority );
	}

}
