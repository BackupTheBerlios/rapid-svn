package org.syracus.rapid.components.dao;

import java.util.List;

import org.syracus.rapid.common.IPersistanceDao;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.realm.User;

public interface IModuleDao extends IPersistanceDao {

	public void create( Module module );
	public void update( Module module );
	public void delete( Module module );
	
	public Module find( Long id );
	
	public List<Module> findAll();
	public List<Module> findByName( String name );
	public List<Module> findLikeName( String name );
	public List<Module> findByDescription( String description );
	public List<Module> findLikeDescription( String description );
	public List<Module> findByLeader( User leader );
	public List<Module> findByCreator( User creator );
	public List<Module> findByModifier( User modifier );
	
	public Integer countProjects( Module module );
}
