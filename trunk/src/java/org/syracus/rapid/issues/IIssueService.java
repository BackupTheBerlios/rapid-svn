package org.syracus.rapid.issues;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public interface IIssueService {

	public void addIssueType( IssueType type );
	public void updateIssueType( IssueType type );
	public void deleteIssueType( IssueType type );
	public IssueType getIssueTypeById( Long id );
	public List<IssueType> getAllIssueTypes();
	
	public void addIssuePriority( IssuePriority priority );
	public void updateIssuePriority( IssuePriority priority );
	public void deleteIssuePriority( IssuePriority priority );
	public IssuePriority getIssuePriorityById( Long id );
	public List<IssuePriority> getAllIssuePriorities();
	
	public void addIssueStatus( IssueStatus status );
	public void updateIssueStatus( IssueStatus status );
	public void deleteIssueStatus( IssueStatus status );
	public IssueStatus getIssueStatusById( Long id );
	public List<IssueStatus> getAllIssueStatus();
	public IssueStatus getDefaultIssueStatus();
	
	public void addIssue( Issue issue, User user );
	public void addIssue( Issue issue, Module module, User user );
	public void addIssue( Issue issue, Project project, User user );
	public void addIssue( Issue issue, Component component, User user );
	
	public void updateIssue( Issue issue, User user );
	public void deleteIssue( Issue issue, User user );
	
	public Issue getIssueById( Long id );
	public List<Issue> getAllIssues();
	
	public List<Issue> getIssuesOfModule( Module module );
	public List<Issue> getAllIssuesOfModule( Module module );
	public List<Issue> getIssuesOfProject( Project project );
	public List<Issue> getAllIssuesOfProject( Project project );
	public List<Issue> getIssuesOfComponent( Component component );
	
	public List<Issue> getIssuesByKey( String key );
	public List<Issue> getIssuesBySummary( String summary );
	public List<Issue> getIssuesByDescription( String description );
	
	public List<Issue> getIssuesByCreator( User creator );
	public List<Issue> getIssuesByModifier( User modifier );
	public List<Issue> getIssuesByReporter( User reporter );
	public List<Issue> getIssuesByAssignee( User assignee );
	
	public List<Issue> getIssuesByType( IssueType type );
	public List<Issue> getIssuesByStatus( Status status );
	public List<Issue> getIssuesByPriority( Priority priority );
	
	public List<Issue> getIssuesByCriteria( DetachedCriteria criteria );
	public List<Issue> getIssuesByCriteria( DetachedCriteria criteria, int first, int max );
	
	public List<Issue> getNewestIssuesByAssignee( User assignee, int max );
}
