package org.syracus.rapid.comments;

import java.util.Date;
import java.util.List;

import org.syracus.rapid.comments.dao.ICommentDao;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.realm.User;

public class CommentService implements ICommentService {

	private ICommentDao commentDao;

	public ICommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void addComment(Comment comment, Comment parent, User user) {
		comment.setIssue( parent.getIssue() );
		comment.setComponent( parent.getComponent() );
		comment.setProject( parent.getProject() );
		comment.setModule( parent.getModule() );
		comment.setParent( parent );
		addComment( comment, user );
	}

	public void addComment(Comment comment, Component component, User user) {
		comment.setIssue( null );
		comment.setComponent( component );
		comment.setProject( null );
		comment.setModule( null );
		comment.setParent( null );
		addComment( comment, user );
	}

	public void addComment(Comment comment, Issue issue, User user) {
		comment.setIssue( issue );
		comment.setComponent( null );
		comment.setProject( null );
		comment.setModule( null );
		comment.setParent( null );
		addComment( comment, user );
	}

	public void addComment(Comment comment, Module module, User user) {
		comment.setIssue( null );
		comment.setComponent( null );
		comment.setProject( null );
		comment.setModule( module );
		comment.setParent( null );
		addComment( comment, user );
	}

	public void addComment(Comment comment, Project project, User user) {
		comment.setIssue( null );
		comment.setComponent( null );
		comment.setProject( project );
		comment.setModule( null );
		comment.setParent( null );
		addComment( comment, user );
	}

	public void addComment(Comment comment, User user) {
		Date now = new Date();
		comment.setCreator( user );
		comment.setCreated( now );
		comment.setModified( now );
		getCommentDao().create( comment );
	}

	public void deleteComment(Comment comment, User user) {
		getCommentDao().delete( comment );
	}

	public Comment getCommentById(Long id) {
		return( getCommentDao().find( id ) );
	}

	public List<Comment> getComponentComments(Component component) {
		return( getCommentDao().findByComponent( component ) );
	}

	public List<Comment> getIssueComments(Issue issue) {
		return( getCommentDao().findByIssue( issue ) );
	}

	public List<Comment> getModuleComments(Module module) {
		return( getCommentDao().findByModule( module ) );
	}

	public List<Comment> getProjectComments(Project project) {
		return( getCommentDao().findByProject( project ) );
	}

	public void updateComment(Comment comment, User user) {
		Date now = new Date();
		comment.setModified( now );
		getCommentDao().update( comment );
	}
	
	
	/*
	private IModuleCommentDao moduleCommentDao;
	private IProjectCommentDao projectCommentDao;
	private IComponentCommentDao componentCommentDao;
	private IIssueCommentDao issueCommentDao;
	
	
	public IComponentCommentDao getComponentCommentDao() {
		return componentCommentDao;
	}

	public void setComponentCommentDao(IComponentCommentDao componentCommentDao) {
		this.componentCommentDao = componentCommentDao;
	}

	public IIssueCommentDao getIssueCommentDao() {
		return issueCommentDao;
	}

	public void setIssueCommentDao(IIssueCommentDao issueCommentDao) {
		this.issueCommentDao = issueCommentDao;
	}

	public IModuleCommentDao getModuleCommentDao() {
		return moduleCommentDao;
	}

	public void setModuleCommentDao(IModuleCommentDao moduleCommentDao) {
		this.moduleCommentDao = moduleCommentDao;
	}

	public IProjectCommentDao getProjectCommentDao() {
		return projectCommentDao;
	}

	public void setProjectCommentDao(IProjectCommentDao projectCommentDao) {
		this.projectCommentDao = projectCommentDao;
	}

	public void addComment(ModuleComment comment, User user) {
		Date now = new Date();
		comment.setCreator( user );
		comment.setCreated( now );
		comment.setModifier( user );
		comment.setModified( now );
		getModuleCommentDao().create( comment );
	}

	public void addComment(ProjectComment comment, User user) {
		Date now = new Date();
		comment.setCreator( user );
		comment.setCreated( now );
		comment.setModifier( user );
		comment.setModified( now );
		getProjectCommentDao().create( comment );
	}

	public void addComment(ComponentComment comment, User user) {
		Date now = new Date();
		comment.setCreator( user );
		comment.setCreated( now );
		comment.setModifier( user );
		comment.setModified( now );
		getComponentCommentDao().create( comment );
	}

	public void addComment(IssueComment comment, User user) {
		Date now = new Date();
		comment.setCreator( user );
		comment.setCreated( now );
		comment.setModifier( user );
		comment.setModified( now );
		getIssueCommentDao().create( comment );
	}

	public void deleteComment(ModuleComment comment, User user) {
		getModuleCommentDao().delete( comment );
	}

	public void deleteComment(ProjectComment comment, User user) {
		getProjectCommentDao().delete( comment );
	}

	public void deleteComment(ComponentComment comment, User user) {
		getComponentCommentDao().delete( comment );
	}

	public void deleteComment(IssueComment comment, User user) {
		getIssueCommentDao().delete( comment );
	}

	public List<ComponentComment> getAllComponentComments() {
		return( getComponentCommentDao().findAll() );
	}

	public List<IssueComment> getAllIssueComments() {
		return( getIssueCommentDao().findAll() );
	}

	public List<ModuleComment> getAllModuleComments() {
		return( getModuleCommentDao().findAll() );
	}

	public List<ProjectComment> getAllProjectComments() {
		return( getProjectCommentDao().findAll() );
	}

	public ComponentComment getComponentComment(Long id) {
		return( getComponentCommentDao().find( id ) );
	}

	public List<ComponentComment> getComponentCommentsByCreator(User creator) {
		return( getComponentCommentDao().findByCreator( creator ) );
	}

	public List<ComponentComment> getComponentCommentsByDescription(
			String description) {
		return( getComponentCommentDao().findLikeDescription( description ) );
	}

	public List<ComponentComment> getComponentCommentsByModifier(User modifier) {
		return( getComponentCommentDao().findByModifier( modifier ) );
	}

	public List<ComponentComment> getComponentCommentsByParent(
			ComponentComment parent) {
		return( getComponentCommentDao().findByParent( parent ) );
	}

	public List<ComponentComment> getComponentCommentsBySummary(String summary) {
		return( getComponentCommentDao().findLikeSummary( summary ) );
	}

	public IssueComment getIssueComment(Long id) {
		return( getIssueCommentDao().find( id ) );
	}

	public List<IssueComment> getIssueCommentsByCreator(User creator) {
		return( getIssueCommentDao().findByCreator( creator ) );
	}

	public List<IssueComment> getIssueCommentsByDescription(String description) {
		return( getIssueCommentDao().findLikeDescription( description ) );
	}

	public List<IssueComment> getIssueCommentsByModifier(User modifier) {
		return( getIssueCommentDao().findByModifier( modifier ) );
	}

	public List<IssueComment> getIssueCommentsByParent(IssueComment parent) {
		return( getIssueCommentDao().findByParent( parent ) );
	}

	public List<IssueComment> getIssueCommentsBySummary(String summary) {
		return( getIssueCommentDao().findLikeSummary( summary ) );
	}

	public ModuleComment getModuleComment(Long id) {
		return( getModuleCommentDao().find( id ) );
	}

	public List<ModuleComment> getModuleCommentsByCreator(User creator) {
		return( getModuleCommentDao().findByCreator( creator ) );
	}

	public List<ModuleComment> getModuleCommentsByDescription(String description) {
		return( getModuleCommentDao().findLikeDescription( description ) );
	}

	public List<ModuleComment> getModuleCommentsByModifier(User modifier) {
		return( getModuleCommentDao().findByModifier( modifier ) );
	}

	public List<ModuleComment> getModuleCommentsByParent(ModuleComment parent) {
		return( getModuleCommentDao().findByParent( parent ) );
	}

	public List<ModuleComment> getModuleCommentsBySummary(String summary) {
		return( getModuleCommentDao().findLikeSummary( summary ) );
	}

	public ProjectComment getProjectComment(Long id) {
		return( getProjectCommentDao().find( id ) );
	}

	public List<ProjectComment> getProjectCommentsByCreator(User creator) {
		return( getProjectCommentDao().findByCreator( creator ) );
	}

	public List<ProjectComment> getProjectCommentsByDescription(
			String description) {
		return( getProjectCommentDao().findLikeDescription( description ) );
	}

	public List<ProjectComment> getProjectCommentsByModifier(User modifier) {
		return( getProjectCommentDao().findByModifier( modifier ) );
	}

	public List<ProjectComment> getProjectCommentsByParent(ProjectComment parent) {
		return( getProjectCommentDao().findByParent( parent ) );
	}

	public List<ProjectComment> getProjectCommentsBySummary(String summary) {
		return( getProjectCommentDao().findLikeSummary( summary ) );
	}

	public void updateComment(ModuleComment comment, User user) {
		getModuleCommentDao().delete( comment );
	}

	public void updateComment(ProjectComment comment, User user) {
		getProjectCommentDao().delete( comment );
	}

	public void updateComment(ComponentComment comment, User user) {
		getComponentCommentDao().delete( comment );
	}

	public void updateComment(IssueComment comment, User user) {
		getIssueCommentDao().delete( comment );
	}
	*/
}
