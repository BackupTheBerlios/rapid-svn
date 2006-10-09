package org.syracus.rapid.realm.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.realm.User;

public class HibernateUserDao extends GenericHibernateDao<User, Long> implements IUserDao {

	

	@SuppressWarnings("unchecked")
	public List<User> findByAccount(String account) {
		return( (List<User>)getHibernateTemplate().find(
				"FROM User u WHERE u.account = ?",
				account
		) );
	}

	@SuppressWarnings("unchecked")
	public List<User> findByEmail(String email) {
		return( (List<User>)getHibernateTemplate().find(
				"FROM User u WHERE u.email = ?",
				email
		) );
	}

	@SuppressWarnings("unchecked")
	public List<User> findByName(String name) {
		return( (List<User>)getHibernateTemplate().find(
				"FROM User u WHERE u.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<User> findLikeAccount(String account) {
		return( (List<User>)getHibernateTemplate().find(
				"FROM User u WHERE u.account LIKE ?",
				account
		) );
	}

	@SuppressWarnings("unchecked")
	public List<User> findLikeEmail(String email) {
		return( (List<User>)getHibernateTemplate().find(
				"FROM User u WHERE u.email LIKE ?",
				email
		) );
	}

	@SuppressWarnings("unchecked")
	public List<User> findLikeName(String name) {
		return( (List<User>)getHibernateTemplate().find(
				"FROM User u WHERE u.name LIKE ?",
				name
		) );
	}

}
