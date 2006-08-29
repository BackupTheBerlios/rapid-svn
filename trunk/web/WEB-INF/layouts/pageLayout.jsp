<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp" %>
<stripes:layout-definition>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="pragma" content="no-cache">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rapid.js"></script>
		<title>${pageTitle}</title>
	</head>
	<body>
		<table width="100%" border="1">
			<tr>
				<td>
					<stripes:layout-component name="pageHeader"/>
				</td>
			</tr>
			<tr>
				<td>
					<stripes:layout-component name="pageContent"/>
				</td>
			</tr>
		</table>
	</body>
</html>
</stripes:layout-definition>