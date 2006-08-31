package org.syracus.rapid.comments;

import org.syracus.rapid.components.Module;

/**
 * 
 * @author snwiem
 * @hibernate.class table="module_comments"
 */
public class ModuleComment extends AbstractComment {

	private Module module;
	private ModuleComment parent;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public ModuleComment getParent() {
		return parent;
	}

	public void setParent(ModuleComment parent) {
		this.parent = parent;
	}

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
