package org.syracus.rapid.actions.components;

import net.sourceforge.stripes.integration.spring.SpringBean;

import org.syracus.rapid.components.IComponentService;
import org.syracus.rapid.history.IHistoryService;
import org.syracus.rapid.issues.IIssueService;
import org.syracus.rapid.realm.IRealmService;
import org.syracus.rapid.stripes.RapidActionBean;

public class BaseComponentActionBean extends RapidActionBean {

	private IComponentService componentService;
	private IIssueService issueService;
	private IRealmService realmService;
	private IHistoryService historyService;

	
	
	public IHistoryService getHistoryService() {
		return historyService;
	}

	@SpringBean("historyService")
	public void setHistoryService(IHistoryService historyService) {
		this.historyService = historyService;
	}

	public IRealmService getRealmService() {
		return realmService;
	}

	@SpringBean("realmService")
	public void setRealmService(IRealmService realmService) {
		this.realmService = realmService;
	}

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
