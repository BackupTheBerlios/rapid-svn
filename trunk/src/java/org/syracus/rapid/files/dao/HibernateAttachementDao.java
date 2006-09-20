package org.syracus.rapid.files.dao;

import org.syracus.rapid.common.AbstractHibernateDao;
import org.syracus.rapid.files.ComponentAttachement;
import org.syracus.rapid.files.IssueAttachement;
import org.syracus.rapid.files.ModuleAttachement;
import org.syracus.rapid.files.ProjectAttachement;

public class HibernateAttachementDao extends AbstractHibernateDao
		implements IAttachementDao {

	public ModuleAttachement findModuleAttachement(Long id) {
		return( (ModuleAttachement)getHibernateTemplate().get( ModuleAttachement.class, id ) );
	}
	public ProjectAttachement findProjectAttachement(Long id) {
		return( (ProjectAttachement)getHibernateTemplate().get( ProjectAttachement.class, id ) );
	}
	public ComponentAttachement findComponentAttachement(Long id) {
		return( (ComponentAttachement)getHibernateTemplate().get( ComponentAttachement.class, id ) );
	}
	public IssueAttachement findIssueAttachement(Long id) {
		return( (IssueAttachement)getHibernateTemplate().get( IssueAttachement.class, id ) );
	}
	public void deleteAttachement(ComponentAttachement attachement) {
		getHibernateTemplate().delete( attachement );
	}
	public void deleteAttachement(IssueAttachement attachement) {
		getHibernateTemplate().delete( attachement );
	}
	public void deleteAttachement(ModuleAttachement attachement) {
		getHibernateTemplate().delete( attachement );
	}
	public void deleteAttachement(ProjectAttachement attachement) {
		getHibernateTemplate().delete( attachement );
	}

	
}
