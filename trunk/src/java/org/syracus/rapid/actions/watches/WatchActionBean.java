package org.syracus.rapid.actions.watches;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.IComponentService;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.stripes.RapidActionBean;
import org.syracus.rapid.watches.GenericWatch;
import org.syracus.rapid.watches.IGenericWatchService;

@UrlBinding("/protected/watch.action")
public class WatchActionBean extends RapidActionBean {

	protected static final transient Log log = LogFactory.getLog( WatchActionBean.class );
	
	private Long moduleId;
	private Long projectId;
	private Long componentId;
	
	private IComponentService componentService;
	private IGenericWatchService watchService;
	
	
	
	public IComponentService getComponentService() {
		return componentService;
	}

	@SpringBean("componentService")
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}

	public IGenericWatchService getWatchService() {
		return watchService;
	}

	@SpringBean("watchService")
	public void setWatchService(IGenericWatchService watchService) {
		this.watchService = watchService;
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

	public Resolution add() {
		User user = getContext().getAuthUser();
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				getWatchService().addWatch( component, user );
			}
		}
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				getWatchService().addWatch( project, user );
			}
		}
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				getWatchService().addWatch( module, user );
			}
		}
		return( null );
	}
	
	public Resolution remove() {
		User user = getContext().getAuthUser();
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				getWatchService().deleteWatch( component, user );
			}
		}
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				getWatchService().deleteWatch( project, user );
			}
		}
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				getWatchService().deleteWatch( module, user );
			}
		}
		return( null );
	}
	
	public Resolution toggle() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[toggle] BEGIN" );
		}
		Resolution resolution = null;
		
		User user = getContext().getAuthUser();
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			String ret = "";
			if ( null != component ) {
				GenericWatch watch = getWatchService().getWatch( component, user);
				if ( null != watch ) {
					getWatchService().deleteWatch( watch );
					ret = "watch";
				} else {
					getWatchService().addWatch( component, user );
					ret = "unwatch";
				}
			}
			
			return( new StreamingResolution( "text/plain", ret ) );
		}
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			String ret = "";
			if ( null != project ) {
				GenericWatch watch = getWatchService().getWatch( project, user);
				if ( null != watch ) {
					getWatchService().deleteWatch( watch );
					ret = "watch";
				} else {
					getWatchService().addWatch( project, user );
					ret = "unwatch";
				}
			}
			return( new StreamingResolution( "text/plain", ret ) );
		}
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			String ret = "";
			if ( null != module ) {
				GenericWatch watch = getWatchService().getWatch( module, user);
				if ( null != watch ) {
					getWatchService().deleteWatch( watch );
					ret = "watch";
				} else {
					getWatchService().addWatch( module, user );
					ret = "unwatch";
				}
			}
			return( new StreamingResolution( "text/plain", ret ) );
		}
		return( resolution );
	}
	
	public GenericWatch getWatched() {
		User user = getContext().getAuthUser();
		if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			if ( null != component ) {
				return( getWatchService().getWatch( component, user ) );
			}
		}
		if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			if ( null != project ) {
				return( getWatchService().getWatch( project, user ) );
			}
		}
		if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			if ( null != module ) {
				return( getWatchService().getWatch( module, user ) );
			}
		}
		return( null );
	}
}
