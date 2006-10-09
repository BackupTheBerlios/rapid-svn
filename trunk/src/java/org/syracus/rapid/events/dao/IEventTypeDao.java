package org.syracus.rapid.events.dao;

import java.util.List;

import org.syracus.rapid.events.EventType;

public interface IEventTypeDao {

	public void create( EventType type );
	public void update( EventType type );
	public void delete( EventType type );
	
	public EventType find( Long id );
	
	public List<EventType> findAll();
	public List<EventType> findByName( String name );
	public List<EventType> findLikeName( String name );
	public List<EventType> findByDescription( String description );
	public List<EventType> findLikeDescription( String description );
	
}
