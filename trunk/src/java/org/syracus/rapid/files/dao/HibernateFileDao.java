package org.syracus.rapid.files.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.syracus.rapid.files.AbstractFile;

public class HibernateFileDao extends HibernateDaoSupport implements IFileDao {

	public void create(AbstractFile file) {
		getHibernateTemplate().save( file );
	}

	public void delete(AbstractFile file) {
		getHibernateTemplate().delete( file );
	}

	public AbstractFile find(Long id) {
		return( (AbstractFile)getHibernateTemplate().get( AbstractFile.class, id ) );
	}

	public void update(AbstractFile file) {
		getHibernateTemplate().update( file );
	}

}
