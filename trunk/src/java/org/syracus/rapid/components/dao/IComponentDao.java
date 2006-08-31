package org.syracus.rapid.components.dao;

import java.util.List;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public interface IComponentDao {

	public void create( Component project );
	public void update( Component project );
	public void delete( Component project );
	
	public Component find( Long id );
	
	public List<Component> findAll();
	public List<Component> findByModule( Module module );
	public List<Component> findByProject( Project project );
	public List<Component> findByModuleAndProject( Module module, Project project );
	public List<Component> findByName( String name );
	public List<Component> findLikeName( String name );
	public List<Component> findByDescription( String description );
	public List<Component> findLikeDescription( String description );
	public List<Component> findByCreator( User creator );
	public List<Component> findByModifier( User modifier );
	public List<Component> findByLeader( User leader );
}
