package org.syracus.rapid.actions.realm;

import java.util.List;

import net.sourceforge.stripes.integration.spring.SpringBean;

import org.syracus.rapid.profiles.IProfileService;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.realm.IRealmService;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.stripes.RapidActionBean;

public class RealmActionBean extends RapidActionBean {

	private IRealmService realmService;
	private IProfileService profileService;
	
	public IRealmService getRealmService() {
		return realmService;
	}

	@SpringBean("realmService")
	public void setRealmService(IRealmService realmService) {
		this.realmService = realmService;
	}
	
	public IProfileService getProfileService() {
		return profileService;
	}

	@SpringBean("profileService")
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

	public List<User> getAllUsers() {
		return( getRealmService().getAllUsers() );
	}
	
	public List<UserProfile> getAllUserProfiles() {
		return( getProfileService().getAllUserProfiles() );
	}
	
}
