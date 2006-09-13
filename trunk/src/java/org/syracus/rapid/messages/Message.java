package org.syracus.rapid.messages;

import java.util.Date;

import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="messages"
 */
public class Message {

	private Long id;
	private User sender;
	private User receiver;
	
	private String subject;
	private String body;
	
	private Date send;
	private String status;

	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 * @hibernate.property type="text"
	 */
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 
	 * @return
	 * @hibernate.id generator-class="native"
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true"
	 */
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	/**
	 * 
	 * @return
	 * @hibernate.property not-null="true"
	 */
	public Date getSend() {
		return send;
	}

	public void setSend(Date send) {
		this.send = send;
	}

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true"
	 */
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
