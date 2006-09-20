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
import org.syracus.rapid.files.ComponentAttachement;
import org.syracus.rapid.history.ComponentHistory;
import org.syracus.rapid.history.ModuleHistory;
import org.syracus.rapid.history.ProjectHistory;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.realm.User;

@UrlBinding("/protected/component.action")
public class ComponentActionBean extends BaseComponentActionBean {

	protected static final transient Log log = LogFactory.getLog( ComponentActionBean.class );
	
	private Long moduleId;
	private Long projectId;
	
	private Long componentId;
	private Component component;
	
	private Module selectedModule;
	private List<Module> selectableModules;
	private Project selectedProject;
	private List<Project> selectableProjects;
	
	
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public Long getComponentId() {
		return componentId;
	}
	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}
	
	
	public List<Module> getSelectableModules() {
		return selectableModules;
	}
	public void setSelectableModules(List<Module> selectableModules) {
		this.selectableModules = selectableModules;
	}
	public List<Project> getSelectableProjects() {
		return selectableProjects;
	}
	public void setSelectableProjects(List<Project> selectableProjects) {
		this.selectableProjects = selectableProjects;
	}
	public Module getSelectedModule() {
		return selectedModule;
	}
	public void setSelectedModule(Module selectedModule) {
		this.selectedModule = selectedModule;
	}
	public Project getSelectedProject() {
		return selectedProject;
	}
	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}
	
	public Resolution create() {
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				setSelectedProject( project );
				
				Component component = new Component();
				component.setKey( project.getKey() );
				setComponent( component );
				
				Module module = project.getModule();
				if ( null != module ) {
					setSelectedModule( module );
				} else {
					Module dummyModule = new Module();
					dummyModule.setId( new Long( -1 ) );
					dummyModule.setName( "No module" );
					setSelectedModule( dummyModule );
				}
				return( new ForwardResolution( "/protected/components/componentCreate.jsp" ) );
			}
		}
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				setSelectedModule( module );
				
				Component component = new Component();
				component.setKey( module.getKey() );
				setComponent( component );
				
				List<Project> projects = getComponentService().getProjectsOfModule( module );
				Project dummy = new Project();
				dummy.setId( new Long( -1 ) );
				dummy.setName( "No project" );
				projects.add( 0, dummy );
				setSelectableProjects( projects );
				
				return( new ForwardResolution( "/protected/components/componentCreate.jsp" ) );
			}
		}
		List<Module> modules = getComponentService().getAllModules();
		Module dummyModule = new Module();
		dummyModule.setId( new Long( -1 ) );
		dummyModule.setName( "No module" );
		modules.add( 0, dummyModule );
		setSelectableModules( modules );
		
		List<Project> projects = getComponentService().getTopLevelProjects();
		Project dummyProject = new Project();
		dummyProject.setId( new Long( -1 ) );
		dummyProject.setName( "No project" );
		projects.add( 0, dummyProject );
		setSelectableProjects( projects );
		
		return( new ForwardResolution( "/protected/components/componentCreate.jsp" ) );
	}
	
	public Resolution view() {
		Component component = getComponentService().getComponentById( getComponentId() );
		setComponent( component );
		return( new ForwardResolution( "/protected/components/componentView.jsp" ) );
	}
	
	public Resolution edit() {
		Component component = getComponentService().getComponentById( getComponentId() );
		setComponent( component );
		return( new ForwardResolution( "/protected/components/componentEdit.jsp" ) );
	}
	
	public Resolution save() {
		if ( null == getComponent().getId() ) {
			if ( StringUtils.isBlank( getComponent().getKey() ) ) {
				getContext().getValidationErrors().add( "key", new SimpleError( "A component key is required." ) );
			}
			if ( StringUtils.isBlank( getComponent().getName() ) ) {
				getContext().getValidationErrors().add( "name", new SimpleError( "A component name is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				if ( null != getProjectId() ) {
					// we came from creating a project component
					Project project = getComponentService().getProjectById( getProjectId() );
					if ( null != project ) {
						setSelectedProject( project );
						Module module = project.getModule();
						if ( null == module ) {
							module = new Module();
							module.setId( new Long( -1 ) );
							module.setName( "No module" );
						}
						setSelectedModule( module );
					}
				} else if ( null != getModuleId() ) {
					// we came from creating a module component
					Module module = getComponentService().getModuleById( getModuleId() );
					if ( null != module ) {
						setSelectedModule( module );
						
						List<Project> projects = getComponentService().getProjectsOfModule( module );
						if ( null != projects && false == projects.isEmpty() ) {
							Project dummy = new Project();
							dummy.setId( new Long( -1 ) );
							dummy.setName( "No project" );
							projects.add( 0, dummy );
							setSelectableProjects( projects );
						}
					}
				} else {
					// we came from creating a top level component
					List<Module> modules = getComponentService().getAllModules();
					Module dummyModule = new Module();
					dummyModule.setId( new Long( -1 ) );
					dummyModule.setName( "No module" );
					modules.add( 0, dummyModule );
					setSelectableModules( modules );
					
					Module module = getComponent().getModule();
					if ( null != module
					 && -1 != module.getId() )
					{
						// a module was selected. so just load module specific projects
						List<Project> projects = getComponentService().getProjectsOfModule( module );
						if ( null != projects && false == projects.isEmpty() ) {
							Project dummy = new Project();
							dummy.setId( new Long( -1 ) );
							dummy.setName( "No project" );
							projects.add( 0, dummy );
							setSelectableProjects( projects );
						}
					} else {
						// no module was selected. load all top level projects.
						List<Project> projects = getComponentService().getTopLevelProjects();
						Project dummyProject = new Project();
						dummyProject.setId( new Long( -1 ) );
						dummyProject.setName( "No project" );
						projects.add( 0, dummyProject );
						setSelectableProjects( projects );
					}
				}
				return( getContext().getSourcePageResolution() );
			}
			if ( null != getComponent().getModule() && -1 == getComponent().getModule().getId() ) {
				getComponent().setModule( null );
			}
			if ( null != getComponent().getProject() && -1 == getComponent().getProject().getId() ) {
				getComponent().setProject( null );
			}
			
			ComponentHistory history = new ComponentHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			history.setText( "Component created." );
			history.setComponent( getComponent() );
			
			Set<ComponentHistory> initialHistory = new HashSet<ComponentHistory>();
			initialHistory.add( history );
			getComponent().setHistory( initialHistory );
			
			getComponentService().addComponent( getComponent(), getContext().getAuthUser() );
			
			// finally add history entry to module
			if ( null != getComponent().getProject() ) {
				ProjectHistory projectHistory = new ProjectHistory();
				projectHistory.setCreated( new Date() );
				projectHistory.setCreator( getContext().getAuthUser() );
				projectHistory.setText( "New component '" + getComponent().getName() + "' added." );
				projectHistory.setProject( getComponent().getProject() );
				getHistoryService().addHistory( projectHistory );
			} else if ( null != getComponent().getModule() ) {
				ModuleHistory moduleHistory = new ModuleHistory();
				moduleHistory.setCreated( new Date() );
				moduleHistory.setCreator( getContext().getAuthUser() );
				moduleHistory.setText( "New component '" + getComponent().getName() + "' added." );
				moduleHistory.setModule( getComponent().getModule() );
				getHistoryService().addHistory( moduleHistory );
			}
		} else {
			if ( StringUtils.isBlank( getComponent().getName() ) ) {
				getContext().getValidationErrors().add( "name", new SimpleError( "A component name is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				if ( null != getComponent().getModule() ) {
					getComponent().setModule( getComponentService().getModuleById( getComponent().getModule().getId() ) );
				}
				if ( null != getComponent().getProject() ) {
					getComponent().setProject( getComponentService().getProjectById( getComponent().getProject().getId() ) );
				}
				return( getContext().getSourcePageResolution() );
			}
			Component component = getComponentService().getComponentById( getComponent().getId() );
			StringBuffer message = new StringBuffer();
			
			String oldName = component.getName();
			String newName = StringUtils.trimToNull( getComponent().getName() );
			if ( false == StringUtils.equals( oldName, newName ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Name changed from '" + oldName + "' to '" + newName + "'." );
				component.setName( newName );
			}
			
			String oldDescription = component.getDescription();
			String newDescription = StringUtils.trimToNull( getComponent().getDescription() );
			if ( false == StringUtils.equals( oldDescription, newDescription ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Description changed." );
				component.setDescription( newDescription );
			}
			
			User oldLeader = component.getLeader();
			User newLeader = getComponent().getLeader();
			if ( false == oldLeader.getId().equals( newLeader.getId() ) ) {
				newLeader = getRealmService().getUserById( newLeader.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Leader changed from '" + oldLeader.getName() + "' to '" + newLeader.getName() + "'." );
				component.setLeader( newLeader );
			}
			
			//component.setModule( getComponent().getModule() );
			//component.setProject( getComponent().getProject() );
			
			ComponentHistory history = new ComponentHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			history.setText( (0 < message.length()) ? message.toString() : "No changes applied." );
			history.setComponent( component );
			component.getHistory().add( history );
			
			getComponentService().updateComponent( component, getContext().getAuthUser() );
		}
		return( new RedirectResolution( "/protected/component.action" )
			.addParameter( "view", "" )
			.addParameter( "componentId", getComponent().getId() )
		);
	}
	
	public Resolution delete() {
		Resolution resolution = null;
		Component component = getComponentService().getComponentById( getComponentId() );
		if ( null != component ) {
			Project project = component.getProject();
			Module module = component.getModule();
			if ( null != project ) {
				resolution = new RedirectResolution( "/protected/project.action" )
					.addParameter( "view", "" )
					.addParameter( "projectId", project.getId() );
			} else if ( null != module ) {
				resolution = new RedirectResolution( "/protected/module.action" )
					.addParameter( "view", "" )
					.addParameter( "moduleId", module.getId() );
			} else {
				resolution = new ForwardResolution( "/protected/components/componentList.jsp" );
			}
			
			getComponentService().deleteComponent( component, getContext().getAuthUser() );
			
			if ( null != project ) {
				ProjectHistory projectHistory = new ProjectHistory();
				projectHistory.setCreated( new Date() );
				projectHistory.setCreator( getContext().getAuthUser() );
				projectHistory.setText( "Component '" + component.getName() + "' deleted." );
				projectHistory.setProject( project );
				getHistoryService().addHistory( projectHistory );
			} else if ( null != module ) {
				ModuleHistory moduleHistory = new ModuleHistory();
				moduleHistory.setCreated( new Date() );
				moduleHistory.setCreator( getContext().getAuthUser() );
				moduleHistory.setText( "Component '" + component.getName() + "' deleted." );
				moduleHistory.setModule( module );
				getHistoryService().addHistory( moduleHistory );
			}
		}
		return( resolution );
	}
	
	public Resolution key() {
		String key = "";
		if ( null != getComponentId() && -1 != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				key = component.getKey();
			}
		}
		return( new StreamingResolution( "text", key ) );
	}
	
	public List<Component> getAllComponents() {
		return( getComponentService().getAllComponents() );
	}
	
	public List<Component> getOwnComponents() {
		int maxComponents = Integer.parseInt( getContext().getUserProfile().getProperty( UserProfile.KEY_MAX_COMPONENTS, UserProfile.DEF_MAX_COMPONENTS ) );
		return( getComponentService().getNewestComponentsByLeader( getContext().getAuthUser(), maxComponents ) );
	}
	
	/*
	public List<Component> getModuleComponents() {
		List<Component> components = null;
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				components = getComponentService().getComponentsOfModule( module );
			}
		}
		return( components );
	}
	*/
	
	/*
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
	*/
	
	public List<Issue> getComponentIssues() {
		List<Issue> issues = null;
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				issues = getIssueService().getIssuesOfComponent( component );
			}
		}
		return( issues );
	}
	
	public List<Component> getSelectableComponents() {
		List<Component> components = null;
		if ( null != getProjectId() ) {
			if ( -1 != getProjectId() ) {
				Project project = getComponentService().getProjectById( getProjectId() );
				if ( null != project ) {
					components = getComponentService().getComponentsOfProject( project );
				}
			}
		} else if ( null != getModuleId() ) {
			if ( -1 != getModuleId() ) {
				Module module = getComponentService().getModuleById( getModuleId() );
				if ( null != module ) {
					components = getComponentService().getComponentsOfModule( module );
				}
			} else {
				components = getComponentService().getAllComponentsOfModule( null );
			}
		} else {
			components = getComponentService().getAllComponents();
		}
		Component dummyComponent = new Component();
		dummyComponent.setId( new Long( -1 ) );
		dummyComponent.setName( "No component" );
		components.add( 0, dummyComponent );
		return( components );
	}
	
	public List<Project> getAvailableProjects() {
		List<Project> projects = null;
		if ( null != getModuleId() && -1 != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				projects = getComponentService().getProjectsOfModule( module );
			}
		} else {
			projects = getComponentService().getTopLevelProjects();
		}
		Project dummy = new Project();
		dummy.setId( new Long( -1 ) );
		dummy.setName( "No project" );
		projects.add( 0, dummy );
		return( projects );
	}
	
	public Set<ComponentAttachement> getComponentAttachements() {
		Set<ComponentAttachement> attachements = null;
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				attachements = component.getAttachements();
			}
		}
		return( attachements );
	}
	
	public List<ComponentHistory> getComponentHistory() {
		List<ComponentHistory> history = null;
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				history = new ArrayList<ComponentHistory>( component.getHistory() );
			}
		}
		if ( null != history ) {
			Collections.sort( history, new Comparator<ComponentHistory>() {
				public int compare(ComponentHistory arg0, ComponentHistory arg1) {
					return( arg0.getCreated().compareTo( arg1.getCreated() ) );
				}
			} );
		}
		return( history );
	}
}
