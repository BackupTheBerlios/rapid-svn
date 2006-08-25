package org.syracus.rapid.realm;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.syracus.rapid.realm.dao.IUserDao;

public class RealmServiceImpl implements IRealmService {

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(User user) {
		getUserDao().create( user );
	}

	public User authenticateUser(String account, String password) {
		List<User> users = getUsersByAccount( account );
		if ( null == users || users.isEmpty() ) {
			return( null );
		}
		if ( 1 < users.size() ) {
			throw new DataIntegrityViolationException( "More than one users found for account '" + account + "'" );
		}
		
		User user = users.get( 0 );
		if ( null == user.getPassword() ) {
			// user account is disabled
			return( null );
		}
		
		if ( !user.getPassword().equals( password ) ) {
			return( null );
		}
		
		return( user );
	}

	public void deleteUser(User user) {
		getUserDao().delete( user );
	}

	public List<User> getAllUsers() {
		return( getUserDao().findAll() );
	}

	public User getUserById(Long id) {
		return( getUserDao().find( id ) );
	}

	public List<User> getUsersByAccount(String account) {
		return( getUserDao().findByAccount( account ) );
	}

	public List<User> getUsersByEmail(String email) {
		return( getUserDao().findLikeEmail( email ) );
	}

	public List<User> getUsersByName(String name) {
		return( getUserDao().findLikeName( name ) );
	}

	public void updateUser(User user) {
		getUserDao().update( user );
	}
	
	
	
}
