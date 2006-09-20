package org.syracus.rapid.issues.dao;

import java.util.List;

import org.syracus.rapid.issues.IssuePriority;

public interface IIssuePriorityDao {

	public void create( IssuePriority priority );
	public void update( IssuePriority priority );
	public void delete( IssuePriority priority );
	
	public IssuePriority find( Long id );
	public List<IssuePriority> findAll();
	
}
