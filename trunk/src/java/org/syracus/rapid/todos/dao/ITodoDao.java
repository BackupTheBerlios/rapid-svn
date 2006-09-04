package org.syracus.rapid.todos.dao;

import java.util.List;

import org.syracus.rapid.common.IPersistanceDao;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.todos.Todo;

public interface ITodoDao extends IPersistanceDao {

	public void create( Todo todo );
	public void update( Todo todo );
	public void delete( Todo todo );
	
	public Todo find( Long id );
	public List<Todo> findAll();
	public List<Todo> findByOwner( User owner );
	public List<Todo> findByOwnerAndDone( User owner, boolean done );
	public Todo findByOwnerAndId( User owner, Long id );
	
}
