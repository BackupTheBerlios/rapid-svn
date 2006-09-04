package org.syracus.rapid.profiles;

import java.util.List;

public interface IProfileService {

	public void addUserProfile( UserProfile profile );
	public void updateUserProfile( UserProfile profile );
	public void deleteUserProfile( UserProfile profile );
	
	public UserProfile getUserProfile( Long id );
	public List<UserProfile> getAllUserProfiles();
	
	
}
