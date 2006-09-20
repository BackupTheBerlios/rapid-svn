<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Assigned Issues</td>
				<td align="right">
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="componentId" value="${param.componentId}"/>
						new
					</stripes:link>
					<stripes:link href="/protected/components/componentIssues.jsp" onclick="ajaxUpdate(this.href, '_projectViewContent');return false;">
						<stripes:link-param name="componentId" value="${param.componentId}"/>
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="componentAction" binding="/protected/component.action"/>
	<c:set var="issues" value="${componentAction.componentIssues}"/>
	<c:if test="${not empty issues}">
	<table width="100%" border="1">
		<tr>
			<th>Issue</th>
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