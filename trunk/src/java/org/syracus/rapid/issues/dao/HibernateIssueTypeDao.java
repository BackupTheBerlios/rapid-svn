package org.syracus.rapid.issues.dao;

import java.util.List;

import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.issues.IssueType;

public class HibernateIssueTypeDao extends AbstractHibernateDao implements
		IIssueTypeDao {

	public void create(IssueType type) {
		getHibernateTemplate().save( type );
	}

	public void delete(IssueType type) {
		getHibernateTemplate().delete( type );
	}

	public IssueType find(Long id) {
		return( (IssueType)getHibernateTemplate().get( IssueType.class, id ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<IssueType> findAll() {
		List<IssueType> types = null;
		types = getHibernateTemplate().find(
				"FROM IssueType"
		);
		return( types );
	}

	public void update(IssueType type) {
		getHibernateTemplate().update( type );
	}

}
