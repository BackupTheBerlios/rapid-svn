package org.syracus.rapid.comments;

import java.util.List;

import org.syracus.rapid.realm.User;

public interface ICommentService {

	public void addComment( ModuleComment comment, User user );
	public void addComment( ProjectComment comment, User user );
	public void addComment( ComponentComment comment, User user );
	public void addComment( IssueComment comment, User user );
	
	public void updateComment( ModuleComment comment, User user );
	public void updateComment( ProjectComment comment, User user );
	public void updateComment( ComponentComment comment, User user );
	public void updateComment( IssueComment comment, User user );
	
	public void deleteComment( ModuleComment comment, User user );
	public void deleteComment( ProjectComment comment, User user );
	public void deleteComment( ComponentComment comment, User user );
	public void deleteComment( IssueComment comment, User user );
	
	public ModuleComment getModuleComment( Long id );
	public ProjectComment getProjectComment( Long id );
	public ComponentComment getComponentComment( Long id );
	public IssueComment getIssueComment( Long id );
	
	public List<ModuleComment> getAllModuleComments();
	public List<ProjectComment> getAllProjectComments();
	public List<ComponentComment> getAllComponentComments();
	public List<IssueComment> getAllIssueComments();
	
	public List<ModuleComment> getModuleCommentsByParent( ModuleComment parent );
	public List<ProjectComment> getProjectCommentsByParent( ProjectComment parent );
	public List<ComponentComment> getComponentCommentsByParent( ComponentComment parent );
	public List<IssueComment> getIssueCommentsByParent( IssueComment parent );
	
	public List<ModuleComment> getModuleCommentsBySummary( String summary );
	public List<ProjectComment> getProjectCommentsBySummary( String summary );
	public List<ComponentComment> getComponentCommentsBySummary( String summary );
	public List<IssueComment> getIssueCommentsBySummary( String summary );
	
	public List<ModuleComment> getModuleCommentsByDescription( String description );
	public List<ProjectComment> getProjectCommentsByDescription( String description );
	public List<ComponentComment> getComponentCommentsByDescription( String description );
	public List<IssueComment> getIssueCommentsByDescription( String description );
	
	public List<ModuleComment> getModuleCommentsByCreator( User creator );
	public List<ProjectComment> getProjectCommentsByCreator( User creator );
	public List<ComponentComment> getComponentCommentsByCreator( User creator );
	public List<IssueComment> getIssueCommentsByCreator( User creator );
	
	public List<ModuleComment> getModuleCommentsByModifier( User creator );
	public List<ProjectComment> getProjectCommentsByModifier( User creator );
	public List<ComponentComment> getComponentCommentsByModifier( User creator );
	public List<IssueComment> getIssueCommentsByModifier( User creator );
	
}
