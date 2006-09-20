package org.syracus.rapid.issues.dao;

import java.util.List;

import org.syracus.rapid.common.IPersistanceDao;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.issues.IssueType;
import org.syracus.rapid.issues.Priority;
import org.syracus.rapid.issues.Status;
import org.syracus.rapid.realm.User;

public interface IIssueDao extends IPersistanceDao {

	public void create( Issue issue );
	public void update( Issue issue );
	public void delete( Issue issue );
	
	public Issue find( Long id );
	
	public List<Issue> findAll();
	public List<Issue> findByKey( String key );
	public List<Issue> findLikeKey( String key );
	public List<Issue> findByModule( Module module, boolean recursive );
	public List<Issue> findByProject( Project project, boolean recursive );
	public List<Issue> findByComponent( Component component );
	public List<Issue> findBySummary( String summary );
	public List<Issue> findLikeSummary( String summary );
	public List<Issue> findByDescription( String description );
	public List<Issue> findLikeDescription( String description );
	public List<Issue> findByCreator( User creator );
	public List<Issue> findByModifier( User modifier );
	public List<Issue> findByAssignee( User leader );
	public List<Issue> findByReporter( User reporter );
	
	public List<Issue> findByType( IssueType type );
	public List<Issue> findByStatus( Status status );
	public List<Issue> findByPriority( Priority priority );
	

}
