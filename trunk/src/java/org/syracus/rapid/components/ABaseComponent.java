package org.syracus.rapid.components;

import java.util.Date;

import org.syracus.rapid.realm.User;

public class ABaseComponent {

	private Long id;
	private String name;
	private String description;
	private User creator;
	private Date created;
	private User modifier;
	private Date modified;
	
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
	 * @hibernate.property
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
	public User getModifier() {
		return modifier;
	}
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
