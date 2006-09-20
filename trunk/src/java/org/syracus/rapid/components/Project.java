package org.syracus.rapid.components;

import java.util.Set;

import org.syracus.rapid.files.ProjectAttachement;
import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="projects"
 */
public class Project extends ABaseComponent {

	private Module module;
	private User leader;
	private String home;
	private Set<ProjectAttachement> attachements;
	
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" cascade="all-delete-orphan" inverse="true"
	 * @hibernate.key column="project"
	 * @hibernate.one-to-many class="org.syracus.rapid.files.ProjectAttachement"/>
	 */
	public Set<ProjectAttachement> getAttachements() {
		return attachements;
	}
	public void setAttachements(Set<ProjectAttachement> attachements) {
		this.attachements = attachements;
	}
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
	
	
}
