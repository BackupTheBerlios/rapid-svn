package org.syracus.rapid.actions.todos;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;

import org.syracus.rapid.stripes.RapidActionBean;
import org.syracus.rapid.todos.ITodoService;
import org.syracus.rapid.todos.Todo;

@UrlBinding("/protected/todo.action")
public class TodoActionBean extends RapidActionBean {

	private ITodoService todoService;
	
	private Long todoId;
	private Todo todo;
	
	
	public ITodoService getTodoService() {
		return todoService;
	}

	@SpringBean("todoService")
	public void setTodoService(ITodoService todoService) {
		this.todoService = todoService;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	public Resolution view() {
		Todo todo = getTodoService().getOwnTodo( getContext().getAuthUser(), getTodoId() );
		if ( null != todo ) {
			setTodo( todo );
		} else {
			getContext().getValidationErrors().addGlobalError(
					new SimpleError( "No such Todo found." )
			);
		}
		return( new ForwardResolution( "/protected/todos/todoView.jsp" ) );
	}
	
	public Resolution edit() {
		Todo todo = getTodoService().getOwnTodo( getContext().getAuthUser(), getTodoId() );
		if ( null != todo ) {
			setTodo( todo );
		} else {
			getContext().getValidationErrors().addGlobalError(
					new SimpleError( "No such Todo found." )
			);
		}
		return( new ForwardResolution( "/protected/todos/todoEdit.jsp" ) );
	}
	
	public Resolution save() {
		if ( null == getTodo().getId() ) {
			getTodoService().addTodo( getTodo(), getContext().getAuthUser() );
		} else {
			Todo todo = getTodoService().getOwnTodo( getContext().getAuthUser(), getTodo().getId() );
			if ( null != todo ) {
				todo.setSummary( getTodo().getSummary() );
				todo.setDescription( getTodo().getDescription() );
				getTodoService().updateTodo( todo, getContext().getAuthUser() );
			} else {
				getContext().getValidationErrors().addGlobalError(
						new SimpleError( "No such Todo found." )
				);
			}
		}
		return( new RedirectResolution( "/protected/todo.action" )
			.addParameter("view", "" )
			.addParameter( "todoId", getTodo().getId() )
		);
	}
	
	public Resolution delete() {
		Todo todo = getTodoService().getOwnTodo( getContext().getAuthUser(), getTodoId() );
		if ( null != todo ) {
			getTodoService().deleteTodo( todo, getContext().getAuthUser() );
		} else {
			getContext().getValidationErrors().addGlobalError(
					new SimpleError( "No such Todo found." )
			);
		}
		return( getContext().getSourcePageResolution() );
	}
	
	public Resolution close() {
		Todo todo = getTodoService().getOwnTodo( getContext().getAuthUser(), getTodoId() );
		if ( null != todo ) {
			todo.setDone( true );
			getTodoService().updateTodo( todo, getContext().getAuthUser() );
		} else {
			getContext().getValidationErrors().addGlobalError(
					new SimpleError( "No such Todo found." )
			);
		}
		return( getContext().getSourcePageResolution() );
	}
	
	public Resolution open() {
		Todo todo = getTodoService().getOwnTodo( getContext().getAuthUser(), getTodoId() );
		if ( null != todo ) {
			todo.setDone( false );
			getTodoService().updateTodo( todo, getContext().getAuthUser() );
		} else {
			getContext().getValidationErrors().addGlobalError(
					new SimpleError( "No such Todo found." )
			);
		}
		return( getContext().getSourcePageResolution() );
	}
	
	public List<Todo> getOwnTodos() {
		return( getTodoService().getOwnTodos( getContext().getAuthUser() ) );
	}
}
