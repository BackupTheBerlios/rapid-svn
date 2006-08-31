package org.syracus.rapid.comments.dao;

import java.util.List;

import org.syracus.rapid.comments.ModuleComment;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.realm.User;

public interface IModuleCommentDao {

	public void create( ModuleComment comment );
	public void update( ModuleComment comment );
	public void delete( ModuleComment comment );
	
	public ModuleComment find( Long id );
	
	public List<ModuleComment> findAll();
	public List<ModuleComment> findByModule( Module module );
	public List<ModuleComment> findByParent( ModuleComment parent );
	public List<ModuleComment> findBySummary( String summary );
	public List<ModuleComment> findLikeSummary( String description );
	public List<ModuleComment> findByDescription( String description );
	public List<ModuleComment> findLikeDescription( String description );
	public List<ModuleComment> findByCreator( User creator );
	public List<ModuleComment> findByModifier( User modifier );
	
}
