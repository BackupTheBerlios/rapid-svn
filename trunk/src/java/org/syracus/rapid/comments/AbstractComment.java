package org.syracus.rapid.comments;

import java.util.Date;

import org.syracus.rapid.realm.User;

public class AbstractComment {

	private Long id;

	private String subject;
	private String text;
	
	private User creator;
	private Date created;
	private User modifier;
	private Date modified;
	
	
	/**
	 * 
	 * @return
	 * hibernate.property
	 */
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 
	 * @return
	 * hibernate.many-to-one lazy="true"
	 */
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
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
	 * hibernate.property
	 */
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	/**
	 * 
	 * @return
	 * hibernate.many-to-one lazy="true"
	 */
	public User getModifier() {
		return modifier;
	}
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	/**
	 * 
	 * @return
	 * hibernate.property
	 */
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 
	 * @return
	 * hibernate.property type="text"
	 */
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
