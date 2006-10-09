package org.syracus.rapid.watches;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;

/**
 * 
 * @author snwiem
 * @hibernate.class table="generic_watches"
 */
public class GenericWatch extends AbstractWatch {

	private Module module;
	private Project project;
	private Component component;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="false"
	 */
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="false"
	*/
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="false"
	*/
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
