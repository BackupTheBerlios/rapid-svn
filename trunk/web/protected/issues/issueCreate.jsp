<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Create Issue
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	
	<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
	<c:set var="allUsers" value="${realmAction.allUsers}"/>
	
	<stripes:form action="/protected/issue.action" method="post">
		<stripes:hidden name="moduleId" value="${moduleId}"/>
		<stripes:hidden name="projectId" value="${projectId}"/>
		<stripes:hidden name="componentId" value="${componentId}"/>
		<table width="100%">
			<tr>
				<td><label>Module:</label></td>
				<td>
					<c:if test="${not empty actionBean.selectedModule}">
						<select id="_moduleSelection" name="issue.module.id" value="${actionBean.selectedModule.id}" disabled="disabled">
							<option selected="selected" value="${actionBean.selectedModule.id}">${actionBean.selectedModule.name}</option>
						</select>
					</c:if>
					<c:if test="${empty actionBean.selectedModule}">
						<stripes:select id="_moduleSelection" name="issue.module.id" value="${issue.module.id}"
							onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableProjects.jsp?moduleId='+selectedValue(this),'_projectSelector');ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableComponents.jsp?moduleId='+selectedValue(this),'_componentSelector');ajaxText('${pageContext.request.contextPath}/protected/module.action?key=&moduleId='+selectedValue(this),setKey);return true;">
							<stripes:options-collection collection="${actionBean.selectableModules}" label="name" value="id"/>
						</stripes:select>
					</c:if>
					<stripes:errors field="selection"/>
				</td>
			</tr>
			<tr>
				<td><label>Project:</label></td>
				<td>
					<c:if test="${not empty actionBean.selectedProject}">
						<select name="issue.project.id" value="${actionBean.selectedProject.id}" disabled="disabled">
							<option selected="selected" value="${actionBean.selectedProject.id}">${actionBean.selectedProject.name}</option>
						</select>
						<%--
						<stripes:hidden name="issue.project.id" value="${actionBean.selectedProject.id}"/>
						${actionBean.selectedProject.name}
						--%>
					</c:if>
					<c:if test="${empty actionBean.selectedProject}">
						<div id="_projectSelector">
						<stripes:select name="issue.project.id" value="${issue.project.id}"
							onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableComponents.jsp?projectId='+selectedValue(this)+'&moduleId='+selectedValue($(_moduleSelection)),'_componentSelector');ajaxText('${pageContext.request.contextPath}/protected/project.action?key=&projectId='+selectedValue(this),setKey);return true;">
							<stripes:options-collection collection="${actionBean.selectableProjects}" label="name" value="id"/>
						</stripes:select>
						</div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label>Component:</label></td>
				<td>
					<c:if test="${not empty actionBean.selectedComponent}">
						<select id="_componentSelection" name="issue.component.id" value="${actionBean.selectedComponent.id}" disabled="disabled">
							<option selected="selected" value="${actionBean.selectedComponent.id}">${actionBean.selectedComponent.name}</option>
						</select>
					</c:if>
					<c:if test="${empty actionBean.selectedComponent}">
						<div id="_componentSelector">
						<stripes:select name="issue.component.id" value="${issue.component.id}"
							onchange="ajaxText('${pageContext.request.contextPath}/protected/component.action?key=&componentId='+selectedValue(this),setKey);return true;">
							<stripes:options-collection collection="${actionBean.selectableComponents}" label="name" value="id"/>
						</stripes:select>
						</div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label>KEY:</label></td>
				<td>
					<stripes:text disabled="disabled" maxlength="5" size="5" id="_key" name="issue.key" value="${issue.key}"/>
					<stripes:errors field="key"/>
				</td>
			</tr>
			<tr>
				<td><label>Type:</label></td>
				<td>
					<stripes:select name="issue.type" value="${issue.type}">
						<stripes:options-enumeration enum="org.syracus.rapid.issues.Type"/>
					</stripes:select>
				</td>
			</tr>
			<tr>
				<td><label>Priority:</label></td>
				<td>
					<stripes:select name="issue.priority" value="${issue.priority}">
						<stripes:options-enumeration enum="org.syracus.rapid.issues.Priority"/>
					</stripes:select>
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
				<td colspan="2">
					<stripes:button name="save" value="save" onclick="ajaxForm(this.form,this.value,'_workbenchContent');"/>
				</td>
			</tr>
		</table>
	</stripes:form>
	</stripes:layout-component>
	
</stripes:layout-render>