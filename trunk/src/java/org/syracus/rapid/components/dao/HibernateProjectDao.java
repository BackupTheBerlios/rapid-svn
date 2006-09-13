package org.syracus.rapid.components.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public class HibernateProjectDao extends AbstractHibernateDao implements
		IProjectDao {

	public void create(Project project) {
		getHibernateTemplate().save( project );
	}

	public void delete(Project project) {
		getHibernateTemplate().delete( project );
	}

	public Project find(Long id) {
		return( (Project)getHibernateTemplate().get( Project.class, id ) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findAll() {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project"
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByCreator(User creator) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.create = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByDescription(String description) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByModifier(User modifier) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.modifier = ?",
				modifier
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByModule(Module module) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Project.class );
		if ( null == module ) {
			criteria.add( Restrictions.isNull( "module" ) );
		} else {
			criteria.add( Restrictions.eq( "module", module ) );
		}
		return( findByCriteria( criteria ) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findByName(String name) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findLikeDescription(String description) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Project> findLikeName(String name) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.name LIKE ?",
				name
		) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByLeader(User leader) {
		return( (List<Project>)getHibernateTemplate().find(
				"FROM Project p WHERE p.leader = ?",
				leader
		) ); 
	}

	public void update(Project project) {
		getHibernateTemplate().update( project );
	}

	public Integer countComponentsOfProject( final Project project ) {
		return( (Integer)getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return( (Integer)session.createQuery( "SELECT COUNT(*) FROM Component c WHERE c.project = ?" )
					.setEntity( 0, project )
					.uniqueResult()
				);
			}
		} ) );
	}
}
