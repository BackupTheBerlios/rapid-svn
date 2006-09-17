package org.syracus.rapid.actions.components;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.profiles.UserProfile;

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
			getComponentService().addModule( getModule(), getContext().getAuthUser() );
		} else {
			Module module = getComponentService().getModuleById( getModule().getId() );
			module.setName( getModule().getName() );
			module.setDescription( getModule().getDescription() );
			module.setLeader( getModule().getLeader() );
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
	
}
