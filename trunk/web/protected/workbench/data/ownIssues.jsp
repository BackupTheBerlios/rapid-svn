<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean var="issueAction" binding="/protected/issue.action"/>
<table width="100%">
	<c:forEach var="issue" items="${issueAction.ownIssues}">
	<tr>
		<td>
			<stripes:link href="/protected/issue.action" event="view" title="${issue.description}" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
				<stripes:link-param name="issueId" value="${issue.id}"/>
					${issue.summary}
			</stripes:link>
		</td>
	</tr>
	</c:forEach>
</table>