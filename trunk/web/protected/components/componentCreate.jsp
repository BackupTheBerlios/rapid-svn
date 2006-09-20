<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Create Component
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/component.action" method="post">
		<stripes:hidden name="moduleId" value="${moduleId}"/>
		<stripes:hidden name="projectId" value="${projectId}"/>
		<table>
			<tr>
				<td>Module:</td>
				<td>
				<c:if test="${not empty actionBean.selectedModule}">
					<stripes:hidden name="component.module.id" value="${actionBean.selectedModule.id}"/>
					${actionBean.selectedModule.name}
				</c:if>
				<c:if test="${empty actionBean.selectedModule}">
					<stripes:select name="component.module.id" id="_moduleSelector"
						onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/components/selectableProjects.jsp?moduleId='+selectedValue(this),'_projectSelector');ajaxText('${pageContext.request.contextPath}/protected/module.action?key=&moduleId='+selectedValue(this),setKey);return true;">
						<stripes:options-collection collection="${actionBean.selectableModules}" label="name" value="id"/>
					</stripes:select>
					<stripes:link href="/protected/components/moduleCreate.jsp" onclick="ajaxUpdate(this.href,'_workbenchContent');return false;">
						create
					</stripes:link>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>Project:</td>
				<td>
				<c:if test="${not empty actionBean.selectedProject}">
					<log:debug category="org.syracus.rapid.JSP">I have an selected project</log:debug>
					<stripes:hidden name="component.project.id" value="${actionBean.selectedProject.id}"/>
					${actionBean.selectedProject.name}
				</c:if>
				<c:if test="${empty actionBean.selectedProject}">
					<log:debug category="org.syracus.rapid.JSP">I expect an list a projects</log:debug>
					<div id="_projectSelector">
						<c:if test="${not empty actionBean.selectableProjects}">
							<stripes:select name="component.project.id"
								onchange="ajaxText('${pageContext.request.contextPath}/protected/project.action?key=&projectId='+selectedValue(this),setKey);">
								<stripes:options-collection collection="${actionBean.selectableProjects}" label="name" value="id"/>
							</stripes:select>
						</c:if>
					</div>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>KEY:</td>
				<td>
					<stripes:text id="_key" maxlength="5" size="5" name="component.key" value="${component.key}"/>
					<stripes:errors field="key"/>
				</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>
					<stripes:text name="component.name" value="${component.name}"/>
					<stripes:errors field="name"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<stripes:textarea name="component.description" value="${component.description}"/>
				</td>
			</tr>
			<tr>
				<td>Leader:</td>
				<td>
					<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
					<stripes:select name="component.leader.id" value="${component.leader.id}">
						<stripes:options-collection collection="${realmAction.allUsers}" label="name" value="id"/>
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