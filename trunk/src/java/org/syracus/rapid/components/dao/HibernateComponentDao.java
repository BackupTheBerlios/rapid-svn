package org.syracus.rapid.components.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public class HibernateComponentDao extends HibernateDaoSupport implements
		IComponentDao {

	public void create(Component component) {
		getHibernateTemplate().save( component );
	}

	public void delete(Component component) {
		getHibernateTemplate().delete( component );
	}

	public Component find(Long id) {
		return( (Component)getHibernateTemplate().get( Component.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findAll() {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByCreator(User creator) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByDescription(String description) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByModifier(User modifier) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByModule(Module module) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.module = ?",
				module
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByModuleAndProject(Module module, Project project) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.module = ? AND c.project = ?",
				new Object[]{ module, project }
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByName(String name) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByProject(Project project) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.project = ?",
				project
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findLikeDescription(String description) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findLikeName(String name) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.name LIKE ?",
				name
		) );
	}

	public void update(Component component) {
		getHibernateTemplate().update( component );
	}

}
