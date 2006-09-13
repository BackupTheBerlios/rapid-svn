package org.syracus.rapid.components;

import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="components"
 */
public class Component extends ABaseComponent {

	private Module module;
	private Project project;
	private User leader;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true"
	 */
	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="false"
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
	 * @hibernate.many-to-one not-null="false"
	 */
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
