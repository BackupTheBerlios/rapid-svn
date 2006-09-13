package org.syracus.rapid.components;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.syracus.rapid.components.dao.IComponentDao;
import org.syracus.rapid.components.dao.IModuleDao;
import org.syracus.rapid.components.dao.IProjectDao;
import org.syracus.rapid.realm.User;

public class ComponentServiceImpl implements IComponentService {

	private IModuleDao moduleDao;
	private IProjectDao projectDao;
	private IComponentDao componentDao;
	
	public IComponentDao getComponentDao() {
		return componentDao;
	}

	public void setComponentDao(IComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	public IModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(IModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public IProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void addComponent(Component component, User user) {
		Date now = new Date();
		component.setCreator( user );
		component.setCreated( now );
		component.setModifier( user );
		component.setModified( now );
		getComponentDao().create( component );
	}

	public void addModule(Module module, User user) {
		Date now = new Date();
		module.setCreator( user );
		module.setCreated( now );
		module.setModifier( user );
		module.setModified( now );
		getModuleDao().create( module );
	}

	public void addProject(Project project, User user) {
		Date now = new Date();
		project.setCreator( user );
		project.setCreated( now );
		project.setModifier( user );
		project.setModified( now );
		getProjectDao().create( project );
	}

	public void deleteComponent(Component component, User user) {
		getComponentDao().delete( component );
	}

	public void deleteModule(Module module, User user) {
		getModuleDao().delete( module );
	}

	public void deleteProject(Project project, User user) {
		getProjectDao().delete( project );
	}

	public List<Component> getAllComponents() {
		return( getComponentDao().findAll() );
	}

	public List<Module> getAllModules() {
		return( getModuleDao().findAll() );
	}

	public List<Project> getAllProjects() {
		return( getProjectDao().findAll() );
	}

	public Component getComponentById(Long id) {
		return( getComponentDao().find( id ) );
	}

	public List<Component> getComponentsByCreator(User creator) {
		return( getComponentDao().findByCreator( creator ) );
	}

	public List<Component> getComponentsByDescription(String description) {
		return( getComponentDao().findLikeDescription( description ) );
	}

	public List<Component> getComponentsByModifier(User modifier) {
		return( getComponentDao().findByModifier( modifier ) );
	}

	public List<Component> getComponentsByName(String name) {
		return( getComponentDao().findLikeName( name ) );
	}

	public List<Component> getAllComponentsOfModule(Module module) {
		return( getComponentDao().findByModule( module, true ) );
	}
	
	public List<Component> getTopLevelComponents() {
		return( getComponentDao().findByModuleAndProject(null, null) );
	}
	
	public List<Component> getComponentsOfModule(Module module) {
		return( getComponentDao().findByModule( module, false ) );
	}

	public List<Component> getComponentsOfModuleAndProject(Module module,
			Project project) {
		return( getComponentDao().findByModuleAndProject( module, project ) );
	}

	public List<Component> getComponentsOfProject(Project project) {
		return( getComponentDao().findByProject( project ) );
	}

	public Module getModuleById(Long id) {
		return( getModuleDao().find( id ) );
	}

	public List<Module> getModulesByCreator(User creator) {
		return( getModuleDao().findByCreator( creator ) );
	}

	public List<Module> getModulesByDescription(String description) {
		return( getModuleDao().findLikeDescription( description ) );
	}

	public List<Module> getModulesByModifier(User modifier) {
		return( getModuleDao().findByModifier( modifier ) );
	}

	public List<Module> getModulesByName(String name) {
		return( getModuleDao().findLikeName( name ) );
	}

	public Project getProjectById(Long id) {
		return( getProjectDao().find( id ) );
	}

	public List<Project> getProjectsByCreator(User creator) {
		return( getProjectDao().findByCreator( creator ) );
	}

	public List<Project> getProjectsByDescription(String description) {
		return( getProjectDao().findLikeDescription( description ) );
	}

	public List<Project> getProjectsByModifier(User modifier) {
		return( getProjectDao().findByModifier( modifier ) );
	}

	public List<Project> getProjectsByName(String name) {
		return( getProjectDao().findLikeName( name ) );
	}

	
	public List<Project> getTopLevelProjects() {
		return( getProjectDao().findByModule( null ) );
	}

	public List<Project> getProjectsOfModule(Module module) {
		return( getProjectDao().findByModule( module ) );
	}

	public void updateComponent(Component component, User user) {
		Date now = new Date();
		component.setModifier( user );
		component.setModified( now );
		getComponentDao().update( component );
	}

	public void updateModule(Module module, User user) {
		Date now = new Date();
		module.setModifier( user );
		module.setModified( now );
		getModuleDao().update( module );
	}

	public void updateProject(Project project, User user) {
		Date now = new Date();
		project.setModifier( user );
		project.setModified( now );
		getProjectDao().update( project );
	}
	
	public List<Module> getModulesByLeader( User leader ) {
		return( getModuleDao().findByLeader( leader ) );
	}
	
	public List<Project> getProjectsByLeader( User leader ) {
		return( getProjectDao().findByLeader( leader ) );
	}
	
	public List<Component> getComponentsByLeader( User leader ) {
		return( getComponentDao().findByLeader( leader ) );
	}

	@SuppressWarnings("unchecked")
	public List<Module> getNewestModulesByLeader(User leader, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Module.class )
			.add( Restrictions.eq( "leader", leader ) )
			.addOrder( Order.desc( "modified" ) );
		
		return( (List<Module>)getModuleDao().findByCriteria(criteria, 0, max ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> getNewestProjectsByLeader(User leader, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Project.class )
			.add( Restrictions.eq( "leader", leader ) )
			.addOrder( Order.desc( "modified" ) );
		
		return( (List<Project>)getProjectDao().findByCriteria(criteria, 0, max ) );
	}

	@SuppressWarnings("unchecked")
	public List<Component> getNewestComponentsByLeader(User leader, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass( Component.class )
			.add( Restrictions.eq( "leader", leader ) )
			.addOrder( Order.desc( "modified" ) );
		
		return( (List<Component>)getComponentDao().findByCriteria(criteria, 0, max ) );
	}

	public Integer getNumberOfProjects(Module module) {
		return( getModuleDao().countProjectsOfModule( module ) );
	}
	
	public Integer getNumberOfComponents(Module module) {
		return( getModuleDao().countComponentsOfModule( module ) );
	}
	
	public Integer getNumberOfComponents(Project project) {
		return( getProjectDao().countComponentsOfProject( project ) );
	}
	
	
	
}
