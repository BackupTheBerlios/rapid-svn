<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Component Details</td>
				<td align="right">
					<stripes:link href="/protected/components/componentList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
						issue
					</stripes:link>
					<stripes:link href="/protected/component.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
						edit
					</stripes:link>
					<stripes:link href="/protected/component.action" event="delete" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
						delete
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<table>
		<tr>
			<td>KEY:</td>
			<td>${actionBean.component.key}</td>
		</tr>
		<tr>
			<td>Module:</td>
			<td>
				<c:if test="${not empty actionBean.component.module}">
				<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="moduleId" value="${actionBean.component.module.id}"/>
					${actionBean.component.module.name}
				</stripes:link>
				</c:if>
				<c:if test="${empty actionBean.component.module}">
					No module assigned.
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Project:</td>
			<td>
				<c:if test="${not empty actionBean.component.project}">
				<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="projectId" value="${actionBean.component.project.id}"/>
					${actionBean.component.project.name}
				</stripes:link>
				</c:if>
				<c:if test="${empty actionBean.component.project}">
					No project assigned.
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td>${actionBean.component.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.component.description}</pre>
			</td>
		</tr>
		<tr>
			<td>Leader:</td>
			<td>
				${actionBean.component.leader.name}
			</td>
		</tr>
		<tr>
			<td>Created:</td>
			<td>
				${actionBean.component.created} by ${actionBean.component.creator.name}
			</td>
		</tr>
		<tr>
			<td>Last Modified:</td>
			<td>
				${actionBean.component.modified} by ${actionBean.component.modifier.name}
			</td>
		</tr>
	</table>
	
	<table width="100%" border="1">
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<stripes:link href="/protected/components/componentIssues.jsp" onclick="ajaxUpdate(this.href,'_componentViewContent');return false;">
								<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
								Issues
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/components/componentAttachements.jsp" onclick="ajaxUpdate(this.href,'_componentViewContent');return false;">
								<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
								Attachements
							</stripes:link>
						</td>
						<td>
							<stripes:link href="" onclick="return false;">
								<stripes:link-param name="moduleId" value="${actionBean.component.id}"/>
								History
							</stripes:link>
						</td>
						<td>
							<stripes:link href="" onclick="return false;">
								<stripes:link-param name="moduleId" value="${actionBean.component.id}"/>
								Comments
							</stripes:link>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="_componentViewContent">
					<c:choose>
						<c:when test="${param.tab1 eq 'comments'}">
						</c:when>
						<c:otherwise>
							<c:import url="/protected/components/componentIssues.jsp">
								<c:param name="componentId" value="${actionBean.component.id}"/>
							</c:import>
						</c:otherwise>
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
	
	</stripes:layout-component>
	
</stripes:layout-render>