<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Modules</td>
				<td align="right">
					<stripes:link href="/protected/components/moduleCreate.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
					<stripes:link href="/protected/components/moduleList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="moduleAction" binding="/protected/module.action"/>
	<c:set var="modules" value="${moduleAction.allModules}"/>
	<c:if test="${not empty modules}">
		<table width="100%" border="1">
			<tr>
				<th>Module</th>
				<th>Leader</th>
				<th>Created</th>
				<th>Modified</th>
			</tr>
		<c:forEach var="module" items="${modules}">
			<tr>
				<td>
					<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="moduleId" value="${module.id}"/>
						${module.name}
					</stripes:link>
				</td>
				<td>
					${module.leader.name}
				</td>
				<td>
					${module.created}
				</td>
				<td>
					${module.modified}
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty modules}">
		No modules available.
	</c:if>
	</stripes:layout-component>
	
</stripes:layout-render>