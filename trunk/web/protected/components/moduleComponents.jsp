<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Components</td>
				<td align="right">
					<stripes:link href="/protected/component.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="moduleId" value="${param.moduleId}"/>
						new
					</stripes:link>
					<stripes:link href="/protected/components/moduleComponents.jsp" onclick="ajaxUpdate(this.href, '_moduleContentView');return false;">
						<stripes:link-param name="moduleId" value="${param.moduleId}"/>
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="moduleAction" binding="/protected/module.action"/>
	<c:set var="components" value="${moduleAction.moduleComponents}"/>
	<c:if test="${not empty components}">
	<table width="100%" border="1">
		<tr>
			<th>Component</th>
			<th>Project</th>
			<th>Leader</th>
			<th>Created</th>
			<th>Modified</th>
		</tr>
	<c:forEach var="component" items="${components}">
		<tr>
			<td>
				<stripes:link href="/protected/component.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
					<stripes:link-param name="componentId" value="${component.id}"/>
					${component.name}
				</stripes:link>
			</td>
			<td>
				<c:if test="${not empty component.project}">
					<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return false;">
						<stripes:link-param name="projectId" value="${component.project.id}"/>
						${component.project.name}
					</stripes:link>
				</c:if>
				<c:if test="${empty component.project}">
					&nbsp;
				</c:if>
			</td>
			<td>
				${component.leader.name}
			</td>
			<td>
				${component.created}
			</td>
			<td>
				${component.modified}
			</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty components}">
		No components available.
	</c:if>
	</stripes:layout-component>
	
</stripes:layout-render>