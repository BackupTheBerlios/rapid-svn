package org.syracus.rapid.components;

import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="projects"
 */
public class Project extends ABaseComponent {

	private Module module;
	private Project parent;
	private User leader;
	private String home;

	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
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
	 * @hibernate.many-to-one
	 */
	public Project getParent() {
		return parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
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
	
	
}
