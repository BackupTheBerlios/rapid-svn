package org.syracus.rapid.history;

import org.syracus.rapid.components.Module;

/**
 * 
 * @author snwiem
 * @hibernate.class table="module_history"
 */
public class ModuleHistory extends AbstractHistory {

	private Module module;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true" 
	 */
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	
}
