package org.syracus.rapid.todos.dao;

import java.util.List;

import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.todos.Todo;

public class HibernateTodoDao extends GenericHibernateDao<Todo, Long> implements ITodoDao {

	
	@SuppressWarnings("unchecked")
	public List<Todo> findByOwner(User owner) {
		return( (List<Todo>)getHibernateTemplate().find(
				"FROM Todo t WHERE t.owner = ? ORDER BY t.modified DESC",
				owner
		) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Todo> findByOwnerAndDone(User owner, boolean done) {
		return( (List<Todo>)getHibernateTemplate().find(
				"FROM Todo t WHERE t.owner = ? AND done = ? ORDER BY t.modified DESC",
				new Object[]{
						owner, done
				}
		) );
	}

	
	@SuppressWarnings("unchecked")
	public Todo findByOwnerAndId(User owner, Long id) {
		List<Todo> todos = getHibernateTemplate().find(
				"FROM Todo t WHERE t.id = ? AND t.owner = ?",
				new Object[]{
						id, owner
				}
		);
		return( (false == todos.isEmpty()) ? todos.get( 0 ) : null );
	}

}
