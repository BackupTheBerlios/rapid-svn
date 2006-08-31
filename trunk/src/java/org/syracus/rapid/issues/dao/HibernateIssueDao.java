package org.syracus.rapid.issues.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.issues.Priority;
import org.syracus.rapid.issues.Status;
import org.syracus.rapid.issues.Type;
import org.syracus.rapid.realm.User;

public class HibernateIssueDao extends HibernateDaoSupport implements IIssueDao {

	public void create(Issue issue) {
		getHibernateTemplate().save( issue );
	}

	public void delete(Issue issue) {
		getHibernateTemplate().delete( issue );
	}

	public Issue find(Long id) {
		return( (Issue)getHibernateTemplate().get( Issue.class, id) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findAll() {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByAssignee(User assignee) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.assignee = ?",
				assignee
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByComponent(Component component) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.component = ?",
				component
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByCreator(User creator) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByDescription(String description) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByModifier(User modifier) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByModule(Module module) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.module = ?",
				module
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByPriority(Priority priority) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.priority = ?",
				priority
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByProject(Project project) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.project = ?",
				project
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByReporter(User reporter) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.reporter = ?",
				reporter
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByStatus(Status status) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.status = ?",
				status
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findBySummary(String summary) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.summary = ?",
				summary
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findByType(Type type) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.type = ?",
				type
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findLikeDescription(String description) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> findLikeSummary(String summary) {
		return( (List<Issue>)getHibernateTemplate().find(
				"FROM Issue i WHERE i.summary LIKE ?",
				summary
		) );
	}

	public void update(Issue issue) {
		getHibernateTemplate().update( issue );
	}

}