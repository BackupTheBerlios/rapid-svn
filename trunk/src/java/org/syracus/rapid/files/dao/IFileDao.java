package org.syracus.rapid.files.dao;

import org.syracus.rapid.files.AbstractFile;

public interface IFileDao {

	public void create( AbstractFile file );
	public void update( AbstractFile file );
	public void delete( AbstractFile file );
	
	public AbstractFile find( Long id );
	
}
