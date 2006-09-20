package org.syracus.rapid.files;

import org.syracus.rapid.components.Component;

/**
 * 
 * @author snwiem
 * @hibernate.class table="component_attachements"
 */
public class ComponentAttachement extends AbstractAttachement {

	private Component component;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true" 
	 */
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
	
	
}
