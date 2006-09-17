<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Create Project
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/project.action" method="post">
		<stripes:hidden name="project.id" value="${project.id}"/>
		<stripes:hidden name="project.module.id" value="${project.module.id}"/>
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
						No module assigned.
					</c:if>
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