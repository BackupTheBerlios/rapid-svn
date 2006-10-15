<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
<title>Insert title here</title>
</head>
<body>
	<cal:month month="${param.month}" year="${param.year}" day="${param.day}">
		<cal:headerClass>calendarToday</cal:headerClass>
		<cal:headerStyle>font-weight:bold;</cal:headerStyle>
	</cal:month>
</body>
</html>