package org.syracus.rapid.actions.components;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.profiles.UserProfile;

@UrlBinding("/protected/component.action")
public class ComponentActionBean extends BaseComponentActionBean {

	private Long componentId;
	private Component component;
	
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
		if ( null != getComponent().getId() ) {
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
}
