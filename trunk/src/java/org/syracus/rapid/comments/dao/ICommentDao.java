package org.syracus.rapid.comments.dao;

import java.util.List;

import org.syracus.rapid.comments.Comment;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.realm.User;

public interface ICommentDao {

	public Comment find( Long id );
	
	public void create( Comment comment );
	public void update( Comment comment );
	public void delete( Comment comment );
	
	public List<Comment> findByModule( Module module );
	public List<Comment> findByProject( Project project );
	public List<Comment> findByComponent( Component component );
	public List<Comment> findByIssue( Issue issue );
	public List<Comment> findByParent( Comment parent );
	public List<Comment> findByCreator( User creator );
	
	public List<Comment> findByModuleAndParent( Module module, Comment parent );
	public List<Comment> findByProjectAndParent( Project project, Comment parent );
	public List<Comment> findByComponentAndParent( Component component, Comment parent );
	public List<Comment> findByIssueAndParent( Issue issue, Comment parent );
	
	
	public List<Comment> findByModuleAndCreator( Module module, User creator );
	public List<Comment> findByProjectAndCreator( Project project, User creator );
	public List<Comment> findByComponentAndCreator( Component component, User creator );
	public List<Comment> findByIssueAndCreator( Issue issue, User creator );
	public List<Comment> findByParentAndCreator( Comment parent, User creator );
	
}
