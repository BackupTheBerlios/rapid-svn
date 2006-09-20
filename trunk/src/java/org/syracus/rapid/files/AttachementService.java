package org.syracus.rapid.files;

import org.syracus.rapid.files.dao.IAttachementDao;

public class AttachementService implements IAttachementService {

	private IAttachementDao attachementDao;

	public IAttachementDao getAttachementDao() {
		return attachementDao;
	}

	public void setAttachementDao(IAttachementDao attachementDao) {
		this.attachementDao = attachementDao;
	}

	public ComponentAttachement getComponentAttachementById(Long id) {
		return( getAttachementDao().findComponentAttachement( id ) );
	}

	public ModuleAttachement getModuleAttachementById(Long id) {
		return( getAttachementDao().findModuleAttachement( id ) );
	}

	public ProjectAttachement getProjectAttachementById(Long id) {
		return( getAttachementDao().findProjectAttachement( id ) );
	}
	public IssueAttachement getIssueAttachementById(Long id) {
		return( getAttachementDao().findIssueAttachement( id ) );
	}

	public void deleteAttachement(ComponentAttachement attachement) {
		getAttachementDao().deleteAttachement( attachement );
	}

	public void deleteAttachement(IssueAttachement attachement) {
		getAttachementDao().deleteAttachement( attachement );
	}

	public void deleteAttachement(ModuleAttachement attachement) {
		getAttachementDao().deleteAttachement( attachement );
	}

	public void deleteAttachement(ProjectAttachement attachement) {
		getAttachementDao().deleteAttachement( attachement );
	}

	
	
	

}
