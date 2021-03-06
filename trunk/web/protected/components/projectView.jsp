<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Project Details</td>
				<td align="right">
					<stripes:useActionBean var="watchAction" binding="/protected/watch.action"/>
					<c:set var="watched" value="${watchAction.watched}"/>
					<stripes:link id="_watch" href="/protected/watch.action" event="toggle" onclick="ajaxUpdate(this.href, '_watch');return false;">
						<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
						<c:if test="${empty watched}">
						watch
						</c:if>
						<c:if test="${not empty watched}">
						unwatch
						</c:if>
					</stripes:link>
					<stripes:link href="/protected/components/projectList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
						issue
					</stripes:link>
					<stripes:link href="/protected/component.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
						component
					</stripes:link>
					<stripes:link href="/protected/project.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
						edit
					</stripes:link>
					<stripes:link href="/protected/project.action" event="delete" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
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
			<td>${actionBean.project.key}</td>
		</tr>
		<tr>
			<td>Module:</td>
			<td>
				<c:if test="${not empty actionBean.project.module}">
				<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="moduleId" value="${actionBean.project.module.id}"/>
					${actionBean.project.module.name}
				</stripes:link>
				</c:if>
				<c:if test="${empty actionBean.project.module}">
					No module assigned
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td>${actionBean.project.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.project.description}</pre>
			</td>
		</tr>
		<tr>
			<td>Project Home:</td>
			<td>
				<pre>${actionBean.project.home}</pre>
			</td>
		</tr>
		<tr>
			<td>Leader:</td>
			<td>
				${actionBean.project.leader.name}
			</td>
		</tr>
		<tr>
			<td>Created:</td>
			<td>
				${actionBean.project.created} by ${actionBean.project.creator.name}
			</td>
		</tr>
		<tr>
			<td>Last Modified:</td>
			<td>
				${actionBean.project.modified} by ${actionBean.project.modifier.name}
			</td>
		</tr>
	</table>
	
	<table width="100%" border="1">
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<stripes:link href="/protected/components/projectComponents.jsp" onclick="ajaxUpdate(this.href,'_projectViewContent');return false;">
								<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
								Components
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/components/projectIssues.jsp" onclick="ajaxUpdate(this.href,'_projectViewContent');return false;">
								<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
								Issues
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/components/projectAttachements.jsp" onclick="ajaxUpdate(this.href,'_projectViewContent');return false;">
								<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
								Attachements
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/components/projectHistory.jsp" onclick="ajaxUpdate(this.href,'_projectViewContent');return false;">
								<stripes:link-param name="projectId" value="${actionBean.project.id}"/>
								History
							</stripes:link>
						</td>
						<td>
							<stripes:link href="" onclick="return false;">
								<stripes:link-param name="moduleId" value="${actionBean.project.id}"/>
								Comments
							</stripes:link>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="_projectViewContent">
					<c:choose>
						<c:when test="${param.tab1 eq 'issues'}">
						</c:when>
						<c:otherwise>
							<c:import url="/protected/components/projectComponents.jsp">
								<c:param name="projectId" value="${actionBean.project.id}"/>
							</c:import>
						</c:otherwise>
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
	
	</stripes:layout-component>
	
</stripes:layout-render>