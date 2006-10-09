package org.syracus.rapid.components.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.syracus.rapid.common.GenericHibernateDao;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.realm.User;

public class HibernateModuleDao extends GenericHibernateDao<Module, Long> implements
		IModuleDao {

	

	
	@SuppressWarnings("unchecked")
	public List<Module> findByKey(String key) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Module.class );
		criteria.add( Restrictions.eqProperty( "key", key ) );
		return( findByCriteria( criteria ) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findLikeKey(String key) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Module.class );
		criteria.add( Restrictions.ilike( "key", key, MatchMode.ANYWHERE ) );
		return( findByCriteria( criteria ) );
	}

	

	@SuppressWarnings("unchecked")
	public List<Module> findByCreator(User creator) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.create = ?",
				creator
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByDescription(String description) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.description = ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByModifier(User modifier) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.modifier = ?",
				modifier
		) ); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> findByLeader(User leader) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.leader = ?",
				leader
		) ); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> findByName(String name) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.name = ?",
				name
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findLikeDescription(String description) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.description LIKE ?",
				description
		) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> findLikeName(String name) {
		return( (List<Module>)getHibernateTemplate().find(
				"FROM Module m WHERE m.create LIKE ?",
				name
		) );
	}

	

	public Integer countProjectsOfModule( final Module module ) {
		return( (Integer)getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return( (Integer)session.createQuery( "SELECT COUNT(*) FROM Project p WHERE p.module = ?" )
					.setEntity( 0, module )
					.uniqueResult()
				);
			}
		} ) );
	}
	
	public Integer countComponentsOfModule( final Module module ) {
		return( (Integer)getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return( (Integer)session.createQuery( "SELECT COUNT(*) FROM Component c WHERE c.module = ?" )
					.setEntity( 0, module )
					.uniqueResult()
				);
			}
		} ) );
	}

	public Integer countMatchingKeys( final String key ) {
		return( (Integer)getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return( (Integer)session.createQuery( "SELECT COUNT(*) FROM Module m WHERE m.key LIKE ?" )
					.setString( 0, (key + "%") )
					.uniqueResult()
				);
			}
		} ) );
	}
	
}
