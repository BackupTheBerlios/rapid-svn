package org.syracus.rapid.history;

import org.syracus.rapid.history.dao.IComponentHistoryDao;
import org.syracus.rapid.history.dao.IModuleHistoryDao;
import org.syracus.rapid.history.dao.IProjectHistoryDao;

public class HistoryService implements IHistoryService {

	private IModuleHistoryDao moduleHistoryDao;
	private IProjectHistoryDao projectHistoryDao;
	private IComponentHistoryDao componentHistoryDao;
	
	
	

	public IComponentHistoryDao getComponentHistoryDao() {
		return componentHistoryDao;
	}




	public void setComponentHistoryDao(IComponentHistoryDao componentHistoryDao) {
		this.componentHistoryDao = componentHistoryDao;
	}




	public IModuleHistoryDao getModuleHistoryDao() {
		return moduleHistoryDao;
	}




	public void setModuleHistoryDao(IModuleHistoryDao moduleHistoryDao) {
		this.moduleHistoryDao = moduleHistoryDao;
	}




	public IProjectHistoryDao getProjectHistoryDao() {
		return projectHistoryDao;
	}




	public void setProjectHistoryDao(IProjectHistoryDao projectHistoryDao) {
		this.projectHistoryDao = projectHistoryDao;
	}




	




	public void addHistory(ModuleHistory history) {
		getModuleHistoryDao().create( history );
	}
	public void addHistory(ProjectHistory history) {
		getProjectHistoryDao().create( history );
	}
	public void addHistory(ComponentHistory history) {
		getComponentHistoryDao().create( history );
	}
	

}
