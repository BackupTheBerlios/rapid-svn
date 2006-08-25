package org.syracus.rapid.components;

/**
 * 
 * @author snwiem
 * @hibernate.class table="components"
 */
public class Component extends ABaseComponent {

	private Module module;
	private Project project;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
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
	 * @hibernate.many-to-one
	 */
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
