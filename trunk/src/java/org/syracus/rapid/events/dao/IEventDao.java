package org.syracus.rapid.events.dao;

import java.util.Date;
import java.util.List;

import org.syracus.rapid.events.Event;

public interface IEventDao {

	public void create( Event event );
	public void update( Event event );
	public void delete( Event event );
	
	public Event find( Long id );
	public List<Event> findAll();
	public List<Event> findByStartDate( Date startDate );
	public List<Event> findByEndDate( Date endDate );
	public List<Event> findByDate( Date date );
	
	public List<Event> findByTitle( String title );
	public List<Event> findLikeTitle( String title );
	
	public List<Event> findByDescription( String description );
	public List<Event> findLikeDescription( String description );
	
	
}
