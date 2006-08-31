package org.syracus.rapid.comments.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.comments.ModuleComment;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.realm.User;

public class HibernateModuleCommentDao extends HibernateDaoSupport implements
		IModuleCommentDao {

	public void create(ModuleComment comment) {
		getHibernateTemplate().save( comment );
	}

	public void delete(ModuleComment comment) {
		getHibernateTemplate().delete( comment );
	}

	public ModuleComment find(Long id) {
		return( (ModuleComment)getHibernateTemplate().get( ModuleComment.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findAll() {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findByCreator(User creator) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findByDescription(String description) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findByModifier(User modifier) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findByModule(Module module) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.module = ?",
				module
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findByParent(ModuleComment parent) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.parent = ?",
				parent
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findBySummary(String summary) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.summary = ?",
				summary
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findLikeDescription(String description) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ModuleComment> findLikeSummary(String summary) {
		return( (List<ModuleComment>)getHibernateTemplate().find(
				"FROM ModuleComment c WHERE c.summary LIKE ?",
				summary
		) );
	}

	public void update(ModuleComment comment) {
		getHibernateTemplate().update( comment );
	}

}
