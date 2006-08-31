package org.syracus.rapid.comments;

import org.syracus.rapid.issues.Issue;

/**
 * 
 * @author snwiem
 * @hibernate.class table="issue_comments"
 */
public class IssueComment extends AbstractComment {

	private Issue issue;
	private IssueComment parent;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public IssueComment getParent() {
		return parent;
	}

	public void setParent(IssueComment parent) {
		this.parent = parent;
	}

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
