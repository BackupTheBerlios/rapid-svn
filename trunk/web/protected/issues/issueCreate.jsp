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
		<stripes:hidden name="issue.status.id" value="${issue.status.id}"/>
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
						<stripes:link href="/protected/components/moduleCreate.jsp" onclick="ajaxUpdate(this.href,'_workbenchContent');return false;">
							create
						</stripes:link>
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
						<stripes:link href="/protected/project.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return false;">
							create
						</stripes:link>
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
						<stripes:link href="/protected/component.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return false;">
							create
						</stripes:link>
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
							<c:if test="${not empty types}">
								<c:if test="${not empty types[0].description}">
									${types[0].description}
								</c:if>
								<c:if test="${empty types[0].description}">
									No description available.
								</c:if>
							</c:if>
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
							<c:if test="${not empty priorities}">
								<c:if test="${not empty priorities[0].description}">
									${priorities[0].description}
								</c:if>
								<c:if test="${empty priorities[0].description}">
									No description available.
								</c:if>
							</c:if>
						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td><label>Status:</label></td>
				<td>${actionBean.issue.status.name}</td>
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