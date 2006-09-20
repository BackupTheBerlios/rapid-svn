<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Assigned Issues</td>
				<td align="right">
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="projectId" value="${param.projectId}"/>
						new
					</stripes:link>
					<stripes:link href="/protected/components/projectIssues.jsp" onclick="ajaxUpdate(this.href, '_projectViewContent');return false;">
						<stripes:link-param name="projectId" value="${param.projectId}"/>
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="projectAction" binding="/protected/project.action"/>
	<c:set var="issues" value="${projectAction.projectIssues}"/>
	<c:if test="${not empty issues}">
	<table width="100%" border="1">
		<tr>
			<th>Issue</th>
			<th>Component</th>
			<th>Type</th>
			<th>Status</th>
			<th>Priority</th>
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
				${issue.type.name}
			</td>
			<td>
				${issue.status.name}
			</td>
			<td>
				${issue.priority.name}
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