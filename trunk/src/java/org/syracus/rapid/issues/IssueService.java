package org.syracus.rapid.issues;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.dao.IIssueDao;
import org.syracus.rapid.issues.dao.IIssuePriorityDao;
import org.syracus.rapid.issues.dao.IIssueStatusDao;
import org.syracus.rapid.issues.dao.IIssueTypeDao;
import org.syracus.rapid.realm.User;

public class IssueService implements IIssueService {

	private IIssueDao issueDao;
	private IIssueTypeDao issueTypeDao;
	private IIssuePriorityDao issuePriorityDao;
	private IIssueStatusDao issueStatusDao;
	
	
	public IIssueStatusDao getIssueStatusDao() {
		return issueStatusDao;
	}

	public void setIssueStatusDao(IIssueStatusDao issueStatusDao) {
		this.issueStatusDao = issueStatusDao;
	}

	public IIssuePriorityDao getIssuePriorityDao() {
		return issuePriorityDao;
	}

	public void setIssuePriorityDao(IIssuePriorityDao issuePriorityDao) {
		this.issuePriorityDao = issuePriorityDao;
	}

	public IIssueTypeDao getIssueTypeDao() {
		return issueTypeDao;
	}

	public void setIssueTypeDao(IIssueTypeDao issueTypeDao) {
		this.issueTypeDao = issueTypeDao;
	}

	public IIssueDao getIssueDao() {
		return issueDao;
	}

	public void setIssueDao(IIssueDao issueDao) {
		this.issueDao = issueDao;
	}

	public void addIssue(Issue issue, User user) {
		Date now = new Date();
		issue.setCreator( user );
		issue.setCreated( now );
		issue.setModifier( user );
		issue.setModified( now );
		getIssueDao().create( issue );
	}

	public void deleteIssue(Issue issue, User user) {
		getIssueDao().delete( issue );
	}

	public List<Issue> getAllIssues() {
		return( getIssueDao().findAll() );
	}

	public Issue getIssueById(Long id) {
		return( getIssueDao().find( id ) );
	}

	
	public List<Issue> getIssuesByKey(String key) {
		return( getIssueDao().findLikeKey( key ) );
	}

	public List<Issue> getIssuesByAssignee(User assignee) {
		return( getIssueDao().findByAssignee( assignee ) );
	}

	public List<Issue> getIssuesByCreator(User creator) {
		return( getIssueDao().findByCreator( creator ) );
	}

	public List<Issue> getIssuesByDescription(String description) {
		return( getIssueDao().findLikeDescription( description ) );
	}

	public List<Issue> getIssuesByModifier(User modifier) {
		return( getIssueDao().findByModifier( modifier ) );
	}

	public List<Issue> getIssuesByPriority(Priority priority) {
		return( getIssueDao().findByPriority( priority ) );
	}

	public List<Issue> getIssuesByReporter(User reporter) {
		return( getIssueDao().findByReporter( reporter ) );
	}

	public List<Issue> getIssuesByStatus(Status status) {
		return( getIssueDao().findByStatus( status ) );
	}

	public List<Issue> getIssuesBySummary(String summary) {
		return( getIssueDao().findLikeSummary( summary ) );
	}

	public List<Issue> getIssuesByType(IssueType type) {
		return( getIssueDao().findByType( type ) );
	}

	public List<Issue> getIssuesOfComponent(Component component) {
		return( getIssueDao().findByComponent( component ) );
	}

	public List<Issue> getAllIssuesOfModule(Module module) {
		return( getIssueDao().findByModule( module, true ) );
	}
	public List<Issue> getIssuesOfModule(Module module) {
		return( getIssueDao().findByModule( module, false ) );
	}

	public List<Issue> getAllIssuesOfProject(Project project) {
		return( getIssueDao().findByProject( project, true ) );
	}
	public List<Issue> getIssuesOfProject(Project project) {
		return( getIssueDao().findByProject( project, false ) );
	}

	public void updateIssue(Issue issue, User user) {
		Date now = new Date();
		issue.setModifier( user );
		issue.setModified( now );
		getIssueDao().update( issue );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByCriteria(DetachedCriteria criteria) {
		return( (List<Issue>)getIssueDao().findByCriteria( criteria ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByCriteria(DetachedCriteria criteria, int first, int max ) {
		return( (List<Issue>)getIssueDao().findByCriteria( criteria, first, max ) );
	}

	public void addIssue(Issue issue, Component component, User user) {
		issue.setModule( component.getModule() );
		issue.setProject( component.getProject() );
		issue.setComponent( component );
		addIssue( issue, user );
	}

	public void addIssue(Issue issue, Module module, User user) {
		issue.setModule( module );
		issue.setProject( null );
		issue.setComponent( null );
		addIssue( issue, user );
	}

	public void addIssue(Issue issue, Project project, User user) {
		issue.setModule( project.getModule() );
		issue.setProject( project );
		issue.setComponent( null );
		addIssue( issue, user );
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getNewestIssuesByAssignee(User assignee, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Issue.class )
			.add( Restrictions.eq( "assignee", assignee ) )
			.addOrder( Order.desc( "modified" ) );
	
		return( (List<Issue>)getIssueDao().findByCriteria(criteria, 0, max ) );
	}

	public void addIssueType(IssueType type) {
		getIssueTypeDao().create( type );
	}

	public void deleteIssueType(IssueType type) {
		getIssueTypeDao().delete( type );
	}

	public List<IssueType> getAllIssueTypes() {
		return( getIssueTypeDao().findAll() );
	}

	public IssueType getIssueTypeById(Long id) {
		return( getIssueTypeDao().find( id ) );
	}

	public void updateIssueType(IssueType type) {
		getIssueTypeDao().update( type );
	}

	public void addIssuePriority(IssuePriority priority) {
		getIssuePriorityDao().create( priority );
	}

	public void deleteIssuePriority(IssuePriority priority) {
		getIssuePriorityDao().delete( priority );
	}

	public List<IssuePriority> getAllIssuePriorities() {
		return( getIssuePriorityDao().findAll() );
	}

	public IssuePriority getIssuePriorityById(Long id) {
		return( getIssuePriorityDao().find( id ) );
	}

	public void updateIssuePriority(IssuePriority priority) {
		getIssuePriorityDao().update( priority );
	}

	public void addIssueStatus(IssueStatus status) {
		getIssueStatusDao().create( status );
	}

	public void deleteIssueStatus(IssueStatus status) {
		getIssueStatusDao().delete( status );
	}

	public List<IssueStatus> getAllIssueStatus() {
		return( getIssueStatusDao().findAll() );
	}

	public IssueStatus getIssueStatusById(Long id) {
		return( getIssueStatusDao().find( id ) );
	}

	public void updateIssueStatus(IssueStatus status) {
		getIssueStatusDao().update( status );
	}

	public IssueStatus getDefaultIssueStatus() {
		return( getIssueStatusDao().findByOrder( 0 ) );
	}

	
	
}
