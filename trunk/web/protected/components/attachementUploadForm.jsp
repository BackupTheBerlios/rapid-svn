<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
</head>
<body>
<stripes:form action="/protected/attachement.action" method="post">

<stripes:hidden name="moduleId" value="${param.moduleId}"/>
<stripes:hidden name="projectId" value="${param.projectId}"/>
<stripes:hidden name="componentId" value="${param.componentId}"/>
<stripes:hidden name="issueId" value="${param.issueId}"/>

<table width="100%" border="1">
	<stripes:messages key="UploadMessage"/>
	<tr>
		<td><label>Description:</label></td>
		<td>
			<stripes:textarea name="description" value=""/>
		</td>
	</tr>
	<tr>
		<td><label>File:</label></td>
		<td>
			<stripes:file name="attachement"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<stripes:submit name="save" value="save"/>
		</td>
	</tr>
</table>
</stripes:form>
</body>
</html>