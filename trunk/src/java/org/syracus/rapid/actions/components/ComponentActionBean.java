package org.syracus.rapid.actions.components;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.profiles.UserProfile;

@UrlBinding("/protected/component.action")
public class ComponentActionBean extends BaseComponentActionBean {

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
				List<Project> projects = getComponentService().getProjectsOfModule( module );
				if ( null != projects && false == projects.isEmpty() ) {
					Project dummy = new Project();
					dummy.setId( new Long( -1 ) );
					dummy.setName( "No project" );
					projects.add( 0, dummy );
					setSelectableProjects( projects );
				}
				return( new ForwardResolution( "/protected/components/componentCreate.jsp" ) );
			}
		}
		List<Module> modules = getComponentService().getAllModules();
		if ( null != modules && !modules.isEmpty() ) {
			Module dummyModule = new Module();
			dummyModule.setId( new Long( -1 ) );
			dummyModule.setName( "No module" );
			modules.add( 0, dummyModule );
			
			List<Project> projects = getComponentService().getTopLevelProjects();
			Project dummyProject = new Project();
			dummyProject.setId( new Long( -1 ) );
			dummyProject.setName( "No project" );
			projects.add( 0, dummyProject );
			
			setSelectableProjects( projects );
			setSelectableModules( modules );
		}
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
			if ( null != getComponent().getModule() && -1 == getComponent().getModule().getId() ) {
				getComponent().setModule( null );
			}
			if ( null != getComponent().getProject() && -1 == getComponent().getProject().getId() ) {
				getComponent().setProject( null );
			}
			getComponentService().addComponent( getComponent(), getContext().getAuthUser() );
		} else {
			Component component = getComponentService().getComponentById( getComponent().getId() );
			component.setName( getComponent().getName() );
			component.setDescription( getComponent().getDescription() );
			component.setLeader( getComponent().getLeader() );
			component.setModule( getComponent().getModule() );
			component.setProject( getComponent().getProject() );
			getComponentService().updateComponent( component, getContext().getAuthUser() );
		}
		return( new RedirectResolution( "/protected/component.action" )
			.addParameter( "view", "" )
			.addParameter( "componentId", getComponent().getId() )
		);
	}
	
	public Resolution delete() {
		Component component = getComponentService().getComponentById( getComponentId() );
		getComponentService().deleteComponent( component, getContext().getAuthUser() );
		return( new ForwardResolution( "" ) );
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
}
