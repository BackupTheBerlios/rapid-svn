package org.syracus.rapid.components.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public class HibernateProjectDao extends HibernateDaoSupport implements
		IProjectDao {

	public void create(Project project) {
		getHibernateTemplate().save( project );
	}

	public void delete(Project project) {
		getHibernateTemplate().delete( project );
	}

	public Project find(Long id) {
		return( (Project)getHibernateTemplate().get( Project.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findAll() {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByCreator(User creator) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.create = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByDescription(String description) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByModifier(User modifier) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByModule(Module module) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.module = ?",
				module
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByName(String name) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findLikeDescription(String description) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findLikeName(String name) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.name LIKE ?",
				name
		) );
	}

	public void update(Project project) {
		getHibernateTemplate().update( project );
	}

}
