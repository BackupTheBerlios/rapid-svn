package org.syracus.rapid.events;

import java.util.List;

import org.syracus.rapid.events.dao.IEventDao;
import org.syracus.rapid.events.dao.IEventTypeDao;

public class EventService implements IEventService {

	private IEventTypeDao typeDao;
	private IEventDao eventDao;
	
	
	public IEventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(IEventDao eventDao) {
		this.eventDao = eventDao;
	}

	public IEventTypeDao getTypeDao() {
		return typeDao;
	}

	public void setTypeDao(IEventTypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void addEvent(Event event) {
		getEventDao().create( event );
	}

	public void addEventType(EventType type) {
		getTypeDao().create(type );
	}

	public void deleteEvent(Event event) {
		getEventDao().delete( event );
	}

	public void deleteEventType(EventType type) {
		getTypeDao().delete( type );
	}

	public List<EventType> getAllEventTypes() {
		return( getTypeDao().findAll() );
	}

	public List<Event> getAllEvents() {
		return( getEventDao().findAll() );
	}

	public Event getEventById(Long id) {
		return( getEventDao().find( id ) );
	}

	public EventType getEventTypeById(Long id) {
		return( getTypeDao().find( id ) );
	}

	public void updateEvent(Event event) {
		getEventDao().update( event );
	}

	public void updateEventType(EventType type) {
		getTypeDao().update( type );
	}

}
