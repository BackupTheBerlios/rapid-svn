package org.syracus.rapid.issues;

import java.util.Date;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="issues"
 */
public class Issue {

	private Long id;
	
	private Module module;
	private Project project;
	private Component component;
	
	private String summary;
	private String description;
	
	private String type;
	private String status;
	private String priority;
	
	private User assignee;
	private Date assigned;
	
	private User reporter;
	private Date reported;
	
	private User creator;
	private Date created;
	private User modifier;
	private Date modified;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public Date getAssigned() {
		return assigned;
	}
	public void setAssigned(Date assigned) {
		this.assigned = assigned;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public Date getReported() {
		return reported;
	}
	public void setReported(Date reported) {
		this.reported = reported;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
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
	 * @hibernate.property
	 */
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
