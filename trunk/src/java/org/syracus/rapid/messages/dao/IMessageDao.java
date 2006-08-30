package org.syracus.rapid.messages.dao;

import java.util.List;

import org.syracus.rapid.messages.Message;
import org.syracus.rapid.realm.User;

public interface IMessageDao {

	public void create( Message message );
	public void update( Message message );
	public void delete( Message message );
	
	public Message find( Long id );
	
	public List<Message> findAll();
	public List<Message> findBySender( User sender );
	public List<Message> findByReceiver( User receiver );
	
	public List<Message> findBySubject( String subject );
	public List<Message> findLikeSubject( String subject );
	public List<Message> findByBody( String body );
	public List<Message> findLikeBody( String body );
	
}
