package org.syracus.rapid.comments.dao;

import java.util.List;

import org.syracus.rapid.comments.Comment;
import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.realm.User;

public class HibernateCommentDao extends GenericHibernateDao<Comment, Long> implements
		ICommentDao {

	@SuppressWarnings("unchecked")
	public List<Comment> findByComponent(Component component) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.component = ?",
				component
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByCreator(User creator) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByIssue(Issue issue) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.issue = ?",
				issue
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByModule(Module module) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.module = ?",
				module
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByParent(Comment parent) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.parent = ?",
				parent
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByProject(Project project) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.project = ?",
				project
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByComponentAndCreator(Component component,
			User creator) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.component = ? AND c.creator = ?",
				new Object[]{
						component, creator
				}
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByComponentAndParent(Component component,
			Comment parent) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.component = ? AND c.parent = ?",
				new Object[]{
						component, parent
				}
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByIssueAndCreator(Issue issue, User creator) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.issue = ? AND c.creator = ?",
				new Object[]{
						issue, creator
				}
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByIssueAndParent(Issue issue, Comment parent) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.issue = ? AND c.parent = ?",
				new Object[]{
						issue, parent
				}
		) ); 
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByModuleAndCreator(Module module, User creator) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.module = ? AND c.creator = ?",
				new Object[]{
						module, creator
				}
		) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> findByModuleAndParent(Module module,
			Comment parent) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.module = ? AND c.parent = ?",
				new Object[]{
						module, parent
				}
		) ); 
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByProjectAndCreator(Project project,
			User creator) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.project = ? AND c.creator = ?",
				new Object[]{
						project, creator
				}
		) ); 
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByProjectAndParent(Project project,
			Comment parent) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.project = ? AND c.parent = ?",
				new Object[]{
						project, parent
				}
		) ); 
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByParentAndCreator(Comment parent, User creator) {
		return( (List<Comment>)getHibernateTemplate().find(
				"FROM Comment c WHERE c.parent = ? AND c.creator = ?",
				new Object[]{
						parent, creator
				}
		) );
	}

	
}
