<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp" %>
<stripes:layout-definition>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${pageTitle}</title>
	</head>
	<body>
		<table width="100%">
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