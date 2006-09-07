<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Project Details</td>
				<td align="right">
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
			<td>Module:</td>
			<td>
				<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="moduleId" value="${actionBean.project.module.id}"/>
					${actionBean.project.module.name}
				</stripes:link>
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
	</table>
	</stripes:layout-component>
	
</stripes:layout-render>