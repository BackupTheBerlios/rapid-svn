package org.syracus.rapid.history;

import org.syracus.rapid.components.Project;

/**
 * 
 * @author snwiem
 * @hibernate.class table="project_history"
 */
public class ProjectHistory extends AbstractHistory {

	private Project project;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true" 
	 */
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
