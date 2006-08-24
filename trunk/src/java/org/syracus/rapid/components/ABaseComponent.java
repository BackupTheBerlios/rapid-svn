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
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public User getModifier() {
		return modifier;
	}
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
