package org.syracus.rapid.events;

import java.util.List;

public interface IEventService {

	public void addEventType( EventType type );
	public void updateEventType( EventType type );
	public void deleteEventType( EventType type );
	
	public EventType getEventTypeById( Long id );
	public List<EventType> getAllEventTypes();
	
	
	public void addEvent( Event event );
	public void updateEvent( Event event );
	public void deleteEvent( Event event );
	
	public Event getEventById( Long id );
	public List<Event> getAllEvents();
	
}
