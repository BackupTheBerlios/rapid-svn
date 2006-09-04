package org.syracus.rapid.issues;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.dao.IIssueDao;
import org.syracus.rapid.realm.User;

public class IssueService implements IIssueService {

	private IIssueDao issueDao;
	
	
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

	public List<Issue> getIssuesByType(Type type) {
		return( getIssueDao().findByType( type ) );
	}

	public List<Issue> getIssuesOfComponent(Component component) {
		return( getIssueDao().findByComponent( component ) );
	}

	public List<Issue> getIssuesOfModule(Module module) {
		return( getIssueDao().findByModule( module ) );
	}

	public List<Issue> getIssuesOfProject(Project project) {
		return( getIssueDao().findByProject( project ) );
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

	
}
