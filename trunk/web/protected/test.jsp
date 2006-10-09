<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/includes/taglibs.jsp" %>
<html>
	<body>
		<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
		<c:set var="user" value="${realmAction.authUser}"/>
		<p>${user.name}</p>
		<c:forEach var="role" items="${user.roles}">
			<p>${role.name}</p>
		</c:forEach>
	</body>
</html>