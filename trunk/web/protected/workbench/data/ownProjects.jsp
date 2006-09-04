<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean var="projectAction" binding="/protected/project.action"/>
<table width="100%">
	<c:forEach var="project" items="${projectAction.ownProjects}">
	<tr>
		<td>
			<stripes:link href="/protected/project.action" event="view" title="${project.description}" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
				<stripes:link-param name="projectId" value="${project.id}"/>
					${project.name}
			</stripes:link>
		</td>
	</tr>
	</c:forEach>
</table>
