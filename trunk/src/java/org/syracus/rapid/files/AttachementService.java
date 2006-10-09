package org.syracus.rapid.files;

import org.syracus.rapid.files.dao.IComponentAttachementDao;
import org.syracus.rapid.files.dao.IIssueAttachementDao;
import org.syracus.rapid.files.dao.IModuleAttachementDao;
import org.syracus.rapid.files.dao.IProjectAttachementDao;

public class AttachementService implements IAttachementService {

	private IModuleAttachementDao moduleAttachementDao;
	private IProjectAttachementDao projectAttachementDao;
	private IComponentAttachementDao componentAttachementDao;
	private IIssueAttachementDao issueAttachementDao;

	

	public IComponentAttachementDao getComponentAttachementDao() {
		return componentAttachementDao;
	}

	public void setComponentAttachementDao(
			IComponentAttachementDao componentAttachementDao) {
		this.componentAttachementDao = componentAttachementDao;
	}

	public IIssueAttachementDao getIssueAttachementDao() {
		return issueAttachementDao;
	}

	public void setIssueAttachementDao(IIssueAttachementDao issueAttachementDao) {
		this.issueAttachementDao = issueAttachementDao;
	}

	public IModuleAttachementDao getModuleAttachementDao() {
		return moduleAttachementDao;
	}

	public void setModuleAttachementDao(IModuleAttachementDao moduleAttachementDao) {
		this.moduleAttachementDao = moduleAttachementDao;
	}

	public IProjectAttachementDao getProjectAttachementDao() {
		return projectAttachementDao;
	}

	public void setProjectAttachementDao(
			IProjectAttachementDao projectAttachementDao) {
		this.projectAttachementDao = projectAttachementDao;
	}

	public ComponentAttachement getComponentAttachementById(Long id) {
		return( getComponentAttachementDao().find( id ) );
	}

	public ModuleAttachement getModuleAttachementById(Long id) {
		return( getModuleAttachementDao().find( id ) );
	}

	public ProjectAttachement getProjectAttachementById(Long id) {
		return( getProjectAttachementDao().find( id ) );
	}
	public IssueAttachement getIssueAttachementById(Long id) {
		return( getIssueAttachementDao().find( id ) );
	}

	public void deleteAttachement(ComponentAttachement attachement) {
		getComponentAttachementDao().delete( attachement );
	}

	public void deleteAttachement(IssueAttachement attachement) {
		getIssueAttachementDao().delete( attachement );
	}

	public void deleteAttachement(ModuleAttachement attachement) {
		getModuleAttachementDao().delete( attachement );
	}

	public void deleteAttachement(ProjectAttachement attachement) {
		getProjectAttachementDao().delete( attachement );
	}

	
	
	

}
