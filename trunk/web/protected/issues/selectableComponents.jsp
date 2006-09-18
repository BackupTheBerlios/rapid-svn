<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<log:debug category="JSP">moduleId = '${param.moduleId}'</log:debug>
<log:debug category="JSP">projectId = '${param.projectId}'</log:debug>
<stripes:useActionBean binding="/protected/issue.action" var="issueAction"/>
<c:set var="components" value="${issueAction.availableComponents}"/>
<c:if test="${not empty components}">
<select name="issue.component.id"
	onchange="ajaxText('${pageContext.request.contextPath}/protected/component.action?key=&componentId='+selectedValue(this),setKey);return true;">
	<c:forEach var="component" items="${components}">
	<option value="${component.id}">${component.name}</option>
	</c:forEach>
</select>
</c:if>