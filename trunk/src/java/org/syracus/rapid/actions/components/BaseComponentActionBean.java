package org.syracus.rapid.actions.components;

import net.sourceforge.stripes.integration.spring.SpringBean;

import org.syracus.rapid.components.IComponentService;
import org.syracus.rapid.stripes.RapidActionBean;

public class BaseComponentActionBean extends RapidActionBean {

	private IComponentService componentService;

	public IComponentService getComponentService() {
		return componentService;
	}

	@SpringBean("componentService")
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}
	
	
}
