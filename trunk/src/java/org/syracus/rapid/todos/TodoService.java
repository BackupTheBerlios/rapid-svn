package org.syracus.rapid.todos;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.todos.dao.ITodoDao;

public class TodoService implements ITodoService {

	private ITodoDao todoDao;
	
	
	public ITodoDao getTodoDao() {
		return todoDao;
	}

	public void setTodoDao(ITodoDao todoDao) {
		this.todoDao = todoDao;
	}

	public void addTodo(Todo todo, User user) {
		Date now = new Date();
		todo.setOwner( user );
		todo.setCreated( now );
		todo.setModified( now );
		getTodoDao().create( todo );
	}

	public void deleteTodo(Todo todo, User user) {
		getTodoDao().delete( todo );
	}

	public List<Todo> getAllTodos() {
		return( getTodoDao().findAll() );
	}

	public Todo getTodoById(Long id) {
		return( getTodoDao().find( id ) );
	}

	public List<Todo> getTodosByOwner(User owner) {
		return( getTodoDao().findByOwner( owner ) );
	}

	@SuppressWarnings("unchecked")
	public List<Todo> getNewestTodosByOwner(User owner, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Todo.class )
			.add( Restrictions.eq( "owner", owner ) )
			.addOrder( Order.desc( "modified" ) );
		return( (List<Todo>)getTodoDao().findByCriteria( criteria, 0, max ) );
	}

	public Todo getOwnTodo(User owner, Long id) {
		return( getTodoDao().findByOwnerAndId( owner, id ) );
	}

	public void updateTodo(Todo todo, User user) {
		Date now = new Date();
		todo.setModified( now );
		getTodoDao().update( todo );
	}

}
