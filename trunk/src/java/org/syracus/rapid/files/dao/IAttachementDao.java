package org.syracus.rapid.files.dao;

import org.syracus.rapid.files.ComponentAttachement;
import org.syracus.rapid.files.IssueAttachement;
import org.syracus.rapid.files.ModuleAttachement;
import org.syracus.rapid.files.ProjectAttachement;

public interface IAttachementDao {
	
	public ModuleAttachement findModuleAttachement( Long id );
	public ProjectAttachement findProjectAttachement( Long id );
	public ComponentAttachement findComponentAttachement( Long id );
	public IssueAttachement findIssueAttachement( Long id );
	
	public void deleteAttachement( IssueAttachement attachement );
	public void deleteAttachement( ComponentAttachement attachement );
	public void deleteAttachement( ProjectAttachement attachement );
	public void deleteAttachement( ModuleAttachement attachement );
}
