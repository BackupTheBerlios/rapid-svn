<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/pageLayout.jsp" pageTitle="RaPid - Login">
	<stripes:layout-component name="pageHeader">
		<h1>Please login</h1>
	</stripes:layout-component>
	<stripes:layout-component name="pageContent">
		<stripes:form action="/public/login.action" method="post">
		<stripes:hidden name="target" value="${target}"/>
		<table>
			<tr>
				<td>Account:</td>
				<td>
					<stripes:text name="account" value="${account}"/>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<stripes:password name="password" value="${password}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<stripes:submit name="login" value="login"/>
				</td>
			</tr>
		</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
