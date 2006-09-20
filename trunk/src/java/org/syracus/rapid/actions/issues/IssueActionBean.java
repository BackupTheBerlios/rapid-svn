package org.syracus.rapid.actions.issues;

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
import org.syracus.rapid.history.ComponentHistory;
import org.syracus.rapid.history.IHistoryService;
import org.syracus.rapid.history.IssueHistory;
import org.syracus.rapid.history.ModuleHistory;
import org.syracus.rapid.history.ProjectHistory;
import org.syracus.rapid.issues.IIssueService;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.issues.IssuePriority;
import org.syracus.rapid.issues.IssueStatus;
import org.syracus.rapid.issues.IssueType;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.realm.IRealmService;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.stripes.RapidActionBean;

@UrlBinding("/protected/issue.action")
public class IssueActionBean extends RapidActionBean {
	
	protected static final transient Log log = LogFactory.getLog( IssueActionBean.class );
	
	private Long issueId;
	private Issue issue;
	
	private Long typeId;
	private Long priorityId;
	private Long statusId;
	
	
	private Long moduleId;
	private Long projectId;
	private Long componentId;
	
	private Module selectedModule;
	private Project selectedProject;
	private Component selectedComponent;
	private IssueStatus selectedStatus;
	
	private List<Module> selectableModules;
	private List<Project> selectableProjects;
	private List<Component> selectableComponents;
	
	
	private IIssueService issueService;
	private IComponentService componentService;
	private IRealmService realmService;
	private IHistoryService historyService;
	
	private String historyComment;
	
	
	public IHistoryService getHistoryService() {
		return historyService;
	}

	@SpringBean("historyService")
	public void setHistoryService(IHistoryService historyService) {
		this.historyService = historyService;
	}

	public IRealmService getRealmService() {
		return realmService;
	}

	@SpringBean("realmService")
	public void setRealmService(IRealmService realmService) {
		this.realmService = realmService;
	}

	public String getHistoryComment() {
		return historyComment;
	}

	public void setHistoryComment(String historyComment) {
		this.historyComment = historyComment;
	}

	public IssueStatus getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(IssueStatus selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

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
	
	public Resolution delete() {
		Resolution resolution = null;
		Issue issue = getIssueService().getIssueById( getIssueId() );
		if ( null != issue ) {
			Component component = issue.getComponent();
			Project project = issue.getProject();
			Module module = issue.getModule();
			
			if ( null != component ) {
				resolution = new RedirectResolution( "/protected/component.action" )
					.addParameter( "view", "" )
					.addParameter( "componentId", component.getId() );
			} else if ( null != project ) {
				resolution = new RedirectResolution( "/protected/project.action" )
					.addParameter( "view", "" )
					.addParameter( "projectId", project.getId() );
			} else if ( null != module ) {
				resolution = new RedirectResolution( "/protected/module.action" )
					.addParameter( "view", "" )
					.addParameter( "moduleId", module.getId() );
			} else {
				resolution = new ForwardResolution( "/protected/issues/issueList.jsp" );
			}
			
			getIssueService().deleteIssue( issue, getContext().getAuthUser() );
			
			if ( null != component ) {
				ComponentHistory componentHistory = new ComponentHistory();
				componentHistory.setCreated( new Date() );
				componentHistory.setCreator( getContext().getAuthUser() );
				componentHistory.setText( "Issue '" + issue.getKey() + "' deleted." );
				componentHistory.setComponent( component );
				getHistoryService().addHistory( componentHistory );
			} else if ( null != project ) {
				ProjectHistory projectHistory = new ProjectHistory();
				projectHistory.setCreated( new Date() );
				projectHistory.setCreator( getContext().getAuthUser() );
				projectHistory.setText( "Issue '" + issue.getKey() + "' deleted." );
				projectHistory.setProject( project );
				getHistoryService().addHistory( projectHistory );
			} else if ( null != module ) {
				ModuleHistory moduleHistory = new ModuleHistory();
				moduleHistory.setCreated( new Date() );
				moduleHistory.setCreator( getContext().getAuthUser() );
				moduleHistory.setText( "Issue '" + issue.getKey() + "' deleted." );
				moduleHistory.setModule( module );
				getHistoryService().addHistory( moduleHistory );
			}
		}
		return( resolution );
	}

	public Resolution create() {
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				setSelectedComponent( component );
				
				Issue issue = new Issue();
				issue.setKey( component.getKey() );
				issue.setStatus( getIssueService().getDefaultIssueStatus() );
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
				issue.setStatus( getIssueService().getDefaultIssueStatus() );
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
				issue.setStatus( getIssueService().getDefaultIssueStatus() );
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
		
		Issue issue = new Issue();
		issue.setStatus( getIssueService().getDefaultIssueStatus() );
		setIssue( issue );
		
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
				// restore issue status
				if ( null != getIssue().getStatus() ) {
					getIssue().setStatus( getIssueService().getIssueStatusById( getIssue().getStatus().getId() ) );
				}
				if ( null != getIssue().getType() ) {
					if ( log.isDebugEnabled() ) {
						log.debug( "[create] issue type found '" + getIssue().getType().getId() + "'" );
					}
					getIssue().setType( getIssueService().getIssueTypeById( getIssue().getType().getId() ) );
				} else if ( log.isDebugEnabled() ) {
					log.debug( "[create] no issue type available." );
				}
				if ( null != getIssue().getPriority() ) {
					getIssue().setPriority( getIssueService().getIssuePriorityById( getIssue().getPriority().getId() ) );
				}
				
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
			
			
			//getIssue().setStatus( getIssueService().getDefaultIssueStatus() );
			IssueHistory history = new IssueHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			history.setText( "Issue created." );
			history.setIssue( getIssue() );
			
			Set<IssueHistory> initialHistory = new HashSet<IssueHistory>();
			initialHistory.add( history );
			getIssue().setHistory( initialHistory );
			
			getIssueService().addIssue( getIssue(), getContext().getAuthUser() );
			
			// update issue with new generated key
			getIssue().setKey( getIssue().getKey() + getIssue().getId() );
			getIssueService().updateIssue( issue, getContext().getAuthUser() );
			
			if ( null != getIssue().getComponent() ) {
				ComponentHistory componentHistory = new ComponentHistory();
				componentHistory.setCreated( new Date() );
				componentHistory.setCreator( getContext().getAuthUser() );
				componentHistory.setText( "New issue '" + getIssue().getKey() + "' added." );
				componentHistory.setComponent( getIssue().getComponent() );
				getHistoryService().addHistory( componentHistory );
			}
			if ( null != getIssue().getProject() ) {
				ProjectHistory projectHistory = new ProjectHistory();
				projectHistory.setCreated( new Date() );
				projectHistory.setCreator( getContext().getAuthUser() );
				projectHistory.setText( "New issue '" + getIssue().getKey() + "' added." );
				projectHistory.setProject( getIssue().getProject() );
				getHistoryService().addHistory( projectHistory );
			} else if ( null != getIssue().getModule() ) {
				ModuleHistory moduleHistory = new ModuleHistory();
				moduleHistory.setCreated( new Date() );
				moduleHistory.setCreator( getContext().getAuthUser() );
				moduleHistory.setText( "New issue '" + getIssue().getKey() + "' added." );
				moduleHistory.setModule( getIssue().getModule() );
				getHistoryService().addHistory( moduleHistory );
			}
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
			
			StringBuffer message = new StringBuffer();
			
			Issue issue = getIssueService().getIssueById( getIssue().getId() );
			
			String oldSummary = issue.getSummary();
			String newSummary = StringUtils.trimToNull( getIssue().getSummary() );
			if ( false == StringUtils.equals( oldSummary, newSummary ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Summary changed." );
				issue.setSummary( newSummary );
			}
			String oldDescription = issue.getDescription();
			String newDescription = StringUtils.trimToNull( getIssue().getDescription() );
			if ( false == StringUtils.equals( oldDescription, newDescription ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Description changed." );
				issue.setDescription( newDescription );
			}
			
			IssueType oldType = issue.getType();
			IssueType newType = getIssue().getType();
			if ( false == oldType.getId().equals( newType.getId() ) ) {
				newType = getIssueService().getIssueTypeById( newType.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Type changed from '" + oldType.getName() + "' to '" + newType.getName() + "'." );
				issue.setType( newType );
			}
			
			IssuePriority oldPriority = issue.getPriority();
			IssuePriority newPriority = getIssue().getPriority();
			if ( false == oldPriority.getId().equals( newPriority.getId() ) ) {
				newPriority = getIssueService().getIssuePriorityById( newPriority.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Priority changed from '" + oldPriority.getName() + "' to '" + newPriority.getName() + "'." );
				issue.setPriority( newPriority );
			}
			
			IssueStatus oldStatus = issue.getStatus();
			IssueStatus newStatus = getIssue().getStatus();
			if ( false == oldStatus.getId().equals( newStatus.getId() ) ) {
				newStatus = getIssueService().getIssueStatusById( newStatus.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Status changed from '" + oldStatus.getName() + "' to '" + newStatus.getName() + "'." );
				issue.setStatus( newStatus );
			}
			
			User oldReporter = issue.getReporter();
			User newReporter = getIssue().getReporter();
			if ( false == oldReporter.getId().equals( newReporter.getId() ) ) {
				newReporter = getRealmService().getUserById( newReporter.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Reporter changed from '" + oldReporter.getName() + "' to '" + newReporter.getName() + "'." );
				issue.setReporter( newReporter );
			}
			
			User oldAssignee = issue.getAssignee();
			User newAssignee = getIssue().getAssignee();
			if ( false == oldAssignee.getId().equals( newAssignee.getId() ) ) {
				newAssignee = getRealmService().getUserById( newAssignee.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Assignee changed from '" + oldAssignee.getName() + "' to '" + newAssignee.getName() + "'." );
				issue.setAssignee( newAssignee );
			}
			
			IssueHistory history = new IssueHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			if ( 0 < message.length() ) {
				history.setText( message.toString() );
			} else {
				history.setText( "No changes applied" );
			}
			if ( StringUtils.isNotBlank( getHistoryComment() ) ) {
				history.setComment( getHistoryComment() );
			}
			history.setIssue( issue );
			issue.getHistory().add( history );
			
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
	
	public List<IssueHistory> getIssueHistory() {
		List<IssueHistory> history = null;
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			if ( null != issue ) {
				history = new ArrayList<IssueHistory>( issue.getHistory() );
			}
		}
		if ( null != history ) {
			Collections.sort( history, new Comparator<IssueHistory>() {
				public int compare(IssueHistory arg0, IssueHistory arg1) {
					return( arg0.getCreated().compareTo( arg1.getCreated() ) );
				}
			} );
		}
		return( history );
	}
	
	public List<IssueType> getIssueTypes() {
		return( getIssueService().getAllIssueTypes() );
	}
	
	public List<IssuePriority> getIssuePriorities() {
		return( getIssueService().getAllIssuePriorities() );
	}
	
	public List<IssueStatus> getIssueStatus() {
		return( getIssueService().getAllIssueStatus() );
	}
	
	/*
	public List<IssueStatus> getOtherIssueStatus() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[getOtherIssueStatus] issueId = '" + getIssueId() + "'" );
		}
		List<IssueStatus> allStatus = getIssueStatus();
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			if ( null != issue ) {
				IssueStatus issueStatus = issue.getStatus();
				if ( null != issueStatus ) {
					for( Iterator i = allStatus.iterator(); i.hasNext(); ) {
						IssueStatus currentStatus = (IssueStatus)i.next();
						if ( currentStatus.getId().equals( issueStatus.getId() ) ) {
							i.remove();
							break;
						}
					}
				}
			}
		}
		return( allStatus );
	}
	*/
	
	public Resolution typeDescription() {
		String description = null;
		if ( null != getTypeId() ) {
			IssueType type = getIssueService().getIssueTypeById( getTypeId() );
			description = type.getDescription();
		}
		if ( StringUtils.isBlank( description ) ) {
			description = "No description available.";
		}
		return( new StreamingResolution( "text/plain", description ) );
	}
	
	public Resolution priorityDescription() {
		String description = null;
		if ( null != getPriorityId() ) {
			IssuePriority priority = getIssueService().getIssuePriorityById( getPriorityId() );
			description = priority.getDescription();
		}
		if ( StringUtils.isBlank( description ) ) {
			description = "No description available.";
		}
		return( new StreamingResolution( "text/plain", description ) );
	}
	
	public Resolution statusDescription() {
		String description = null;
		if ( null != getStatusId() ) {
			IssueStatus status = getIssueService().getIssueStatusById( getStatusId() );
			description = status.getDescription();
		}
		if ( StringUtils.isBlank( description ) ) {
			description = "No description available.";
		}
		return( new StreamingResolution( "text/plain", description ) );
	}
	
}
