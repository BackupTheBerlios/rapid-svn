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
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.files.ModuleAttachement;
import org.syracus.rapid.history.ModuleHistory;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.realm.User;

@UrlBinding("/protected/module.action")
public class ModuleActionBean extends BaseComponentActionBean {

	private Long moduleId;
	private Module module;
	
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Resolution view() {
		Module module = getComponentService().getModuleById( getModuleId() );
		setModule( module );
		return( new ForwardResolution( "/protected/components/moduleView.jsp" ) );
	}
	
	public Resolution edit() {
		Module module = getComponentService().getModuleById( getModuleId() );
		setModule( module );
		return( new ForwardResolution( "/protected/components/moduleEdit.jsp" ) );
	}
	
	public Resolution save() {
		if ( null == getModule().getId() ) {
			if ( StringUtils.isBlank( getModule().getKey() ) ) {
				getContext().getValidationErrors().add( "key", new SimpleError( "A key is required." ) );
			}
			if ( StringUtils.isBlank( getModule().getName() ) ) {
				getContext().getValidationErrors().add( "name", new SimpleError( "A name is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				return( getContext().getSourcePageResolution() );
			}
			
			ModuleHistory history = new ModuleHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			history.setText( "Module created." );
			history.setModule( getModule() );
			
			Set<ModuleHistory> initialHistory = new HashSet<ModuleHistory>();
			initialHistory.add( history );
			getModule().setHistory( initialHistory );
			
			getComponentService().addModule( getModule(), getContext().getAuthUser() );
		} else {
			if ( StringUtils.isBlank( getModule().getName() ) ) {
				getContext().getValidationErrors().add( "name", new SimpleError( "A name is required." ) );
			}
			if ( getContext().getValidationErrors().hasFieldErrors() ) {
				return( getContext().getSourcePageResolution() );
			}
			
			Module module = getComponentService().getModuleById( getModule().getId() );
			StringBuffer message = new StringBuffer();
			
			String oldName = module.getName();
			String newName = StringUtils.trimToNull( getModule().getName() );
			if ( false == StringUtils.equals( oldName, newName ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Name changed from '" + oldName + "' to '" + newName + "'." );
				module.setName( newName );
			}
			
			String oldDescription = module.getDescription();
			String newDescription = StringUtils.trimToNull( getModule().getDescription() );
			if ( false == StringUtils.equals( oldDescription, newDescription ) ) {
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Description changed." );
				module.setDescription( newDescription );
			}
			
			User oldLeader = module.getLeader();
			User newLeader = getModule().getLeader();
			if ( false == oldLeader.getId().equals( newLeader.getId() ) ) {
				newLeader = getRealmService().getUserById( newLeader.getId() );
				if ( 0 < message.length() ) {
					message.append( "\n" );
				}
				message.append( "Leader changed from '" + oldLeader.getName() + "' to '" + newLeader.getName() + "'." );
				module.setLeader( newLeader );
			}
			
			ModuleHistory history = new ModuleHistory();
			history.setCreated( new Date() );
			history.setCreator( getContext().getAuthUser() );
			if ( 0 < message.length() ) {
				history.setText( message.toString() );
			} else {
				history.setText( "No changes applied." );
			}
			history.setModule( module );
			module.getHistory().add( history );
			
			getComponentService().updateModule( module, getContext().getAuthUser() );
		}
		return( new RedirectResolution( "/protected/module.action" )
			.addParameter( "view", "" )
			.addParameter( "moduleId", getModule().getId() )
		);
	}
	
	public Resolution delete() {
		Module module = getComponentService().getModuleById( getModuleId() );
		if ( null != module ) {
			Integer projectCount = getComponentService().getNumberOfProjects( module );
			if ( 0 == projectCount.intValue() ) {
				Integer componentCount = getComponentService().getNumberOfComponents( module );
				if ( 0 == componentCount ) {
					getComponentService().deleteModule( module, getContext().getAuthUser() );
					return( new ForwardResolution( "/protected/components/moduleList.jsp" ) );
				}
			}
		}
		System.out.println( "*** Module not empty !!" );
		getContext().getValidationErrors().addGlobalError(
				new SimpleError( "You can't delete this module. There are still projects referencing it." )
		);
		return( new ForwardResolution( "/protected/components/moduleError.jsp" ) );
	}
	
	public Resolution key() {
		String key = "";
		if ( null != getModuleId() && -1 != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				key = module.getKey();
			}
		}
		return( new StreamingResolution( "text", key ) );
	}
	
	public List<Module> getOwnModules() {
		int maxModules = Integer.parseInt( getContext().getUserProfile().getProperty( UserProfile.KEY_MAX_MODULES, UserProfile.DEF_MAX_MODULES ) );
		return( getComponentService().getNewestModulesByLeader( getContext().getAuthUser(), maxModules ) );
	}
	
	public List<Module> getAllModules() {
		return( getComponentService().getAllModules() );
	}
	
	/*
	public List<Project> getModuleProjects() {
		Module module = getComponentService().getModuleById( getModuleId() );
		List<Project> projects = getComponentService().getProjectsOfModule( module );
		return( projects );
	}
	*/
	
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
	
	public List<Component> getModuleComponents() {
		List<Component> components = null;
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				components = getComponentService().getAllComponentsOfModule( module );
			}
		}
		return( components );
	}
	
	public List<Issue> getModuleIssues() {
		List<Issue> issues = null;
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				issues = getIssueService().getAllIssuesOfModule( module );
			}
		}
		return( issues );
	}
	
	public Set<ModuleAttachement> getModuleAttachements() {
		Set<ModuleAttachement> attachements = null;
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				attachements = module.getAttachements();
			}
		}
		return( attachements );
	}
	
	public List<ModuleHistory> getModuleHistory() {
		List<ModuleHistory> history = null;
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				history = new ArrayList<ModuleHistory>( module.getHistory() );
			}
		}
		if ( null != history ) {
			Collections.sort( history, new Comparator<ModuleHistory>() {
				public int compare(ModuleHistory arg0, ModuleHistory arg1) {
					return( arg0.getCreated().compareTo( arg1.getCreated() ) );
				}
			} );
		}
		return( history );
	}
	
}
