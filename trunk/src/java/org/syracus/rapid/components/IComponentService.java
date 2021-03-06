package org.syracus.rapid.components;

import java.util.List;

import org.syracus.rapid.realm.User;

public interface IComponentService {

	public void addModule( Module module, User user );
	public void updateModule( Module module, User user );
	public void deleteModule( Module module, User user );
	
	public Module getModuleById( Long id );
	public List<Module> getAllModules();
	public List<Module> getModulesByKey( String key );
	public List<Module> getModulesByName( String name );
	public List<Module> getModulesByDescription( String description );
	public List<Module> getModulesByCreator( User creator );
	public List<Module> getModulesByModifier( User modifier );
	public List<Module> getModulesByLeader( User leader );
	public List<Module> getNewestModulesByLeader( User leader, int max );
	public Integer getNumberOfProjects( Module module );
	public Integer getNumberOfComponents( Module module );
	public Integer getNumberOfModuleKeys( String key );
	
	public void addProject( Project project, User user );
	public void updateProject( Project project, User user );
	public void deleteProject( Project project, User user );
	
	public Project getProjectById( Long id );
	public List<Project> getAllProjects();
	public List<Project> getProjectsByKey( String key );
	public List<Project> getTopLevelProjects();
	public List<Project> getProjectsOfModule( Module module );
	public List<Project> getProjectsByName( String name );
	public List<Project> getProjectsByDescription( String description );
	public List<Project> getProjectsByCreator( User creator );
	public List<Project> getProjectsByModifier( User modifier );
	public List<Project> getProjectsByLeader( User leader );
	public List<Project> getNewestProjectsByLeader( User leader, int max );
	public Integer getNumberOfComponents( Project project );
	
	public void addComponent( Component component, User user );
	public void updateComponent( Component project, User user );
	public void deleteComponent( Component project, User user );
	
	public Component getComponentById( Long id );
	public List<Component> getComponentsByKey( String key );
	public List<Component> getAllComponents();
	public List<Component> getAllComponentsOfModule( Module module );
	public List<Component> getTopLevelComponents();
	public List<Component> getComponentsOfModule( Module module );
	public List<Component> getComponentsOfProject( Project project );
	public List<Component> getComponentsOfModuleAndProject( Module module, Project project );
	public List<Component> getComponentsByName( String name );
	public List<Component> getComponentsByDescription( String description );
	public List<Component> getComponentsByCreator( User creator );
	public List<Component> getComponentsByModifier( User modifier );
	public List<Component> getComponentsByLeader( User leader );
	public List<Component> getNewestComponentsByLeader( User leader, int max );
	
}
