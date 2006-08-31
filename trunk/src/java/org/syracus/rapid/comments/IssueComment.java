package org.syracus.rapid.comments;

import org.syracus.rapid.issues.Issue;

/**
 * 
 * @author snwiem
 * @hibernate.class table="issue_comments"
 */
public class IssueComment extends AbstractComment {

	private Issue issue;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	
}
