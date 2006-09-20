package org.syracus.rapid.files;

import org.syracus.rapid.components.Module;

/**
 * 
 * @author snwiem
 * @hibernate.class table="module_attachements"
 */
public class ModuleAttachement extends AbstractAttachement {

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
