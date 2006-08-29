<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/pageLayout.jsp">

	<stripes:layout-component name="pageHeader">
	</stripes:layout-component>
	
	<stripes:layout-component name="pageContent">
	
		<stripes:layout-render name="/WEB-INF/layouts/centerColumnLayout.jsp">
		
			<stripes:layout-component name="leftColumn">
				<jsp:include page="/protected/todos/todoList.jsp" flush="true"/>
			</stripes:layout-component>
			
			<stripes:layout-component name="centerColumn">
				<div id="_workbenchContent">
				</div>
			</stripes:layout-component>
			
			<stripes:layout-component name="rightColumn">
			</stripes:layout-component>
			
		</stripes:layout-render>
		
	</stripes:layout-component>
	
</stripes:layout-render>