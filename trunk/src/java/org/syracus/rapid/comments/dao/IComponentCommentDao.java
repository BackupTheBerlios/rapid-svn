package org.syracus.rapid.comments.dao;

import java.util.List;

import org.syracus.rapid.comments.ComponentComment;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.realm.User;

public interface IComponentCommentDao {

	public void create( ComponentComment comment );
	public void update( ComponentComment comment );
	public void delete( ComponentComment comment );
	
	public ComponentComment find( Long id );
	
	public List<ComponentComment> findAll();
	public List<ComponentComment> findByComponent( Component component );
	public List<ComponentComment> findByParent( ComponentComment parent );
	public List<ComponentComment> findBySummary( String summary );
	public List<ComponentComment> findLikeSummary( String description );
	public List<ComponentComment> findByDescription( String description );
	public List<ComponentComment> findLikeDescription( String description );
	public List<ComponentComment> findByCreator( User creator );
	public List<ComponentComment> findByModifier( User modifier );
	
}
