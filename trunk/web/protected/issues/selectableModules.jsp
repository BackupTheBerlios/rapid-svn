<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<select>
	<c:forEach items="${actionBean.modules}" var="module">
		<option value="${module.id}">${module.name}</option>
	</c:forEach>
</select>