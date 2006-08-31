package org.syracus.rapid.comments;

import org.syracus.rapid.components.Component;

/**
 * 
 * @author snwiem
 * @hibernate.class table="component_comments"
 */
public class ComponentComment extends AbstractComment {

	private Component component;
	private ComponentComment parent;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public ComponentComment getParent() {
		return parent;
	}

	public void setParent(ComponentComment parent) {
		this.parent = parent;
	}

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
	
	
}
