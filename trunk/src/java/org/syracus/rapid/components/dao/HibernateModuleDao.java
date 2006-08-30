package org.syracus.rapid.components.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.realm.User;

public class HibernateModuleDao extends HibernateDaoSupport implements
		IModuleDao {

	public void create(Module module) {
		getHibernateTemplate().save( module );
	}

	public void delete(Module module) {
		getHibernateTemplate().delete( module );
	}

	public Module find(Long id) {
		return( (Module)getHibernateTemplate().get( Module.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findAll() {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByCreator(User creator) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.create = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByDescription(String description) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByModifier(User modifier) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.modifier = ?",
				modifier
		) ); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> findByLeader(User leader) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.leader = ?",
				leader
		) ); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> findByName(String name) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findLikeDescription(String description) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findLikeName(String name) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.create LIKE ?",
				name
		) );
	}

	public void update(Module module) {
		getHibernateTemplate().update( module );
	}

}
