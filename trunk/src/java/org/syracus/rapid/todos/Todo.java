package org.syracus.rapid.todos;

import java.util.Date;

import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="todos"
 */
public class Todo {

	private Long id;
	private String summary;
	private String description;
	private boolean done = false;
	
	private User owner;
	
	private Date created;
	private Date modified;
	
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
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
	 * @hibernate.property type="text"
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	 * @hibernate.property
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
	 * @hibernate.many-to-one
	 */
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
}
