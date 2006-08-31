package org.syracus.rapid.comments.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.comments.IssueComment;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.realm.User;

public class HibernateIssueCommentDao extends HibernateDaoSupport implements
		IIssueCommentDao {

	public void create(IssueComment comment) {
		getHibernateTemplate().save( comment );
	}

	public void delete(IssueComment comment) {
		getHibernateTemplate().delete( comment );
	}

	public IssueComment find(Long id) {
		return( (IssueComment)getHibernateTemplate().get( IssueComment.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findAll() {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findByCreator(User creator) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findByDescription(String description) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findByModifier(User modifier) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findByIssue(Issue issue) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.issue = ?",
				issue
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findByParent(IssueComment parent) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.parent = ?",
				parent
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findBySummary(String summary) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.summary = ?",
				summary
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findLikeDescription(String description) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<IssueComment> findLikeSummary(String summary) {
		return( (List<IssueComment>)getHibernateTemplate().find(
				"FROM IssueComment c WHERE c.summary LIKE ?",
				summary
		) );
	}

	public void update(IssueComment comment) {
		getHibernateTemplate().update( comment );
	}

}
