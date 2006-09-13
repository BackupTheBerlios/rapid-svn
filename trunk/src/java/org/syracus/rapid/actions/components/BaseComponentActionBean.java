package org.syracus.rapid.actions.components;

import net.sourceforge.stripes.integration.spring.SpringBean;

import org.syracus.rapid.components.IComponentService;
import org.syracus.rapid.issues.IIssueService;
import org.syracus.rapid.stripes.RapidActionBean;

public class BaseComponentActionBean extends RapidActionBean {

	private IComponentService componentService;
	private IIssueService issueService;

	
	public IIssueService getIssueService() {
		return issueService;
	}

	@SpringBean("issueService")
	public void setIssueService(IIssueService issueService) {
		this.issueService = issueService;
	}

	public IComponentService getComponentService() {
		return componentService;
	}

	@SpringBean("componentService")
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}
	
	
}
