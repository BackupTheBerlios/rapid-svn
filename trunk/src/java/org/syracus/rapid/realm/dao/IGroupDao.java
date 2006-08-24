package org.syracus.rapid.realm.dao;

import java.util.List;

import org.syracus.rapid.realm.Group;

public interface IGroupDao {

	public Group find( Long id );
	
	public void create( Group group );
	public void update( Group group );
	public void delete( Group group );
	
	public List<Group> findAll();
	public List<Group> findByName( String name );
	public List<Group> findLikeName( String name );
	
}
