package org.syracus.rapid.watches.dao;

import java.util.List;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.watches.GenericWatch;

public interface IGenericWatchDao {

	public void create( GenericWatch watch );
	public void update( GenericWatch watch );
	public void delete( GenericWatch watch );
	
	public GenericWatch findById( Long id );
	
	public List<GenericWatch> findByModule( Module module );
	public List<GenericWatch> findByProject( Project project );
	public List<GenericWatch> findByComponent( Component component );
	public List<GenericWatch> findByUser( User user );
	public GenericWatch findByModule( Module module, User user );
	public GenericWatch findByProject( Project project, User user );
	public GenericWatch findByComponent( Component component, User user );
	
	
}
