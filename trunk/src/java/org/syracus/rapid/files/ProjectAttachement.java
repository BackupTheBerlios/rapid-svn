package org.syracus.rapid.files;

import org.syracus.rapid.components.Project;

/**
 * 
 * @author snwiem
 * @hibernate.class table="project_attachements"
 */
public class ProjectAttachement extends AbstractAttachement {

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
