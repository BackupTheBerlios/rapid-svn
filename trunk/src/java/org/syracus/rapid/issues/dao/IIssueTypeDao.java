package org.syracus.rapid.issues.dao;

import java.util.List;

import org.syracus.rapid.issues.IssueType;

public interface IIssueTypeDao {

	public void create( IssueType type );
	public void update( IssueType type );
	public void delete( IssueType type );
	
	public IssueType find( Long id );
	public List<IssueType> findAll();
	
}
