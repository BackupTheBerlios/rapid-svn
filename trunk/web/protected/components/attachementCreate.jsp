<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Add attachement
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
		<c:if test="${not empty param.moduleId}">
			<c:set var="paramName" value="moduleId"/>
			<c:set var="paramValue" value="${param.moduleId}"/>
		</c:if>
		<c:if test="${not empty param.projectId}">
			<c:set var="paramName" value="projectId"/>
			<c:set var="paramValue" value="${param.projectId}"/>
		</c:if>
		<c:if test="${not empty param.componentId}">
			<c:set var="paramName" value="componentId"/>
			<c:set var="paramValue" value="${param.componentId}"/>
		</c:if>
		<c:if test="${not empty param.issueId}">
			<c:set var="paramName" value="issueId"/>
			<c:set var="paramValue" value="${param.issueId}"/>
		</c:if>
		<iframe marginwidth="0" marginheight="0" scrolling="no" frameborder="0" align="top" width="100%" src="${pageContext.request.contextPath}/protected/components/attachementUploadForm.jsp?${paramName}=${paramValue}">
		</iframe>
		
		<%--
		<stripes:form action="/protected/attachement.action" method="post">
			<stripes:hidden name="moduleId" value="${param.moduleId}"/>
			<stripes:hidden name="projectId" value="${param.projectId}"/>
			<stripes:hidden name="componentId" value="${param.componentId}"/>
			<stripes:hidden name="issueId" value="${param.issueId}"/>
			
			<table width="100%">
				<tr>
					<td><label>Description:</label></td>
					<td>
						<stripes:textarea name="description"/>
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
						<stripes:button name="save" value="save" onclick="ajaxForm(this.form,this.value,'${param.target}');"/>
					</td>
				</tr>
			</table>
		</stripes:form>
		--%>
	</stripes:layout-component>
	
</stripes:layout-render>