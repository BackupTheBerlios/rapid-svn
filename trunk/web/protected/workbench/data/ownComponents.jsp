<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean var="componentAction" binding="/protected/component.action"/>
<table width="100%">
	<c:forEach var="component" items="${componentAction.ownComponents}">
	<tr>
		<td>
			<stripes:link href="/protected/component.action" event="view" title="${component.description}" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
				<stripes:link-param name="componentId" value="${component.id}"/>
					${component.name}
			</stripes:link>
		</td>
	</tr>
	</c:forEach>
</table>