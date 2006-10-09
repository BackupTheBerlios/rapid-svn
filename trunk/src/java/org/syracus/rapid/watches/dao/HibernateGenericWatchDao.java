package org.syracus.rapid.watches.dao;

import java.util.List;

import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.watches.GenericWatch;

public class HibernateGenericWatchDao extends AbstractHibernateDao implements
		IGenericWatchDao {

	public void create(GenericWatch watch) {
		getHibernateTemplate().save( watch );
	}

	public void delete(GenericWatch watch) {
		getHibernateTemplate().delete( watch );
	}

	@SuppressWarnings("unchecked")
	public List<GenericWatch> findByComponent(Component component) {
		return( (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.component = ?",
				component
		) );
	}

	@SuppressWarnings("unchecked")
	public GenericWatch findByComponent(Component component, User user) {
		List<GenericWatch> watches = (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.component = ? AND w.user = ?",
				new Object[]{
						component,
						user
				}
		);
		return( (watches.isEmpty()) ? null : watches.get( 0 ) );
	}

	public GenericWatch findById(Long id) {
		return( (GenericWatch)getHibernateTemplate().get( GenericWatch.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<GenericWatch> findByModule(Module module) {
		return( (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.module = ?",
				module
		) );
	}

	@SuppressWarnings("unchecked")
	public GenericWatch findByModule(Module module, User user) {
		List<GenericWatch> watches = (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.module = ? AND w.user = ?",
				new Object[]{
						module,
						user
				}
		);
		return( (watches.isEmpty()) ? null : watches.get( 0 ) );
	}

	@SuppressWarnings("unchecked")
	public List<GenericWatch> findByProject(Project project) {
		return( (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.project = ?",
				project
		) );
	}

	@SuppressWarnings("unchecked")
	public GenericWatch findByProject(Project project, User user) {
		List<GenericWatch> watches = (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.project = ? AND w.user = ?",
				new Object[]{
						project,
						user
				}
		);
		return( (watches.isEmpty()) ? null : watches.get( 0 ) );
	}

	@SuppressWarnings("unchecked")
	public List<GenericWatch> findByUser(User user) {
		return( (List<GenericWatch>)getHibernateTemplate().find(
				"FROM GenericWatch w WHERE w.user = ?",
				user
		) );
	}

	public void update(GenericWatch watch) {
		getHibernateTemplate().update( watch );
	}

}
