package org.syracus.rapid.watches;

import java.util.List;

import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.watches.dao.IGenericWatchDao;

public class GenericWatchService implements IGenericWatchService {

	private IGenericWatchDao watchDao;
	
	
	public IGenericWatchDao getWatchDao() {
		return watchDao;
	}

	public void setWatchDao(IGenericWatchDao watchDao) {
		this.watchDao = watchDao;
	}

	public void addWatch(Module module, User user) {
		GenericWatch watch = new GenericWatch();
		watch.setModule( module );
		watch.setUser( user );
		addWatch( watch );
	}

	public void addWatch(Project project, User user) {
		GenericWatch watch = new GenericWatch();
		watch.setProject( project );
		watch.setUser( user );
		addWatch( watch );
	}

	public void addWatch(Component component, User user) {
		GenericWatch watch = new GenericWatch();
		watch.setComponent( component );
		watch.setUser( user );
		addWatch( watch );
	}

	public void addWatch(GenericWatch watch) {
		getWatchDao().create( watch );
	}

	public void deleteWatch(Component component, User user) {
		GenericWatch watch = getWatch( component, user );
		if ( null != watch ) {
			getWatchDao().delete( watch );
		}
	}

	public void deleteWatch(Module module, User user) {
		GenericWatch watch = getWatch( module, user );
		if ( null != watch ) {
			getWatchDao().delete( watch );
		}
	}

	public void deleteWatch(Project project, User user) {
		GenericWatch watch = getWatch( project, user );
		if ( null != watch ) {
			getWatchDao().delete( watch );
		}
	}

	public void deleteWatch(GenericWatch watch) {
		getWatchDao().delete( watch );
	}

	public GenericWatch getWatch(Module module, User user) {
		return( getWatchDao().findByModule( module, user ) );
	}

	public GenericWatch getWatch(Project project, User user) {
		return( getWatchDao().findByProject( project, user ) );
	}

	public GenericWatch getWatch(Component component, User user) {
		return( getWatchDao().findByComponent( component, user ) );
	}

	public GenericWatch getWatchById(Long id) {
		return( getWatchDao().findById( id ) );
	}

	public List<GenericWatch> getWatchesOfComponent(Component component) {
		return( getWatchDao().findByComponent( component ) );
	}

	public List<GenericWatch> getWatchesOfModule(Module module) {
		return( getWatchDao().findByModule( module ) );
	}

	public List<GenericWatch> getWatchesOfProject(Project project) {
		return( getWatchDao().findByProject( project ) );
	}

	public List<GenericWatch> getWatchesOfUser(User user) {
		return( getWatchDao().findByUser( user ) );
	}

	public void updateWatch(GenericWatch watch) {
		getWatchDao().update( watch );
	}

}
