<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Available Components</td>
				<td align="right">
					<stripes:link href="/protected/components/componentList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/component.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="projectId" value="${param.projectId}"/>
						new
					</stripes:link>
					<stripes:link href="/protected/components/projectComponents.jsp" onclick="ajaxUpdate(this.href, '_projectViewContent');return false;">
						<stripes:link-param name="projectId" value="${param.projectId}"/>
						refresh
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="projectAction" binding="/protected/project.action"/>
	<c:set var="components" value="${projectAction.projectComponents}"/>
	<c:if test="${not empty components}">
	<table width="100%" border="1">
		<tr>
			<th>Component</th>
			<c:if test="${param.modules != 'false'}">
			<th>Module</th>
			</c:if>
			<c:if test="${param.projects != 'false'}">
			<th>Project</th>
			</c:if>
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
			<c:if test="${param.modules != 'false'}">
			<td>
				<c:if test="${not empty component.module}">
					<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="moduleId" value="${component.module.id}"/>
						${component.module.name}
					</stripes:link>
				</c:if>
			</td>
			</c:if>
			<c:if test="${param.projects != 'false'}">
			<td>
				<c:if test="${not empty component.project}">
					<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						<stripes:link-param name="projectId" value="${component.project.id}"/>
						${component.project.name}
					</stripes:link>
				</c:if>
			</td>
			</c:if>
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