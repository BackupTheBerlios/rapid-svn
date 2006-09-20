package org.syracus.rapid.issues.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.issues.IssueStatus;

public class HibernateIssueStatusDao extends AbstractHibernateDao implements
		IIssueStatusDao {

	protected static final transient Log log = LogFactory.getLog( HibernateIssueStatusDao.class );
	
	public void create(IssueStatus status) {
		getHibernateTemplate().save( status );
	}

	public void delete(IssueStatus status) {
		getHibernateTemplate().delete( status );
	}

	public IssueStatus find(Long id) {
		return( (IssueStatus)getHibernateTemplate().get( IssueStatus.class, id) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueStatus> findAll() {
		return( (List<IssueStatus>)getHibernateTemplate().find(
				"FROM IssueStatus"
		) );
	}
	
	@SuppressWarnings("unchecked")
	public IssueStatus findByOrder( Integer order ) {
		if ( log.isDebugEnabled() ) {
			log.debug( "[findByOrder] order = '" + order + "'" );
		}
		DetachedCriteria criteria = DetachedCriteria.forClass( IssueStatus.class );
		criteria.add( Restrictions.eq( "order", order ) )
			.addOrder( Order.asc( "order" ) );
		
		List<IssueStatus> defaults = (List<IssueStatus>)findByCriteria( criteria );
		if ( log.isDebugEnabled() ) {
			log.debug( "[findByOrder] resulting defaults:" );
			for( IssueStatus status : defaults ) {
				log.debug( "[findByOrder]   status = '" + status.getName() + "'" );
			}
		}
		return( (defaults.isEmpty()) ? null : (IssueStatus)defaults.get( 0 ) );
	}

	public void update(IssueStatus status) {
		getHibernateTemplate().update( status );
	}

}
