package org.syracus.rapid.comments.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.comments.ProjectComment;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public class HibernateProjectCommentDao extends HibernateDaoSupport implements
		IProjectCommentDao {

	public void create(ProjectComment comment) {
		getHibernateTemplate().save( comment );
	}

	public void delete(ProjectComment comment) {
		getHibernateTemplate().delete( comment );
	}

	public ProjectComment find(Long id) {
		return( (ProjectComment)getHibernateTemplate().get( ProjectComment.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findAll() {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findByCreator(User creator) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findByDescription(String description) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findByModifier(User modifier) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findByProject(Project project) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.project = ?",
				project
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findByParent(ProjectComment parent) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.parent = ?",
				parent
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findBySummary(String summary) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.summary = ?",
				summary
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findLikeDescription(String description) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<ProjectComment> findLikeSummary(String summary) {
		return( (List<ProjectComment>)getHibernateTemplate().find(
				"FROM ProjectComment c WHERE c.summary LIKE ?",
				summary
		) );
	}

	public void update(ProjectComment comment) {
		getHibernateTemplate().update( comment );
	}
}
