package org.syracus.rapid.watches;

import java.util.List;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public interface IGenericWatchService {

	public void addWatch( Module module, User user );
	public void addWatch( Project project, User user );
	public void addWatch( Component component, User user );
	public void addWatch( GenericWatch watch );
	public void updateWatch( GenericWatch watch );
	public void deleteWatch( Module module, User user );
	public void deleteWatch( Project project, User user );
	public void deleteWatch( Component component, User user );
	public void deleteWatch( GenericWatch watch );
	
	public List<GenericWatch> getWatchesOfModule( Module module );
	public List<GenericWatch> getWatchesOfProject( Project project );
	public List<GenericWatch> getWatchesOfComponent( Component component );
	public List<GenericWatch> getWatchesOfUser( User user );
	
	public GenericWatch getWatchById( Long id );
	public GenericWatch getWatch( Module module, User user );
	public GenericWatch getWatch( Project project, User user );
	public GenericWatch getWatch( Component component, User user );
	
	
}
