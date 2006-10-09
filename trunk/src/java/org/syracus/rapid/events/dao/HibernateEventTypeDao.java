package org.syracus.rapid.events.dao;

import java.util.List;

import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.events.EventType;

public class HibernateEventTypeDao extends GenericHibernateDao<EventType, Long> implements
		IEventTypeDao {

	@SuppressWarnings("unchecked")
	public List<EventType> findByDescription(String description) {
		return( (List<EventType>)getHibernateTemplate().find(
				"FROM EventType t WHERE t.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<EventType> findByName(String name) {
		return( (List<EventType>)getHibernateTemplate().find(
				"FROM EventType t WHERE t.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<EventType> findLikeDescription(String description) {
		return( (List<EventType>)getHibernateTemplate().find(
				"FROM EventType t WHERE t.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<EventType> findLikeName(String name) {
		return( (List<EventType>)getHibernateTemplate().find(
				"FROM EventType t WHERE t.name LIKE ?",
				name
		) );
	}

}
