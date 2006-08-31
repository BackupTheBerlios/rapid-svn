package org.syracus.rapid.comments.dao;

import java.util.List;

import org.syracus.rapid.comments.IssueComment;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.realm.User;

public interface IIssueCommentDao {

	public void create( IssueComment comment );
	public void update( IssueComment comment );
	public void delete( IssueComment comment );
	
	public IssueComment find( Long id );
	
	public List<IssueComment> findAll();
	public List<IssueComment> findByIssue( Issue issue );
	public List<IssueComment> findByParent( IssueComment parent );
	public List<IssueComment> findBySummary( String summary );
	public List<IssueComment> findLikeSummary( String description );
	public List<IssueComment> findByDescription( String description );
	public List<IssueComment> findLikeDescription( String description );
	public List<IssueComment> findByCreator( User creator );
	public List<IssueComment> findByModifier( User modifier );
	
}
