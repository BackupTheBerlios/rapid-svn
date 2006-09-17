<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Module Details</td>
				<td align="right">
					<stripes:link href="/protected/components/moduleList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
						issue
					</stripes:link>
					<stripes:link href="/protected/component.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
						component
					</stripes:link>
					<stripes:link href="/protected/project.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
						project
					</stripes:link>
					<stripes:link href="/protected/module.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
						edit
					</stripes:link>
					<stripes:link href="/protected/module.action" event="delete" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
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
			<td>${actionBean.module.key}</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td>${actionBean.module.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.module.description}</pre>
			</td>
		</tr>
		<tr>
			<td>Leader:</td>
			<td>
				${actionBean.module.leader.name}
			</td>
		</tr>
		<tr>
			<td>Created:</td>
			<td>
				${actionBean.module.created} by ${actionBean.module.creator.name}
			</td>
		</tr>
		<tr>
			<td>Last Modified:</td>
			<td>
				${actionBean.module.modified} by ${actionBean.module.modifier.name}
			</td>
		</tr>
	</table>
	
	<table width="100%" border="1">
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<stripes:link href="/protected/components/moduleProjects.jsp" onclick="ajaxUpdate(this.href,'_moduleViewContent');return false;">
								<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
								Projects
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/components/moduleComponents.jsp" onclick="ajaxUpdate(this.href,'_moduleViewContent');return false;">
								<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
								Components
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/components/moduleIssues.jsp" onclick="ajaxUpdate(this.href,'_moduleViewContent');return false;">
								<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
								Issues
							</stripes:link>
						</td>
						<td>
							<stripes:link href="" onclick="return false;">
								<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
								History
							</stripes:link>
						</td>
						<td>
							<stripes:link href="" onclick="return false;">
								<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
								Comments
							</stripes:link>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="_moduleViewContent">
					<c:import url="/protected/components/moduleProjects.jsp">
						<c:param name="moduleId" value="${actionBean.module.id}"/>
						<c:param name="menu" value="false"/>
					</c:import>
				</div>
			</td>
		</tr>
	</table>
	</stripes:layout-component>
	
</stripes:layout-render>