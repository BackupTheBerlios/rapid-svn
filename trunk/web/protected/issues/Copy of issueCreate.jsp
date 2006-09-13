<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Create Issue
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
	<c:set var="users" value="${realmAction.allUsers}"/>
	<stripes:form action="/protected/issue.action" method="post">
		<table>
			<tr>
				<td>Module:</td>
				<td>
				<c:if test="${not empty actionBean.selectedModule}">
					<stripes:hidden name="issue.module.id" value="${actionBean.selectedModule.id}"/>
					${actionBean.selectedModule.name}
				</c:if>
				<c:if test="${empty actionBean.selectedModule}">
					<stripes:select name="issue.module.id" id="_moduleSelector" onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableProjects.jsp?moduleId='+selectedValue(this),'_projectSelector');return true;">
						<stripes:options-collection collection="${actionBean.selectableModules}" label="name" value="id"/>
					</stripes:select>
				</c:if>
				</td>
			</tr>
			<div id="_projectSelector">
			<tr>
				<td>Project:</td>
				<td>
				<c:if test="${not empty actionBean.selectedProject}">
					<stripes:hidden name="issue.project.id" value="${actionBean.selectedProject.id}"/>
					${actionBean.selectedProject.name}
				</c:if>
				<c:if test="${empty actionBean.selectedProject}">
					<c:if test="${not empty actionBean.selectableProjects}">
						<stripes:select name="issue.project.id" onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableComponents.jsp?projectId='+selectedValue(this),'_componentSelector');return true;">
							<stripes:options-collection collection="${actionBean.selectableProjects}" label="name" value="id"/>
						</stripes:select>
					</c:if>
					<c:if test="${empty actionBean.selectableProjects}">
						<select disabled="disabled">
							<option>No projects available.</option>
						</select>
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>Component:</td>
				<td>
				<c:if test="${not empty actionBean.selectedComponent}">
					<stripes:hidden name="issue.component.id" value="${actionBean.selectedComponent.id}"/>
					${actionBean.selectedComponent.name}
				</c:if>
				<c:if test="${empty actionBean.selectedComponent}">
					<div id="_componentSelector">
						<c:if test="${not empty actionBean.selectableComponents}">
							<stripes:select name="issue.component.id">
								<stripes:options-collection collection="${actionBean.selectableComponents}" label="name" value="id"/>
							</stripes:select>
						</c:if>
						<c:if test="${empty actionBean.selectableComponents}">
							<select disabled="disabled">
								<option>No components available.</option>
							</select>
						</c:if>
					</div>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>Summary:</td>
				<td>
					<stripes:text name="issue.summary" value="${issue.summary}"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<stripes:textarea name="issue.description" value="${issue.description}"/>
				</td>
			</tr>
			<tr>
				<td>Type:</td>
				<td>
					<stripes:select name="issue.type" value="${issue.type}">
						<stripes:options-enumeration enum="org.syracus.rapid.issues.Type" />
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td>Status:</td>
				<td>
					<stripes:select name="issue.status" value="${issue.status}">
						<stripes:options-enumeration enum="org.syracus.rapid.issues.Status" />
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td>Priority:</td>
				<td>
					<stripes:select name="issue.priority" value="${issue.priority}">
						<stripes:options-enumeration enum="org.syracus.rapid.issues.Priority" />
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td>Assignee:</td>
				<td>
					<stripes:select name="issue.assignee.id" value="${issue.assignee.id}">
						<stripes:options-collection collection="${users}" label="name" value="id"/>
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td>Reporter:</td>
				<td>
					<stripes:select name="issue.reporter.id" value="${issue.reporter.id}">
						<stripes:options-collection collection="${users}" label="name" value="id"/>
					</stripes:select>
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