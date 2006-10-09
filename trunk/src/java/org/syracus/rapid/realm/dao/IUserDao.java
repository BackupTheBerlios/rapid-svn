package org.syracus.rapid.realm.dao;

import java.util.List;

import org.syracus.rapid.common.IGenericHibernateDao;
import org.syracus.rapid.realm.User;

public interface IUserDao extends IGenericHibernateDao<User, Long>{

	public List<User> findByAccount( String account );
	public List<User> findLikeAccount( String account );
	public List<User> findByName( String name );
	public List<User> findLikeName( String name );
	public List<User> findByEmail( String email );
	public List<User> findLikeEmail( String email );
	
}
