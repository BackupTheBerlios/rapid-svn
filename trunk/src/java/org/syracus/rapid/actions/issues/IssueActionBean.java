package org.syracus.rapid.actions.issues;

import java.util.List;
import java.util.Set;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.IComponentService;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.files.IssueAttachement;
import org.syracus.rapid.issues.IIssueService;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.issues.Status;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.stripes.RapidActionBean;

@UrlBinding("/protected/issue.action")
public class IssueActionBean extends RapidActionBean {
	
	protected static final transient Log log = LogFactory.getLog( IssueActionBean.class );
	
	private Long issueId;
	private Issue issue;
	
	
	
	private Long moduleId;
	private Long projectId;
	private Long componentId;
	
	private Module selectedModule;
	private Project selectedProject;
	private Component selectedComponent;
	
	private List<Module> selectableModules;
	private List<Project> selectableProjects;
	private List<Component> selectableComponents;
	
	private IIssueService issueService;
	private IComponentService componentService;
	
	
	

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public List<Component> getSelectableComponents() {
		return selectableComponents;
	}

	public void setSelectableComponents(List<Component> selectableComponents) {
		this.selectableComponents = selectableComponents;
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

	public Component getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(Component selectedComponent) {
		this.selectedComponent = selectedComponent;
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

	public IComponentService getComponentService() {
		return componentService;
	}

	@SpringBean("componentService")
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

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

	public IIssueService getIssueService() {
		return issueService;
	}

	@SpringBean("issueService")
	public void setIssueService(IIssueService issueService) {
		this.issueService = issueService;
	}

	public Resolution create() {
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				setSelectedComponent( component );
				
				Issue issue = new Issue();
				issue.setKey( component.getKey() );
				setIssue( issue );
				
				Module module = component.getModule();
				if ( null == module ) {
					module = new Module();
					module.setId( new Long( -1 ) );
					module.setName( "No module" );
				}
				setSelectedModule( module );
				
				Project project = component.getProject();
				if ( null == project ) {
					project = new Project();
					project.setId( new Long( -1 ) );
					project.setName( "No project" );
				}
				setSelectedProject( project );
				
				return( new ForwardResolution( "/protected/issues/issueCreate.jsp" ) );
			}
		} else if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				setSelectedProject( project );
				
				Issue issue = new Issue();
				issue.setKey( project.getKey() );
				setIssue( issue );
				
				Module module = project.getModule();
				if ( null == module ) {
					module = new Module();
					module.setId( new Long( -1 ) );
					module.setName( "No module" );
				}
				setSelectedModule( module );
				
				
				List<Component> components = getComponentService().getComponentsOfProject( project );
				Component dummyComponent = new Component();
				dummyComponent.setId( new Long( -1 ) );
				dummyComponent.setName( "No component" );
				components.add( 0, dummyComponent );
				
				setSelectableComponents( components );
				return( new ForwardResolution( "/protected/issues/issueCreate.jsp" ) );
			}
		} else if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				setSelectedModule( module );
				
				Issue issue = new Issue();
				issue.setKey( module.getKey() );
				setIssue( issue );
				
				List<Project> projects = getComponentService().getProjectsOfModule( module );
				Project dummyProject = new Project();
				dummyProject.setId( new Long( -1 ) );
				dummyProject.setName( "No project" );
				projects.add( 0, dummyProject );
				setSelectableProjects( projects );
				
				List<Component> components = getComponentService().getComponentsOfModule( module );
				Component dummyComponent = new Component();
				dummyComponent.setId( new Long( -1 ) );
				dummyComponent.setName( "No component" );
				components.add( 0, dummyComponent );
				setSelectableComponents( components );
				
				return( new ForwardResolution( "/protected/issues/issueCreate.jsp" ) );
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
		
		List<Component> components = getComponentService().getTopLevelComponents();
		Component dummyComponent = new Component();
		dummyComponent.setId( new Long( -1 ) );
		dummyComponent.setName( "No component" );
		components.add( 0, dummyComponent );
		setSelectableComponents( components );
		
		return( new ForwardResolution( "/protected/issues/issueCreate.jsp" ) );
	}
	
	public Resolution save() {
		if ( null == getIssue().getId() ) {
			if ( (null == getIssue().getModule() || -1 == getIssue().getModule().getId())
			  && (null == getIssue().getProject() || -1 == getIssue().getProject().getId())
			  && (null == getIssue().getComponent() || -1 == getIssue().getComponent().getId()) )
			{
				getContext().getValidationErrors().add( "selection", new SimpleError( "Either module, project or component is required." ) );
			}
			
			if ( StringUtils.isBlank( getIssue().getKey() ) ) {
				getContext().getValidationErrors().add( "key", new SimpleError( "An issue key is required." ) );
			}
			if ( StringUtils.isBlank( getIssue().getSummary() ) ) {
				getContext().getValidationErrors().add( "summary", new SimpleError( "An issue summary is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				if ( null != getComponentId() ) {
					Component component = getComponentService().getComponentById( getComponentId() );
					if ( null != component ) {
						setSelectedComponent( component );
						
						Module module = component.getModule();
						if ( null == module ) {
							module = new Module();
							module.setId( new Long( -1 ) );
							module.setName( "No module" );
						}
						setSelectedModule( module );
						
						Project project = component.getProject();
						if ( null == project ) {
							project = new Project();
							project.setId( new Long( -1 ) );
							project.setName( "No project" );
						}
						setSelectedProject( project );
					}
				} else if ( null != getProjectId() ) {
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
						
						
						List<Component> components = getComponentService().getComponentsOfProject( project );
						Component dummyComponent = new Component();
						dummyComponent.setId( new Long( -1 ) );
						dummyComponent.setName( "No component" );
						components.add( 0, dummyComponent );
						
						setSelectableComponents( components );
					}
				} else if ( null != getModuleId() ) {
					Module module = getComponentService().getModuleById( getModuleId() );
					if ( null != module ) {
						setSelectedModule( module );
						
						List<Project> projects = getComponentService().getProjectsOfModule( module );
						Project dummyProject = new Project();
						dummyProject.setId( new Long( -1 ) );
						dummyProject.setName( "No project" );
						projects.add( 0, dummyProject );
						setSelectableProjects( projects );
						
						List<Component> components = getComponentService().getComponentsOfModule( module );
						Component dummyComponent = new Component();
						dummyComponent.setId( new Long( -1 ) );
						dummyComponent.setName( "No component" );
						components.add( 0, dummyComponent );
						setSelectableComponents( components );
					}
				} else {
					List<Module> modules = getComponentService().getAllModules();
					Module dummyModule = new Module();
					dummyModule.setId( new Long( -1 ) );
					dummyModule.setName( "No module" );
					modules.add( 0, dummyModule );
					setSelectableModules( modules );
					
					Module module = getIssue().getModule();
					if ( null != module && -1 != module.getId() ) {
						List<Project> projects = getComponentService().getProjectsOfModule( module );
						Project dummyProject = new Project();
						dummyProject.setId( new Long( -1 ) );
						dummyProject.setName( "No project" );
						projects.add( 0, dummyProject );
						setSelectableProjects( projects );
						
						Project project = getIssue().getProject();
						if ( null != project && -1 != project.getId() ) {
							List<Component> components = getComponentService().getComponentsOfProject( project );
							Component dummyComponent = new Component();
							dummyComponent.setId( new Long( -1 ) );
							dummyComponent.setName( "No component" );
							components.add( 0, dummyComponent );
							setSelectableComponents( components );
						} else {
							List<Component> components = getComponentService().getComponentsOfModule( module );
							Component dummyComponent = new Component();
							dummyComponent.setId( new Long( -1 ) );
							dummyComponent.setName( "No component" );
							components.add( 0, dummyComponent );
							setSelectableComponents( components );
						}
					} else {
						List<Project> projects = getComponentService().getTopLevelProjects();
						Project dummyProject = new Project();
						dummyProject.setId( new Long( -1 ) );
						dummyProject.setName( "No project" );
						projects.add( 0, dummyProject );
						setSelectableProjects( projects );
						
						Project project = getIssue().getProject();
						if ( null != project && -1 != project.getId() ) {
							List<Component> components = getComponentService().getComponentsOfProject( project );
							Component dummyComponent = new Component();
							dummyComponent.setId( new Long( -1 ) );
							dummyComponent.setName( "No component" );
							components.add( 0, dummyComponent );
							setSelectableComponents( components );
						} else {
							List<Component> components = getComponentService().getTopLevelComponents();
							Component dummyComponent = new Component();
							dummyComponent.setId( new Long( -1 ) );
							dummyComponent.setName( "No component" );
							components.add( 0, dummyComponent );
							setSelectableComponents( components );
						}
					}
				}
				
				return( getContext().getSourcePageResolution() );
			}
			
			if ( null != getIssue().getModule() && -1 == getIssue().getModule().getId() ) {
				getIssue().setModule( null );
			}
			if ( null != getIssue().getProject() && -1 == getIssue().getProject().getId() ) {
				getIssue().setProject( null );
			}
			if ( null != getIssue().getComponent() && -1 == getIssue().getComponent().getId() ) {
				getIssue().setComponent( null );
			}
			if ( StringUtils.isBlank( getIssue().getKey() ) ) {
				getIssue().setKey( "" );
			}
			getIssue().setStatus( Status.OPEN.toString() );
			
			
			getIssueService().addIssue( getIssue(), getContext().getAuthUser() );
			
			// update issue with new generated key
			getIssue().setKey( getIssue().getKey() + getIssue().getId() );
			getIssueService().updateIssue( issue, getContext().getAuthUser() );
		} else {
			if ( StringUtils.isBlank( getIssue().getSummary() ) ) {
				getContext().getValidationErrors().add( "summary", new SimpleError( "An issue summary is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				if ( null != getIssue().getModule() ) {
					getIssue().setModule( getComponentService().getModuleById( getIssue().getModule().getId() ) );
				}
				if ( null != getIssue().getProject() ) {
					getIssue().setProject( getComponentService().getProjectById( getIssue().getProject().getId() ) );
				}
				if ( null != getIssue().getComponent() ) {
					getIssue().setComponent( getComponentService().getComponentById( getIssue().getComponent().getId() ) );
				}
				return( getContext().getSourcePageResolution() );
			}
			Issue issue = getIssueService().getIssueById( getIssue().getId() );
			issue.setSummary( getIssue().getSummary() );
			issue.setDescription( getIssue().getDescription() );
			issue.setType( getIssue().getType() );
			issue.setPriority( getIssue().getPriority() );
			issue.setStatus( getIssue().getStatus() );
			issue.setReporter( getIssue().getReporter() );
			issue.setAssignee( getIssue().getAssignee() );
			getIssueService().updateIssue( issue, getContext().getAuthUser() );
		}
		return( new RedirectResolution( "/protected/issue.action" )
			.addParameter( "view", "" )
			.addParameter( "issueId", getIssue().getId() )
		);
	}
	
	public Resolution view() {
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			if ( null != issue ) {
				setIssue( issue );
			}
		}
		return( new ForwardResolution( "/protected/issues/issueView.jsp" ) );
	}
	
	public Resolution edit() {
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			if ( null != issue ) {
				setIssue( issue );
			}
		}
		return( new ForwardResolution( "/protected/issues/issueEdit.jsp" ) );
	}
	
	public List<Issue> getAllIssues() {
		return( getIssueService().getAllIssues() );
	}

	public List<Issue> getOwnIssues() {
		int maxIssues = Integer.parseInt( getContext().getUserProfile().getProperty( UserProfile.KEY_MAX_ISSUES, UserProfile.DEF_MAX_ISSUES ) );
		return( getIssueService().getNewestIssuesByAssignee( getContext().getAuthUser(), maxIssues ) );
	}
	
	public List<Module> getAvailableModules() {
		return( getComponentService().getAllModules() );
	}
	
	public List<Project> getAvailableProjects() {
		List<Project> projects = null;
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				projects = getComponentService().getProjectsOfModule( module );
				Project dummyProject = new Project();
				dummyProject.setId( new Long( -1 ) );
				dummyProject.setName( "No project" );
				projects.add( 0, dummyProject );
				return( projects );
			}
		}
		projects = getComponentService().getTopLevelProjects();
		Project dummyProject = new Project();
		dummyProject.setId( new Long( -1 ) );
		dummyProject.setName( "No project" );
		projects.add( 0, dummyProject );
		return( projects );
	}
	
	public List<Component> getAvailableComponents() {
		List<Component> components = null;
		if ( null != getProjectId() && -1 != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				components = getComponentService().getComponentsOfProject( project );
				Component dummyComponent = new Component();
				dummyComponent.setId( new Long( -1 ) );
				dummyComponent.setName( "No component" );
				components.add( 0, dummyComponent );
				return( components );
			}
		} else if ( null != getModuleId() && -1 != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				components = getComponentService().getComponentsOfModule( module );
				Component dummyComponent = new Component();
				dummyComponent.setId( new Long( -1 ) );
				dummyComponent.setName( "No component" );
				components.add( 0, dummyComponent );
				return( components );
			}
		}
		components = getComponentService().getTopLevelComponents();
		Component dummyComponent = new Component();
		dummyComponent.setId( new Long( -1 ) );
		dummyComponent.setName( "No component" );
		components.add( 0, dummyComponent );
		return( components );
	}
	
	public Set<IssueAttachement> getIssueAttachements() {
		Set<IssueAttachement> attachements = null;
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			if ( null != issue ) {
				attachements = issue.getAttachements();
			}
		}
		return( attachements );
	}
	
	
	
}
