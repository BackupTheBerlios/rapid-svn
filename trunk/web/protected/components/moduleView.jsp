<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<table>
		<tr>
			<td>Name:</td>
			<td>${actionBean.module.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.module.description}</pre>
			</td>
		</tr>
	</table>
	</stripes:layout-component>
	
</stripes:layout-render>