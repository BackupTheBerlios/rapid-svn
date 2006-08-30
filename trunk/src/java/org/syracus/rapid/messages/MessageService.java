package org.syracus.rapid.messages;

import java.util.Date;
import java.util.List;

import org.syracus.rapid.messages.dao.IMessageDao;
import org.syracus.rapid.realm.User;

public class MessageService implements IMessageService {

	private IMessageDao messageDao;
	
	
	public IMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public List<Message> getReceivedMessages(User receiver) {
		return( getMessageDao().findByReceiver( receiver ) );
	}

	public List<Message> getSendMessages(User sender) {
		return( getMessageDao().findBySender( sender ) );
	}

	public void sendMessage(String subject, String body, User sender,
			User receiver) {
		Message message = new Message();
		message.setSubject( subject );
		message.setBody( body );
		sendMessage( message, sender, receiver );
	}
	
	public void sendMessage( Message message, User sender, User receiver ) {
		message.setSender( sender );
		message.setReceiver( receiver );
		message.setSend( new Date() );
		getMessageDao().create( message );
	}

}
