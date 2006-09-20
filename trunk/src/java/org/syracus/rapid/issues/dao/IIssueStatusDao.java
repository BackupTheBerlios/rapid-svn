package org.syracus.rapid.issues.dao;

import java.util.List;

import org.syracus.rapid.issues.IssueStatus;

public interface IIssueStatusDao {

	public void create( IssueStatus status );
	public void update( IssueStatus status );
	public void delete( IssueStatus status );
	
	public IssueStatus find( Long id );
	public List<IssueStatus> findAll();
	
	public IssueStatus findByOrder( Integer order );
}
