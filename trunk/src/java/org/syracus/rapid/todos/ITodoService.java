package org.syracus.rapid.todos;

import java.util.List;

import org.syracus.rapid.realm.User;

public interface ITodoService {

	public void addTodo( Todo todo, User user );
	public void updateTodo( Todo todo, User user );
	public void deleteTodo( Todo todo, User user );
	
	public Todo getTodoById( Long id );
	public List<Todo> getAllTodos();
	public List<Todo> getOwnTodos( User owner );
	public Todo getOwnTodo( User owner, Long id );
	
}
