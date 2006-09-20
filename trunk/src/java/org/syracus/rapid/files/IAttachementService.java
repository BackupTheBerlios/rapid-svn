package org.syracus.rapid.files;

public interface IAttachementService {

	
	public ModuleAttachement getModuleAttachementById( Long id );
	public ProjectAttachement getProjectAttachementById( Long id );
	public ComponentAttachement getComponentAttachementById( Long id );
	public IssueAttachement getIssueAttachementById( Long id );
	
	public void deleteAttachement( IssueAttachement attachement );
	public void deleteAttachement( ComponentAttachement attachement );
	public void deleteAttachement( ProjectAttachement attachement );
	public void deleteAttachement( ModuleAttachement attachement );
}
