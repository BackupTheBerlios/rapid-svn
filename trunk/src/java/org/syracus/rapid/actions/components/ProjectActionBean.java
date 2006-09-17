package org.syracus.rapid.actions.components;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.profiles.UserProfile;

@UrlBinding("/protected/project.action")
public class ProjectActionBean extends BaseComponentActionBean {

	private Long moduleId;
	private Long projectId;
	private Project project;
	
	private Module selectedModule;
	private List<Module> selectableModules;
	
	public Module getSelectedModule() {
		return selectedModule;
	}
	public void setSelectedModule(Module selectedModule) {
		this.selectedModule = selectedModule;
	}
	public List<Module> getSelectableModules() {
		return selectableModules;
	}
	public void setSelectableModules(List<Module> selectableModules) {
		this.selectableModules = selectableModules;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
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

	public Resolution create() {
		if ( null == getModuleId() ) {
			List<Module> modules = getComponentService().getAllModules();
			// insert dummy module
			Module dummy = new Module();
			dummy.setId( new Long(-1) );
			dummy.setName( "No module" );
			modules.add( 0, dummy );
			setSelectableModules( modules );
		} else {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				setSelectedModule( module );
				
				project = new Project();
				project.setKey( module.getKey() );
				setProject( project );
			}
		}
		return( new ForwardResolution( "/protected/components/projectCreate.jsp" ) );
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
			// check if 'no module' has been selected
			if ( null != getProject().getModule() && -1 == getProject().getModule().getId() ) {
				getProject().setModule( null );
			}
			getComponentService().addProject( getProject(), getContext().getAuthUser() );
		} else {
			Project project = getComponentService().getProjectById( getProject().getId() );
			project.setModule( getProject().getModule() );
			project.setName( getProject().getName() );
			project.setDescription( getProject().getDescription() );
			project.setHome( getProject().getHome() );
			project.setLeader( getProject().getLeader() );
			getComponentService().updateProject( project, getContext().getAuthUser() );
		}
		return( new RedirectResolution( "/protected/project.action" )
			.addParameter( "view", "" )
			.addParameter( "projectId", getProject().getId() )
		);
	}
	
	public Resolution delete() {
		Resolution resolution = null;
		Project project = getComponentService().getProjectById( getProjectId() );
		if ( null != project ) {
			Integer componentCount = getComponentService().getNumberOfComponents( project );
			if ( 0 == componentCount ) {
				Module module = project.getModule();
				if ( null != module ) {
					resolution = new RedirectResolution( "/protected/module.action" )
						.addParameter( "view", "" )
						.addParameter( "moduleId", module.getId() );
				} else {
					resolution = new RedirectResolution( "/protected/components/projectList.jsp" );
				}
				// finally delete project
				getComponentService().deleteProject( project, getContext().getAuthUser() );
			}
		}
		return( resolution );
	}
	
	public List<Project> getAllProjects() {
		return( getComponentService().getAllProjects() );
	}
	
	public List<Project> getOwnProjects() {
		int maxProjects = Integer.parseInt( getContext().getUserProfile().getProperty( UserProfile.KEY_MAX_PROJECTS, UserProfile.DEF_MAX_PROJECTS ) );
		return( getComponentService().getNewestProjectsByLeader( getContext().getAuthUser(), maxProjects ) );
	}
	
	public List<Component> getProjectComponents() {
		List<Component> components = null;
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				components = getComponentService().getComponentsOfProject( project );
			}
		}
		return( components );
	}
	
	public List<Issue> getProjectIssues() {
		List<Issue> issues = null;
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				issues = getIssueService().getAllIssuesOfProject( project );
			}
		}
		return( issues );
	}
	
	public Resolution key() {
		String key = "";
		if ( null != getProjectId() && -1 != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				key = project.getKey();
			}
		}
		return( new StreamingResolution( "text", key ) );
	}
	
	/*
	public List<Project> getSelectableProjects() {
		List<Project> projects = null;
		if ( null != getModuleId() && -1 != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				projects = getComponentService().getProjectsOfModule( module );
			}
		}
		Project dummy = new Project();
		dummy.setId( new Long( -1 ) );
		dummy.setName( "No project" );
		projects.add( 0, dummy );
		return( projects );
	}
	*/
	
	/*
	public List<Project> getModuleProjects() {
		List<Project> projects = null;
		if ( null != getModuleId() && -1 != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				projects = getComponentService().getProjectsOfModule( module );
			}
		}
		return( projects );
	}
	*/
	
	
	
}
