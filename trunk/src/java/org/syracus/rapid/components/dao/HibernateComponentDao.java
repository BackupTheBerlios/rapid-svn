package org.syracus.rapid.components.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.realm.User;

public class HibernateComponentDao extends GenericHibernateDao<Component, Long> implements
		IComponentDao {

	protected static final transient Log log = LogFactory.getLog( HibernateComponentDao.class );
	

	@SuppressWarnings("unchecked")
	public List<Component> findByKey(String key) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Component.class );
		criteria.add( Restrictions.eqProperty( "key", key ) );
		return( findByCriteria( criteria ) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findLikeKey(String key) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Component.class );
		criteria.add( Restrictions.ilike( "key", key, MatchMode.ANYWHERE ) );
		return( findByCriteria( criteria ) );
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Component> findByCreator(User creator) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.creator = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByDescription(String description) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByModifier(User modifier) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.modifier = ?",
				modifier
		) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Component> findByModule(Module module) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Component.class );
		if ( null != module ) {
			criteria.add( Restrictions.eq( "module", module ) );
		} else {
			criteria.add( Restrictions.isNull( "module" ) );
		}
		return( findByCriteria( criteria ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Component> findByModule(Module module, boolean recursive) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Component.class );
		if ( true == recursive ) {
			criteria.add( Restrictions.eq( "module", module ) );
		} else {
			criteria.add( Restrictions.and(
					Restrictions.eq( "module", module ),
					Restrictions.isNull( "project" )
				)
			);
		}
		return( findByCriteria( criteria ) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByModuleAndProject(Module module, Project project) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Component.class );
		if ( null == module ) {
			if ( null == project ) {
				criteria.add( Restrictions.and(
						Restrictions.isNull( "module" ),
						Restrictions.isNull( "project" )
				) );
			} else {
				criteria.add( Restrictions.and(
						Restrictions.isNull( "module" ),
						Restrictions.eq( "project", project )
				) );
			}
		} else if ( null == project ) {
			criteria.add( Restrictions.and(
					Restrictions.eq( "module", module ),
					Restrictions.isNull( "project" )
			) );
		} else {
			criteria.add( Restrictions.and(
					Restrictions.eq( "module", module ),
					Restrictions.eq( "project", project )
			) );
		}
		return( findByCriteria( criteria ) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByName(String name) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findByProject(Project project) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.project = ?",
				project
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findLikeDescription(String description) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> findLikeName(String name) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.name LIKE ?",
				name
		) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Component> findByLeader(User leader) {
		return( (List<Component>)getHibernateTemplate().find(
				"FROM Component c WHERE c.leader = ?",
				leader
		) ); 
	}

	

}
