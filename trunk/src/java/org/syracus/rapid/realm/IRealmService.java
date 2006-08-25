package org.syracus.rapid.realm;

import java.util.List;

public interface IRealmService {

	public void addUser( User user );
	public void updateUser( User user );
	public void deleteUser( User user );
	
	public User getUserById( Long id );
	public List<User> getAllUsers();
	public List<User> getUsersByAccount( String account );
	public List<User> getUsersByName( String name );
	public List<User> getUsersByEmail( String email );
	
	public User authenticateUser( String account, String password );
	
}
