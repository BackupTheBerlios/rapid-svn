package org.syracus.rapid.actions.components;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.components.Project;
import org.syracus.rapid.profiles.UserProfile;

@UrlBinding("/protected/project.action")
public class ProjectActionBean extends BaseComponentActionBean {

	private Long projectId;
	private Project project;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public Resolution view() {
		Project project = getComponentService().getProjectById( getProjectId() );
		setProject( project );
		return( new ForwardResolution( "/protected/components/projectView.jsp" ) );
	}
	
	public Resolution edit() {
		Project project = getComponentService().getProjectById( getProjectId() );
		setProject( project );
		return( new ForwardResolution( "/protected/components/projectEdit.jsp" ) );
	}
	
	public Resolution save() {
		if ( null == getProject().getId() ) {
			getComponentService().addProject( getProject(), getContext().getAuthUser() );
		} else {
			Project project = getComponentService().getProjectById( getProject().getId() );
			project.setModule( getProject().getModule() );
			project.setName( getProject().getName() );
			project.setDescription( getProject().getDescription() );
			project.setHome( getProject().getHome() );
			project.setLeader( getProject().getLeader() );
			project.setParent( getProject().getParent() );
			getComponentService().updateProject( project, getContext().getAuthUser() );
		}
		return( new RedirectResolution( "/protected/project.action" )
			.addParameter( "view", "" )
			.addParameter( "projectId", getProject().getId() )
		);
	}
	
	public Resolution delete() {
		Project project = getComponentService().getProjectById( getProjectId() );
		getComponentService().deleteProject( project, getContext().getAuthUser() );
		return( new ForwardResolution( "" ) );
	}
	
	public List<Project> getOwnProjects() {
		int maxProjects = Integer.parseInt( getContext().getUserProfile().getProperty( UserProfile.KEY_MAX_PROJECTS, UserProfile.DEF_MAX_PROJECTS ) );
		return( getComponentService().getNewestProjectsByLeader( getContext().getAuthUser(), maxProjects ) );
	}
	
	
}
