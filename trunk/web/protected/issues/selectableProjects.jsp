<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean binding="/protected/issue.action" var="issueAction"/>
<c:set var="projects" value="${issueAction.availableProjects}"/>
<c:if test="${not empty projects}">
<select name="issue.project.id"
	onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issues/selectableComponents.jsp?projectId='+selectedValue(this)+'&moduleId='+selectedValue($(_moduleSelection)),'_componentSelector');ajaxText('${pageContext.request.contextPath}/protected/project.action?key=&projectId='+selectedValue(this),setKey);return true;">
	<c:forEach var="project" items="${projects}">
	<option value="${project.id}">${project.name}</option>
	</c:forEach>
</select>
<stripes:link href="/protected/project.action" event="create" onclick="ajaxUpdate(this.href,'_workbenchContent');return false;">
	<stripes:link-param name="moduleId" value="${param.moduleId}"/>
	create
</stripes:link>
</c:if>