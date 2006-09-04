package org.syracus.rapid.messages;

import java.util.List;

import org.syracus.rapid.realm.User;

public interface IMessageService {

	public void sendMessage( String subject, String body, User sender, User receiver );
	
	public List<Message> getReceivedMessages( User receiver );
	public List<Message> getSendMessages( User sender );
	
	public List<Message> getNewestMessages( User receiver, int max );
	public List<Message> getNewestMessagesWithStatus( User receiver, MessageStatus status, int max );
	
}
