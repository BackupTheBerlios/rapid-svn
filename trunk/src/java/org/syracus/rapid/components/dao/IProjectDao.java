package org.syracus.rapid.components.dao;

import java.util.List;

import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public interface IProjectDao {

	public void create( Project project );
	public void update( Project project );
	public void delete( Project project );
	
	public Project find( Long id );
	
	public List<Project> findAll();
	public List<Project> findByModule( Module module );
	public List<Project> findByParent( Project parent );
	public List<Project> findByName( String name );
	public List<Project> findLikeName( String name );
	public List<Project> findByDescription( String description );
	public List<Project> findLikeDescription( String description );
	public List<Project> findByCreator( User creator );
	public List<Project> findByModifier( User modifier );
	public List<Project> findByLeader( User leader );
}
