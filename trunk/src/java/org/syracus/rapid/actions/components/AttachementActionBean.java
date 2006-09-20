package org.syracus.rapid.actions.components;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.components.Component;
import org.syracus.rapid.components.IComponentService;
import org.syracus.rapid.components.Module;
import org.syracus.rapid.components.Project;
import org.syracus.rapid.files.AbstractAttachement;
import org.syracus.rapid.files.ComponentAttachement;
import org.syracus.rapid.files.IAttachementService;
import org.syracus.rapid.files.IssueAttachement;
import org.syracus.rapid.files.ModuleAttachement;
import org.syracus.rapid.files.ProjectAttachement;
import org.syracus.rapid.issues.IIssueService;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.stripes.RapidActionBean;

@UrlBinding("/protected/attachement.action")
public class AttachementActionBean extends RapidActionBean {

	protected static final transient Log log = LogFactory.getLog( AttachementActionBean.class );
	
	private Long attachementId;
	private String attachementType;
	private Long moduleId;
	private Long projectId;
	private Long componentId;
	private Long issueId;
	private FileBean attachement;
	private String description;
	
	private IComponentService componentService;
	private IIssueService issueService;
	private IAttachementService attachementService;

	
	
	public IIssueService getIssueService() {
		return issueService;
	}

	@SpringBean("issueService")
	public void setIssueService(IIssueService issueService) {
		this.issueService = issueService;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public IAttachementService getAttachementService() {
		return attachementService;
	}

	@SpringBean("attachementService")
	public void setAttachementService(IAttachementService attachementService) {
		this.attachementService = attachementService;
	}

	public String getAttachementType() {
		return attachementType;
	}

	public void setAttachementType(String attachementType) {
		this.attachementType = attachementType;
	}

	public Long getAttachementId() {
		return attachementId;
	}

	public void setAttachementId(Long attachementId) {
		this.attachementId = attachementId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IComponentService getComponentService() {
		return componentService;
	}

	@SpringBean("componentService")
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public FileBean getAttachement() {
		return attachement;
	}

	public void setAttachement(FileBean attachement) {
		this.attachement = attachement;
	}
	
	public Resolution delete() {
		Resolution resolution = null;
		if ( getAttachementType().equals( "issue" ) ) {
			IssueAttachement attachement = getAttachementService().getIssueAttachementById( getAttachementId() );
			if ( null != attachement ) {
				getAttachementService().deleteAttachement( attachement );
			}
			resolution = new ForwardResolution( "/protected/issues/issueAttachements.jsp" );
		} else if ( getAttachementType().equals( "component" ) ) {
			ComponentAttachement attachement = getAttachementService().getComponentAttachementById( getAttachementId() );
			if ( null != attachement ) {
				getAttachementService().deleteAttachement( attachement );
			}
			resolution = new ForwardResolution( "/protected/components/componentAttachements.jsp" );
		} else if ( getAttachementType().equals( "project" ) ) {
			ProjectAttachement attachement = getAttachementService().getProjectAttachementById( getAttachementId() );
			if ( null != attachement ) {
				getAttachementService().deleteAttachement( attachement );
			}
			resolution = new ForwardResolution( "/protected/components/projectAttachements.jsp" );
		} else if ( getAttachementType().equals( "module" ) ) {
			ModuleAttachement attachement = getAttachementService().getModuleAttachementById( getAttachementId() );
			if ( null != attachement ) {
				getAttachementService().deleteAttachement( attachement );
			}
			resolution = new ForwardResolution( "/protected/components/moduleAttachements.jsp" );
		}
		return( resolution );
	}
	
	public Resolution save() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[save] BEGIN (source = '" + getContext().getSourcePageResolution() + "'" );
		}
		
		FileBean f = getAttachement();
		
		if ( log.isDebugEnabled() ) {
			log.debug( "[save] f = '" + f + "'" );
		}
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			IssueAttachement attachement = new IssueAttachement();
			attachement.setIssue( issue );
						
			try {
				attachement.setContentStream( f.getInputStream() );
				attachement.setContentType( f.getContentType() );
				attachement.setFileName( f.getFileName() );
				attachement.setFileSize( f.getSize() );
				attachement.setFileDate( new Date() );
				attachement.setCreator( getContext().getAuthUser() );
				attachement.setDescription( getDescription() );
				issue.getAttachements().add( attachement );
				getIssueService().updateIssue( issue, getContext().getAuthUser() );
				
				
				setDescription( null );
				getContext().getMessages( "UploadMessage" ).add( new SimpleMessage( "Upload succeeded." ) );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		} else if ( null != getComponentId() ) {
			Component component = getComponentService().getComponentById( getComponentId() );
			ComponentAttachement attachement = new ComponentAttachement();
			attachement.setComponent( component );
			
			
			try {
				attachement.setContentStream( f.getInputStream() );
				attachement.setContentType( f.getContentType() );
				attachement.setFileName( f.getFileName() );
				attachement.setFileSize( f.getSize() );
				attachement.setFileDate( new Date() );
				attachement.setCreator( getContext().getAuthUser() );
				attachement.setDescription( getDescription() );
				component.getAttachements().add( attachement );
				getComponentService().updateComponent( component, getContext().getAuthUser() );
				
				
				setDescription( null );
				getContext().getMessages( "UploadMessage" ).add( new SimpleMessage( "Upload succeeded." ) );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		} else if ( null != getProjectId() ) {
			Project project = getComponentService().getProjectById( getProjectId() );
			ProjectAttachement attachement = new ProjectAttachement();
			attachement.setProject( project );
			
			
			try {
				attachement.setContentStream( f.getInputStream() );
				attachement.setContentType( f.getContentType() );
				attachement.setFileName( f.getFileName() );
				attachement.setFileSize( f.getSize() );
				attachement.setFileDate( new Date() );
				attachement.setCreator( getContext().getAuthUser() );
				attachement.setDescription( getDescription() );
				project.getAttachements().add( attachement );
				getComponentService().updateProject( project, getContext().getAuthUser() );
				
				
				setDescription( null );
				getContext().getMessages( "UploadMessage" ).add( new SimpleMessage( "Upload succeeded." ) );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		} else if ( null != getModuleId() ) {
			Module module = getComponentService().getModuleById( getModuleId() );
			ModuleAttachement attachement = new ModuleAttachement();
			attachement.setModule( module );
			
			try {
				attachement.setContentStream( f.getInputStream() );
				attachement.setContentType( f.getContentType() );
				attachement.setFileName( f.getFileName() );
				attachement.setFileSize( f.getSize() );
				attachement.setFileDate( new Date() );
				attachement.setCreator( getContext().getAuthUser() );
				attachement.setDescription( getDescription() );
				module.getAttachements().add( attachement );
				getComponentService().updateModule( module, getContext().getAuthUser() );
				
				
				setDescription( null );
				getContext().getMessages( "UploadMessage" ).add( new SimpleMessage( "Upload succeeded." ) );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
		
		try {
			if ( log.isDebugEnabled() ) {
				log.debug( "[save] deleting temporary file" );
			}
			f.delete();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		if ( log.isDebugEnabled() ) {
			log.debug( "[save] END" );
		}
		return( getContext().getSourcePageResolution() );
		
	}
	
	public Resolution view() {
		StreamingResolution resolution = null;
		AbstractAttachement attachement = null;
		if ( getAttachementType().equals( "module" ) ) {
			attachement = getAttachementService().getModuleAttachementById( getAttachementId() );
		} else if ( getAttachementType().equals( "project" ) ) {
			attachement = getAttachementService().getProjectAttachementById( getAttachementId() );
		} else if ( getAttachementType().equals( "component" ) ) {
			attachement = getAttachementService().getComponentAttachementById( getAttachementId() );
		} else if ( getAttachementType().equals( "issue" ) ) {
			attachement = getAttachementService().getIssueAttachementById( getAttachementId() );
		}
		
		if ( null != attachement ) {
			String contentType = attachement.getContentType();
			if ( null == contentType ) {
				contentType = "application/octet-stream";
			}
			try {
				resolution = new StreamingResolution(
						contentType,
						attachement.getContentStream()
				);
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		return( resolution );
	}
	
	public Resolution download() {
		StreamingResolution resolution = null;
		AbstractAttachement attachement = null;
		if ( getAttachementType().equals( "module" ) ) {
			attachement = getAttachementService().getModuleAttachementById( getAttachementId() );
		} else if ( getAttachementType().equals( "project" ) ) {
			attachement = getAttachementService().getProjectAttachementById( getAttachementId() );
		} else if ( getAttachementType().equals( "component" ) ) {
			attachement = getAttachementService().getComponentAttachementById( getAttachementId() );
		} else if ( getAttachementType().equals( "issue" ) ) {
			attachement = getAttachementService().getIssueAttachementById( getAttachementId() );
		}
		
		if ( null != attachement ) {
			String contentType = attachement.getContentType();
			if ( null == contentType ) {
				contentType = "application/octet-stream";
			}
			try {
				resolution = new StreamingResolution(
						contentType,
						attachement.getContentStream()
				);
				resolution.setFilename( attachement.getFileName() );
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		return( resolution );
	}
	
}
