package org.syracus.rapid.messages.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.messages.Message;
import org.syracus.rapid.messages.MessageStatus;
import org.syracus.rapid.realm.User;

public class HibernateMessageDao extends AbstractHibernateDao implements
		IMessageDao {

	public void create(Message message) {
		getHibernateTemplate().save( message );
	}

	public void delete(Message message) {
		getHibernateTemplate().delete( message );
	}

	public Message find(Long id) {
		return( (Message)getHibernateTemplate().get( Message.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findAll() {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message"
		) );
	}

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

	public void update(Message message) {
		getHibernateTemplate().update( message );
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByStatus(MessageStatus status) {
		return( (List<Message>)getHibernateTemplate().find(
				"FROM Message m WHERE m.status = ?",
				status
		) );
	}

	
}
