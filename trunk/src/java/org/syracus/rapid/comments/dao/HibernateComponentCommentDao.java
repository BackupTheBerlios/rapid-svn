package org.syracus.rapid.comments.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.comments.ComponentComment;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.realm.User;

public class HibernateComponentCommentDao extends HibernateDaoSupport implements
		IComponentCommentDao {

	public void create(ComponentComment comment) {
		getHibernateTemplate().save( comment );
	}

	public void delete(ComponentComment comment) {
		getHibernateTemplate().delete( comment );
	}

	public ComponentComment find(Long id) {
		return( (ComponentComment)getHibernateTemplate().get( ComponentComment.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findAll() {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findByCreator(User creator) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findByDescription(String description) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findByModifier(User modifier) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findByComponent(Component component) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.component = ?",
				component
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findByParent(ComponentComment parent) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.parent = ?",
				parent
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findBySummary(String summary) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.summary = ?",
				summary
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findLikeDescription(String description) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ComponentComment> findLikeSummary(String summary) {
		return( (List<ComponentComment>)getHibernateTemplate().find(
				"FROM ComponentComment c WHERE c.summary LIKE ?",
				summary
		) );
	}

	public void update(ComponentComment comment) {
		getHibernateTemplate().update( comment );
	}

}
