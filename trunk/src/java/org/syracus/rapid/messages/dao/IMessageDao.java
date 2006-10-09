package org.syracus.rapid.messages.dao;

import java.util.List;

import org.syracus.rapid.common.IGenericHibernateDao;
import org.syracus.rapid.messages.Message;
import org.syracus.rapid.messages.MessageStatus;
import org.syracus.rapid.realm.User;

public interface IMessageDao extends IGenericHibernateDao<Message,Long> {

	
	public List<Message> findBySender( User sender );
	public List<Message> findByReceiver( User receiver );
	
	public List<Message> findBySubject( String subject );
	public List<Message> findLikeSubject( String subject );
	public List<Message> findByBody( String body );
	public List<Message> findLikeBody( String body );
	
	public List<Message> findByStatus( MessageStatus status );
	
}
