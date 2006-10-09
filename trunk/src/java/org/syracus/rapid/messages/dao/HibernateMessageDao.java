package org.syracus.rapid.messages.dao;

import java.util.List;

import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.messages.Message;
import org.syracus.rapid.messages.MessageStatus;
import org.syracus.rapid.realm.User;

public class HibernateMessageDao extends GenericHibernateDao<Message, Long> implements
		IMessageDao {

	

	

	@SuppressWarnings("unchecked")
	public List<Message> findByBody(String body) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.body = ?",
				body
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByReceiver(User receiver) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.receiver = ?",
				receiver
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findBySender(User sender) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.sender = ?",
				sender
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findBySubject(String subject) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.subject = ?",
				subject
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findLikeBody(String body) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.body LIKE ?",
				body
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findLikeSubject(String subject) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.subject LIKE ?",
				subject
		) );
	}

	

	@SuppressWarnings("unchecked")
	public List<Message> findByStatus(MessageStatus status) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.status = ?",
				status
		) );
	}

	
}
