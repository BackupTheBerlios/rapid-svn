package org.syracus.rapid.history;

import java.util.Date;

import org.syracus.rapid.realm.User;

public class AbstractHistory {

	private Long id;
	private String text;
	private Date created;
	private User creator;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
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
	 * @hibernate.property type="text" not-null="true"
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
	 * @hibernate.id generator-class="native"
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
