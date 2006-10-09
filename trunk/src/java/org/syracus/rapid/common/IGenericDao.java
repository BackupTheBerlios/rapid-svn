package org.syracus.rapid.common;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, ID extends Serializable> {

	public T find( ID id );
	public List<T> findAll();
	
	public void create( T entity );
	public void update( T entity );
	public void delete( T entity );
	
}
