<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Projects</td>
				<td align="right">
					<stripes:link href="/protected/project.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="moduleId" value="${param.moduleId}"/>
						new
					</stripes:link>
					<stripes:link href="/protected/components/moduleProjects.jsp" onclick="ajaxUpdate(this.href, '_moduleContentView');return false;">
						<stripes:link-param name="moduleId" value="${param.moduleId}"/>
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="moduleAction" binding="/protected/module.action"/>
	<c:set var="projects" value="${moduleAction.moduleProjects}"/>
	<c:if test="${not empty projects}">
	<table width="100%" border="1">
		<tr>
			<th>Project</th>
			<th>Leader</th>
			<th>Created</th>
			<th>Modified</th>
		</tr>
	<c:forEach var="project" items="${projects}">
		<tr>
			<td>
				<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
					<stripes:link-param name="projectId" value="${project.id}"/>
					${project.name}
				</stripes:link>
			</td>
			<td>
				${project.leader.name}
			</td>
			<td>
				${project.created}
			</td>
			<td>
				${project.modified}
			</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty projects}">
		No projects available.
	</c:if>
	</stripes:layout-component>
	
</stripes:layout-render>