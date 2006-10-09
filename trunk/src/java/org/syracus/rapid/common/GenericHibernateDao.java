package org.syracus.rapid.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericHibernateDao<T, ID extends Serializable>
		extends HibernateDaoSupport
		implements IGenericHibernateDao<T, ID>
{

	private Class<T> entityClass;
	
	protected Class<T> getEntityClass() {
		return( this.entityClass );
	}
	
	public GenericHibernateDao() {
		this.entityClass = (Class<T>)((ParameterizedType)getClass()
								.getGenericSuperclass())
								.getActualTypeArguments()[0];
	}
	
	public void create( T entity ) {
		getHibernateTemplate().save( entity );
	}
	
	public void update( T entity ) {
		getHibernateTemplate().update( entity );
	}
	
	public void delete( T entity ) {
		getHibernateTemplate().delete( entity );
	}
	
	@SuppressWarnings("unchecked")
	public T find( ID id ) {
		return( (T)getHibernateTemplate().load( getEntityClass(), id ) );
	}
	
	@SuppressWarnings("unchecked")
	public T find( ID id, LockMode lock ) {
		return( (T)getHibernateTemplate().load( getEntityClass(), id, lock ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return( (List<T>)getHibernateTemplate().loadAll( getEntityClass() ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample( T instance ) {
		DetachedCriteria criteria = createCriteria();
		Example example = Example.create( instance );
		criteria.add( example );
		return( (List<T>)getHibernateTemplate().findByCriteria( criteria ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample( T instance, String[] excludeProperties ) {
		DetachedCriteria criteria = createCriteria();
		Example example = Example.create( instance );
		for ( String property : excludeProperties ) {
			example.excludeProperty( property );
		}
		criteria.add( example );
		return( (List<T>)getHibernateTemplate().findByCriteria( criteria ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria( DetachedCriteria criteria ) {
		return( (List<T>)getHibernateTemplate().findByCriteria( criteria ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria( DetachedCriteria criteria, int first, int max ) {
		return( (List<T>)getHibernateTemplate().findByCriteria( criteria, first, max ) );
	}
	
	public DetachedCriteria createCriteria() {
		return( DetachedCriteria.forClass( getEntityClass() ) );
	}

}
