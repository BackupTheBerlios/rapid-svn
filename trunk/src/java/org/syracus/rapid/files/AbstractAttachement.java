package org.syracus.rapid.files;

import org.syracus.rapid.realm.User;

public abstract class AbstractAttachement extends AbstractFile {

	private String description;
	private User creator;

	/**
	 * 
	 * @return
	 * @hibernate.property type="text" not-null="false"
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
	 * @hibernate.many-to-one
	 */
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
}
