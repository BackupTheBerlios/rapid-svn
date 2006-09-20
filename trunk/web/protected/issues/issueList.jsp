<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Issues</td>
				<td align="right">
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
					<stripes:link href="/protected/issues/issueList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="issueAction" binding="/protected/issue.action"/>
	<c:set var="issues" value="${issueAction.allIssues}"/>
	<c:if test="${not empty issues}">
		<table width="100%" border="1">
			<tr>
				<th>Issue</th>
				<th>Component</th>
				<th>Project</th>
				<th>Module</th>
				<th>Type</th>
				<th>Status</th>
				<th>Priority</th>
				<th>Assignee</th>
				<th>Created</th>
				<th>Modified</th>
			</tr>
		<c:forEach var="issue" items="${issues}">
			<tr>
				<td>
					<stripes:link href="/protected/issue.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="issueId" value="${issue.id}"/>
						[${issue.key}] ${issue.summary}
					</stripes:link>
				</td>
				<td>
					<c:if test="${not empty issue.component}">
					<stripes:link href="/protected/component.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="componentId" value="${issue.component.id}"/>
						${issue.component.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty issue.component}">
						&nbsp;
					</c:if>
				</td>
				<td>
					<c:if test="${not empty issue.project}">
					<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="projectId" value="${issue.project.id}"/>
						${issue.project.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty issue.project}">
						&nbsp;
					</c:if>
				</td>
				<td>
					<c:if test="${not empty issue.module}">
					<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="moduleId" value="${issue.module.id}"/>
						${issue.module.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty issue.module}">
						&nbsp;
					</c:if>
				</td>
				<td>
					${issue.type.name}
				</td>
				<td>
					${issue.status.name}
				</td>
				<td>
					${issue.priority.name}
				</td>
				<td>
					${issue.assignee.name}
				</td>
				<td>
					${issue.created}
				</td>
				<td>
					${issue.modified}
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty issues}">
		No issues available.
	</c:if>
	</stripes:layout-component>
	
</stripes:layout-render>