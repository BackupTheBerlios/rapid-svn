package org.syracus.rapid.history;

import org.syracus.rapid.issues.Issue;

/**
 * 
 * @author snwiem
 * @hibernate.class table="issue_history"
 */
public class IssueHistory extends AbstractHistory {

	private Issue issue;
	private String comment;

	/**
	 * 
	 * @return
	 * @hibernate.property type="text" not-null="false"
	 */
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

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
