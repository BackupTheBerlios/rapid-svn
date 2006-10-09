package org.syracus.rapid.todos.dao;

import java.util.List;

import org.syracus.rapid.common.IGenericHibernateDao;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.todos.Todo;

public interface ITodoDao extends IGenericHibernateDao<Todo, Long> {

	public List<Todo> findByOwner( User owner );
	public List<Todo> findByOwnerAndDone( User owner, boolean done );
	public Todo findByOwnerAndId( User owner, Long id );
	
}
