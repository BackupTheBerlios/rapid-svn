<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Create Project
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/project.action" method="post">
		<table>
			<tr>
				<td>Module:</td>
				<td>
				<c:if test="${not empty actionBean.selectedModule}">
					<stripes:hidden name="project.module.id" value="${actionBean.selectedModule.id}"/>
					${actionBean.selectedModule.name}
				</c:if>
				<c:if test="${empty actionBean.selectedModule}">
					<stripes:select name="project.module.id"
						onchange="ajaxText('${pageContext.request.contextPath}/protected/module.action?key=&moduleId='+selectedValue(this),setProjectKey);">
						<stripes:options-collection collection="${actionBean.selectableModules}" label="name" value="id"/>
					</stripes:select>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>KEY:</td>
				<td>
					<stripes:text id="_key" maxlength="5" name="project.key" value="${project.key}"/>
				</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>
					<stripes:text name="project.name" value="${project.name}"/>
				</td>
			</tr>
			<tr>
				<td>Project Home:</td>
				<td>
					<stripes:text name="project.home" value="${project.home}"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<stripes:textarea name="project.description" value="${project.description}"/>
				</td>
			</tr>
			<tr>
				<td>Leader:</td>
				<td>
					<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
					<stripes:select name="project.leader.id" value="${project.leader.id}">
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