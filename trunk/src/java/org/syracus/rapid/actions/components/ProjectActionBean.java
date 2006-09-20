package org.syracus.rapid.actions.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.files.ProjectAttachement;
import org.syracus.rapid.history.ModuleHistory;
import org.syracus.rapid.history.ProjectHistory;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.realm.User;

@UrlBinding("/protected/project.action")
public class ProjectActionBean extends BaseComponentActionBean {

	protected static final transient Log log = LogFactory.getLog( ProjectActionBean.class );
	
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
			if ( StringUtils.isBlank( getProject().getKey() ) ) {
				getContext().getValidationErrors().add( "key", new SimpleError( "A project key is required." ) );
			}
			if ( StringUtils.isBlank( getProject().getName() ) ) {
				getContext().getValidationErrors().add( "name", new SimpleError( "A project name is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				if ( log.isDebugEnabled() ) {
					log.debug( "[save] validation errors occured. preparing source page resolution." );
				}
				if ( null == getModuleId() ) {
					if ( log.isDebugEnabled() ) {
						log.debug( "[save] no module id found. loading all modules." );
					}
					List<Module> modules = getComponentService().getAllModules();
					// insert dummy module
					Module dummy = new Module();
					dummy.setId( new Long(-1) );
					dummy.setName( "No module" );
					modules.add( 0, dummy );
					setSelectableModules( modules );
				} else {
					if ( log.isDebugEnabled() ) {
						log.debug( "[save] moduleId is '" + getModuleId() + "'" );
					}
					Module module = getComponentService().getModuleById( getModuleId() );
					if ( null != module ) {
						if ( log.isDebugEnabled() ) {
							log.debug( "[save] setting selected module." );
						}
						setSelectedModule( module );
					}
				}
				return( getContext().getSourcePageResolution() );
			}
			// check if 'no module' has been selected
			if ( null != getProject().getModule() && -1 == getProject().getModule().getId() ) {
				getProject().setModule( null );
			}
			
			ProjectHistory history = new ProjectHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			history.setText( "Project created." );
			history.setProject( getProject() );
			
			Set<ProjectHistory> initialHistory = new HashSet<ProjectHistory>();
			initialHistory.add( history );
			getProject().setHistory( initialHistory );
			
			getComponentService().addProject( getProject(), getContext().getAuthUser() );
			
			// finally add history entry to module
			if ( null != getProject().getModule() ) {
				ModuleHistory moduleHistory = new ModuleHistory();
				moduleHistory.setCreated( new Date() );
				moduleHistory.setCreator( getContext().getAuthUser() );
				moduleHistory.setText( "New project '" + getProject().getName() + "' added." );
				moduleHistory.setModule( getProject().getModule() );
				getHistoryService().addHistory( moduleHistory );
			}
		} else {
			if ( StringUtils.isBlank( getProject().getName() ) ) {
				getContext().getValidationErrors().add( "name", new SimpleError( "A project name is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				if ( null != getProject().getModule() ) {
					getProject().setModule( getComponentService().getModuleById( getProject().getModule().getId() ) );
				}
				return( getContext().getSourcePageResolution() );
			}
			
			Project project = getComponentService().getProjectById( getProject().getId() );
			StringBuffer message = new StringBuffer();
			
			//project.setModule( getProject().getModule() );
			
			String oldName = project.getName();
			String newName = StringUtils.trimToNull( getProject().getName() );
			if ( false == StringUtils.equals( oldName, newName ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Name changed from '" + oldName + "' to '" + newName + "'." );
				project.setName( newName );
			}
			
			String oldDescription = project.getDescription();
			String newDescription = StringUtils.trimToNull( getProject().getDescription() );
			if ( false == StringUtils.equals( oldDescription, newDescription ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Description changed." );
				project.setDescription( newDescription );
			}
			
			String oldHome = project.getHome();
			String newHome = StringUtils.trimToNull( getProject().getHome() );
			if ( false == StringUtils.equals( oldHome, newHome ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Project home changed from '" + oldHome + "' to '" + newHome + "'." );
				project.setHome( newHome );
			}
			
			User oldLeader = project.getLeader();
			User newLeader = getProject().getLeader();
			if ( false == oldLeader.getId().equals( newLeader.getId() ) ) {
				newLeader = getRealmService().getUserById( newLeader.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Leader changed from '" + oldLeader.getName() + "' to '" + newLeader.getName() + "'." );
				project.setLeader( newLeader );
			}
			
			ProjectHistory history = new ProjectHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			history.setText( (0 < message.length()) ? message.toString() : "No changed applied." );
			history.setProject( project );
			project.getHistory().add( history );
			
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
					resolution = new ForwardResolution( "/protected/components/projectList.jsp" );
				}
				// finally delete project
				getComponentService().deleteProject( project, getContext().getAuthUser() );
				
				if ( null != module ) {
					ModuleHistory moduleHistory = new ModuleHistory();
					moduleHistory.setCreated( new Date() );
					moduleHistory.setCreator( getContext().getAuthUser() );
					moduleHistory.setText( "Project '" + project.getName() + "' deleted." );
					moduleHistory.setModule( project.getModule() );
					getHistoryService().addHistory( moduleHistory );
				}
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
	
	public Set<ProjectAttachement> getProjectAttachements() {
		Set<ProjectAttachement> attachements = null;
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				attachements = project.getAttachements();
			}
		}
		return( attachements );
	}
	
	public List<ProjectHistory> getProjectHistory() {
		List<ProjectHistory> history = null;
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				history = new ArrayList<ProjectHistory>( project.getHistory() );
			}
		}
		if ( null != history ) {
			Collections.sort( history, new Comparator<ProjectHistory>() {
				public int compare(ProjectHistory arg0, ProjectHistory arg1) {
					return( arg0.getCreated().compareTo( arg1.getCreated() ) );
				}
			} );
		}
		return( history );
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
