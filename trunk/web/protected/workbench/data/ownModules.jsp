<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean var="moduleAction" binding="/protected/module.action"/>
<table width="100%">
	<c:forEach var="module" items="${moduleAction.ownModules}">
	<tr>
		<td>
			<stripes:link href="/protected/module.action" event="view" title="${module.description}" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
				<stripes:link-param name="moduleId" value="${module.id}"/>
					${module.name}
			</stripes:link>
		</td>
	</tr>
	</c:forEach>
</table>
