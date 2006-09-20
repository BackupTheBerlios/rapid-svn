<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Issue history</td>
				<td align="right">
					<%--
					<stripes:link href="/protected/components/attachementCreate.jsp" onclick="ajaxUpdate(this.href, '_issueViewContent');return false;">
						<stripes:link-param name="target" value="_issueViewContent"/>
						<stripes:link-param name="issueId" value="${param.issueId}"/>
						new
					</stripes:link>
					--%>
					<%--
					<stripes:link href="/protected/components/moduleIssues.jsp" onclick="ajaxUpdate(this.href, '_moduleContentView');return false;">
						<stripes:link-param name="moduleId" value="${param.moduleId}"/>
						refresh
					</stripes:link>
					--%>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="issueAction" binding="/protected/issue.action"/>
	<c:set var="history" value="${issueAction.issueHistory}"/>
	<c:if test="${not empty history}">
	<table width="100%" border="1">
		<tr>
			<th>Date</th>
			<th>Message</th>
			<th>Comment</th>
		</tr>
	<c:forEach var="entry" items="${history}">
		<tr>
			<td>${entry.created}</td>
			<td><pre>${entry.text}</pre></td>
			<td><pre>${entry.comment}</pre></td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty history}">
		No history available.
	</c:if>
	</stripes:layout-component>
	
</stripes:layout-render>