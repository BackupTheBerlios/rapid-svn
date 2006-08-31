package org.syracus.rapid.comments.dao;

import java.util.List;

import org.syracus.rapid.comments.ProjectComment;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public interface IProjectCommentDao {

	public void create( ProjectComment comment );
	public void update( ProjectComment comment );
	public void delete( ProjectComment comment );
	
	public ProjectComment find( Long id );
	
	public List<ProjectComment> findAll();
	public List<ProjectComment> findByProject( Project project );
	public List<ProjectComment> findByParent( ProjectComment parent );
	public List<ProjectComment> findBySummary( String summary );
	public List<ProjectComment> findLikeSummary( String description );
	public List<ProjectComment> findByDescription( String description );
	public List<ProjectComment> findLikeDescription( String description );
	public List<ProjectComment> findByCreator( User creator );
	public List<ProjectComment> findByModifier( User modifier );
	
}
