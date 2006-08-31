package org.syracus.rapid.comments;

import org.syracus.rapid.components.Project;

/**
 * 
 * @author snwiem
 * @hibernate.class table="project_comments"
 */
public class ProjectComment extends AbstractComment {

	private Project project;
	private ProjectComment parent;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public ProjectComment getParent() {
		return parent;
	}

	public void setParent(ProjectComment parent) {
		this.parent = parent;
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
	
	
}
