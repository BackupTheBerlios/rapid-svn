package org.syracus.rapid.profiles;

import java.util.List;

import org.syracus.rapid.profiles.dao.IUserProfileDao;

public class ProfileService implements IProfileService {

	private IUserProfileDao userProfileDao;
	
	
	public IUserProfileDao getUserProfileDao() {
		return userProfileDao;
	}

	public void setUserProfileDao(IUserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}

	public void addUserProfile(UserProfile profile) {
		getUserProfileDao().create( profile );
	}

	public void deleteUserProfile(UserProfile profile) {
		getUserProfileDao().delete( profile );
	}

	public List<UserProfile> getAllUserProfiles() {
		return( getUserProfileDao().findAll() );
	}

	public UserProfile getUserProfile(Long id) {
		return( getUserProfileDao().find( id ) );
	}

	public void updateUserProfile(UserProfile profile) {
		getUserProfileDao().update( profile );
	}

}
