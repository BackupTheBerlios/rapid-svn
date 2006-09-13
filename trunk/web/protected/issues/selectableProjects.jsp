<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean binding="/protected/issue.action" var="issueAction"/>
<c:set var="projects" value="${issueAction.availableProjects}"/>
<c:if test="${not empty projects}">
<select name="issue.project.id"
	onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableComponents.jsp?projectId='+selectedValue(this)+'&moduleId='+selectedValue($(_moduleSelection)),'_componentSelector');return true;">
	<c:forEach var="project" items="${projects}">
	<option value="${project.id}">${project.name}</option>
	</c:forEach>
</select>
</c:if>