package org.syracus.rapid.actions.components;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.components.Module;

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
		getComponentService().deleteModule( module, getContext().getAuthUser() );
		return( new ForwardResolution( "" ) );
	}
	
	public List<Module> getOwnModules() {
		List<Module> modules = getComponentService().getModulesByLeader( getContext().getAuthUser() );
		Collections.sort( modules, new Comparator<Module>() {
			public int compare(Module arg0, Module arg1) {
				return( arg0.getModified().compareTo( arg1.getModified() ) );
			}
		} );
		return( modules );
	}
}
