<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Projects</td>
				<td align="right">
					<stripes:link href="/protected/project.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
					<stripes:link href="/protected/components/projectList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="projectAction" binding="/protected/project.action"/>
	<c:set var="projects" value="${projectAction.allProjects}"/>
	<c:if test="${not empty projects}">
		<table width="100%" border="1">
			<tr>
				<th>Project</th>
				<th>Module</th>
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
					<c:if test="${not empty project.module}">
					<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="moduleId" value="${project.module.id}"/>
						${project.module.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty project.module}">
						&nbsp;
					</c:if>
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