<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Edit Component
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/component.action" method="post">
		<stripes:hidden name="component.id" value="${component.id}"/>
		<stripes:hidden name="component.module.id" value="${component.module.id}"/>
		<stripes:hidden name="component.project.id" value="${component.project.id}"/>
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
				<td>
					<stripes:text name="component.name" value="${component.name}"/>
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