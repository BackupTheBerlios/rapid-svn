<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Edit Issue
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	
	<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
	<c:set var="allUsers" value="${realmAction.allUsers}"/>
	
	<stripes:form action="/protected/issue.action" method="post">
		<stripes:hidden name="issue.id" value="${issue.id}"/>
		<stripes:hidden name="issue.module.id" value="${issue.module.id}"/>
		<stripes:hidden name="issue.project.id" value="${issue.project.id}"/>
		<stripes:hidden name="issue.component.id" value="${issue.component.id}"/>
		<stripes:hidden name="issue.key" value="${issue.key}"/>
		<table>
			<tr>
				<td><label>KEY:</label></td>
				<td>${actionBean.issue.key}</td>
			</tr>
			<tr>
				<td><label>Module:</label></td>
				<td>
					<c:if test="${not empty actionBean.issue.module}">
					<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.issue.module.id}"/>
						${actionBean.issue.module.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty actionBean.issue.module}">
						No module assigned.
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label>Project:</label></td>
				<td>
					<c:if test="${not empty actionBean.issue.project}">
					<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="projectId" value="${actionBean.issue.project.id}"/>
						${actionBean.issue.project.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty actionBean.issue.project}">
						No project assigned.
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label>Component:</label></td>
				<td>
					<c:if test="${not empty actionBean.issue.component}">
					<stripes:link href="/protected/component.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.issue.component.id}"/>
						${actionBean.issue.component.name}
					</stripes:link>
					</c:if>
					<c:if test="${empty actionBean.issue.component}">
						No component assigned.
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label>Status:</label></td>
				<td>
					<c:set var="status" value="${actionBean.issueStatus}"/>
					<stripes:select name="issue.status.id"
						onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issue.action?statusDescription=&statusId='+selectedValue(this),'_statusDescription');">
						<stripes:options-collection collection="${status}" label="name" value="id"/>
					</stripes:select>
					<div id="_statusDescription">
						<c:if test="${not empty actionBean.issue.status.description}">
							${actionBean.issue.status.description}
						</c:if>
						<c:if test="${empty actionBean.issue.status.description}">
							No description available.
						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td><label>Type:</label></td>
				<td>
					<c:set var="types" value="${actionBean.issueTypes}"/>
					<stripes:select name="issue.type.id"
						onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issue.action?typeDescription=&typeId='+selectedValue(this),'_typeDescription');">
						<stripes:options-collection collection="${types}" label="name" value="id"/>
					</stripes:select>
					<div id="_typeDescription">
						<c:if test="${not empty actionBean.issue.type.description}">
							${actionBean.issue.type.description}
						</c:if>
						<c:if test="${empty actionBean.issue.type.description}">
							No description available.
						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td><label>Priority:</label></td>
				<td>
					<c:set var="priorities" value="${actionBean.issuePriorities}"/>
					<stripes:select name="issue.priority.id"
						onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issue.action?priorityDescription=&priorityId='+selectedValue(this),'_priorityDescription');">
						<stripes:options-collection collection="${priorities}" label="name" value="id"/>
					</stripes:select>
					<div id="_priorityDescription">
						<c:if test="${not empty actionBean.issue.priority.description}">
							${actionBean.issue.priority.description}
						</c:if>
						<c:if test="${empty actionBean.issue.priority.description}">
							No description available.
						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td><label>Summary:</label></td>
				<td>
					<stripes:text name="issue.summary" value="${issue.summary}"/>
					<stripes:errors field="summary"/>
				</td>
			</tr>
			<tr>
				<td><label>Description:</label></td>
				<td>
					<stripes:textarea name="issue.description" value="${issue.description}"/>
				</td>
			</tr>
			<tr>
				<td><label>Reporter:</label></td>
				<td>
					<stripes:select name="issue.reporter.id" value="${issue.reporter.id}">
						<stripes:options-collection collection="${allUsers}" label="name" value="id"/>
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td><label>Assignee:</label></td>
				<td>
					<stripes:select name="issue.assignee.id" value="${issue.assignee.id}">
						<stripes:options-collection collection="${allUsers}" label="name" value="id"/>
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td><label>Comment:</label></td>
				<td>
					<stripes:textarea name="historyComment"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<stripes:button name="save" value="save" onclick="ajaxForm(this.form,this.value,'_workbenchContent');"/>
				</td>
			</tr>
		</table>
	</stripes:form>
	</stripes:layout-component>
	
</stripes:layout-render>