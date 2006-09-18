<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/pageLayout.jsp" pageTitle="Workbench">

	<stripes:layout-component name="pageHeader">
	</stripes:layout-component>
	
	<stripes:layout-component name="pageContent">
	
		<stripes:layout-render name="/WEB-INF/layouts/centerColumnLayout.jsp">
		
			<stripes:layout-component name="leftColumn">
				<jsp:include page="/protected/workbench/tiles/moduleList.jsp" flush="true"/>
				<jsp:include page="/protected/workbench/tiles/projectList.jsp" flush="true"/>
				<jsp:include page="/protected/workbench/tiles/componentList.jsp" flush="true"/>
				<jsp:include page="/protected/workbench/tiles/issueList.jsp" flush="true"/>
			</stripes:layout-component>
			
			<stripes:layout-component name="centerColumn">
				<div id="_workbenchContent">
					<jsp:include page="/protected/workbench/data/overview.jsp"/>
				</div>
			</stripes:layout-component>
			
			<stripes:layout-component name="rightColumn">
				<jsp:include page="/protected/workbench/tiles/todoList.jsp" flush="true"/>
			</stripes:layout-component>
			
		</stripes:layout-render>
		
	</stripes:layout-component>
	
</stripes:layout-render>