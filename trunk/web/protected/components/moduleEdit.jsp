<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Edit Module
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/module.action" method="post">
		<stripes:hidden name="module.id" value="${module.id}"/>
		<stripes:hidden name="module.key" value="${module.key}"/>
		<table>
			<tr>
				<td>KEY:</td>
				<td>${actionBean.module.key}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>
					<stripes:text name="module.name" value="${module.name}"/>
					<stripes:errors field="name"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<stripes:text name="module.description" value="${module.description}"/>
				</td>
			</tr>
			<tr>
				<td>Leader:</td>
				<td>
					<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
					<stripes:select name="module.leader.id" value="${module.leader.id}">
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