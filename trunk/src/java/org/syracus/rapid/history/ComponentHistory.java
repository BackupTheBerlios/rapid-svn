package org.syracus.rapid.history;

import org.syracus.rapid.components.Component;

/**
 * 
 * @author snwiem
 * @hibernate.class table="component_history"
 */
public class ComponentHistory extends AbstractHistory {

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
