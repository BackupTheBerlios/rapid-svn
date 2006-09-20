package org.syracus.rapid.files;

import org.syracus.rapid.issues.Issue;

/**
 * 
 * @author snwiem
 * @hibernate.class table="issue_attachements"
 */
public class IssueAttachement extends AbstractAttachement {

	private Issue issue;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true" 
	 */
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	
}
