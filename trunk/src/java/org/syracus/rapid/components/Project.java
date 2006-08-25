package org.syracus.rapid.components;

/**
 * 
 * @author snwiem
 * @hibernate.class table="projects"
 */
public class Project extends ABaseComponent {

	private Module module;

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
	
	
}
