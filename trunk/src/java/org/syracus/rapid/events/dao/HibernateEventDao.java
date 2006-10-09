package org.syracus.rapid.events.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.events.Event;

public class HibernateEventDao extends GenericHibernateDao<Event, Long> implements
		IEventDao {

	
	@SuppressWarnings("unchecked")
	public List<Event> findByDate(Date date) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Event.class );
		criteria.add( Restrictions.and( 
				Restrictions.le("startDate", date),
				Restrictions.ge("endDate", date)
		) );
		return( (List<Event>)findByCriteria( criteria ) );
	}

	@SuppressWarnings("unchecked")
	public List<Event> findByDescription(String description) {
		return( (List<Event>)getHibernateTemplate().find(
				"FROM Event e WHERE e.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Event> findByEndDate(Date endDate) {
		return( (List<Event>)getHibernateTemplate().find(
				"FROM Event e WHERE e.endDate = ?",
				endDate
		) );		
	}

	@SuppressWarnings("unchecked")
	public List<Event> findByStartDate(Date startDate) {
		return( (List<Event>)getHibernateTemplate().find(
				"FROM Event e WHERE e.startDate = ?",
				startDate
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Event> findByTitle(String title) {
		return( (List<Event>)getHibernateTemplate().find(
				"FROM Event e WHERE e.title = ?",
				title
		) );
		
	}

	@SuppressWarnings("unchecked")
	public List<Event> findLikeDescription(String description) {
		return( (List<Event>)getHibernateTemplate().find(
				"FROM Event e WHERE e.description LIKE ?",
				description
		) );		
	}

	@SuppressWarnings("unchecked")
	public List<Event> findLikeTitle(String title) {
		return( (List<Event>)getHibernateTemplate().find(
				"FROM Event e WHERE e.title LIKE ?",
				title
		) );
	}

	
}
