package org.syracus.rapid.profiles.dao;

import java.util.List;
import java.util.Map;

import org.syracus.rapid.profiles.UserProfile;

public interface IUserProfileDao {

	public UserProfile find( Long id );
	public List<UserProfile> findAll();
	public Map<Long,UserProfile> findAllMapped();
	
	public void create( UserProfile userProfile );
	public void update( UserProfile userProfile );
	public void delete( UserProfile userProfile );
	
}
