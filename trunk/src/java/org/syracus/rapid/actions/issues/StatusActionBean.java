package org.syracus.rapid.actions.issues;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.history.IssueHistory;
import org.syracus.rapid.issues.IIssueService;
import org.syracus.rapid.issues.Issue;
import org.syracus.rapid.issues.IssueStatus;
import org.syracus.rapid.realm.IRealmService;
import org.syracus.rapid.realm.User;
import org.syracus.rapid.stripes.RapidActionBean;

@UrlBinding("/protected/status.action")
public class StatusActionBean extends RapidActionBean {

	protected static final transient Log log = LogFactory.getLog( StatusActionBean.class );
	
	private Long issueId;
	private Issue issue;
	private IssueStatus selectedStatus;
	private Long statusId;
	protected String historyComment;
	private IIssueService issueService;
	private IRealmService realmService;
	
	
	public IRealmService getRealmService() {
		return realmService;
	}

	@SpringBean("realmService")
	public void setRealmService(IRealmService realmService) {
		this.realmService = realmService;
	}

	public IIssueService getIssueService() {
		return issueService;
	}
	
	@SpringBean("issueService")
	public void setIssueService(IIssueService issueService) {
		this.issueService = issueService;
	}
	
	public String getHistoryComment() {
		return historyComment;
	}

	public void setHistoryComment(String historyComment) {
		this.historyComment = historyComment;
	}

	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public IssueStatus getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(IssueStatus selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Resolution init() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[init] issueId = '" + getIssueId() + "'" );
			log.debug( "[init] statusId = '" + getStatusId() + "'" );
		}
		setSelectedStatus( getIssueService().getIssueStatusById( getStatusId() ) );
		setIssue( getIssueService().getIssueById( getIssueId() ) );
		return( new ForwardResolution( "/protected/issues/issueStatus.jsp" ) );
	}
	
	public Resolution save() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[save] issue id = '" + getIssue().getId() + "'" );
		}
		Issue issue = getIssueService().getIssueById( getIssue().getId() );
		if ( log.isDebugEnabled() ) {
			log.debug( "[save] issue = '" + issue + "'" );
		}
		
		StringBuffer message = new StringBuffer();
		
		IssueStatus oldStatus = issue.getStatus();
		if ( oldStatus.getId() != getStatusId() ) {
			IssueStatus newStatus = getIssueService().getIssueStatusById( getStatusId() );
			issue.setStatus( newStatus );
			message.append( "Status changed from '" + oldStatus.getName() + "' to '" + newStatus.getName() + "'." );
		}
		
		if ( log.isDebugEnabled() ) {
			log.debug( "[save] old assignee = '" + issue.getAssignee().getId() + "'" );
			log.debug( "[save] new assignee = '" + getIssue().getAssignee().getId() + "'" );
		}
		
		if ( false == issue.getAssignee().getId().equals( getIssue().getAssignee().getId() ) ) {
			User oldAssignee = getRealmService().getUserById( issue.getAssignee().getId() );
			User newAssignee = getRealmService().getUserById( getIssue().getAssignee().getId() );
			
			issue.setAssignee( newAssignee );
			if ( 0 < message.length() ) {
				message.append( "\n" );
			}
			message.append( "Assignee changed from '" + oldAssignee.getName() + "' to '" + newAssignee.getName() + "'." );
		}
		
		
		IssueHistory history = new IssueHistory();
		history.setCreated( new Date() );
		history.setCreator( getContext().getAuthUser() );
		history.setIssue( issue );
		if ( 0 < message.length() ) {
			history.setText( message.toString() );
		} else {
			history.setText( "No changes made." );
		}
		if ( StringUtils.isNotBlank( getHistoryComment() ) ) {
			history.setComment( getHistoryComment() );
		}
		issue.getHistory().add( history );
		getIssueService().updateIssue( issue, getContext().getAuthUser() );
		return( new RedirectResolution( "/protected/issue.action" )
			.addParameter( "view", "" )
			.addParameter( "issueId", getIssue().getId() )
		);
	}
	
	public List<IssueStatus> getIssueStatus() {
		return( getIssueService().getAllIssueStatus() );
	}
	
	public List<IssueStatus> getOtherIssueStatus() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[getOtherIssueStatus] issueId = '" + getIssueId() + "'" );
		}
		List<IssueStatus> allStatus = getIssueStatus();
		if ( null != getIssueId() ) {
			Issue issue = getIssueService().getIssueById( getIssueId() );
			if ( null != issue ) {
				IssueStatus issueStatus = issue.getStatus();
				if ( null != issueStatus ) {
					for( Iterator i = allStatus.iterator(); i.hasNext(); ) {
						IssueStatus currentStatus = (IssueStatus)i.next();
						if ( currentStatus.getId().equals( issueStatus.getId() ) ) {
							i.remove();
							break;
						}
					}
				}
			}
		}
		return( allStatus );
	}
	
}
