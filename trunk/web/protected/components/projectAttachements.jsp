<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Documents</td>
				<td align="right">
					
					<stripes:link href="/protected/components/attachementCreate.jsp" onclick="ajaxUpdate(this.href, '_projectViewContent');return false;">
						<stripes:link-param name="target" value="_projectViewContent"/>
						<stripes:link-param name="projectId" value="${param.projectId}"/>
						new
					</stripes:link>
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
	<stripes:useActionBean var="projectAction" binding="/protected/project.action"/>
	<c:set var="attachements" value="${projectAction.projectAttachements}"/>
	<c:if test="${not empty attachements}">
	<table width="100%" border="1">
		<tr>
			<th>File</th>
			<th>Type</th>
			<th>Size</th>
			<th>Date</th>
			<th>User</th>
		</tr>
	<c:forEach var="file" items="${attachements}">
		<tr>
			<td>
				<stripes:link href="/protected/attachement.action" event="view" target="_blank" title="${file.description}">
					<stripes:link-param name="attachementType" value="project"/>
					<stripes:link-param name="attachementId" value="${file.id}"/>
					${file.fileName}
				</stripes:link>
			</td>
			<td>${file.contentType}</td>
			<td>${file.fileSize}</td>
			<td>${file.fileDate}</td>
			<td>${file.creator.name}</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty attachements}">
		No documents available.
	</c:if>
	</stripes:layout-component>
	
</stripes:layout-render>