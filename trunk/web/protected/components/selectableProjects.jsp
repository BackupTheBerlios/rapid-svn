<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean binding="/protected/component.action" var="componentAction"/>
<c:set var="projects" value="${componentAction.availableProjects}"/>
<c:if test="${not empty projects}">
<select name="component.project.id"
	onchange="ajaxText('${pageContext.request.contextPath}/protected/project.action?key=&projectId='+selectedValue(this),setKey);">
	<c:forEach var="project" items="${projects}">
	<option value="${project.id}">${project.name}</option>
	</c:forEach>
</select>
</c:if>