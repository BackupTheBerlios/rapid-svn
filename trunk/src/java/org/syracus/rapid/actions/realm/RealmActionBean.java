package org.syracus.rapid.actions.realm;

import net.sourceforge.stripes.integration.spring.SpringBean;

import org.syracus.rapid.realm.IRealmService;
import org.syracus.rapid.stripes.RapidActionBean;

public class RealmActionBean extends RapidActionBean {

	private IRealmService realmService;

	public IRealmService getRealmService() {
		return realmService;
	}

	@SpringBean("realmService")
	public void setRealmService(IRealmService realmService) {
		this.realmService = realmService;
	}
	
}
