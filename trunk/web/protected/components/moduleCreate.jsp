<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/module.action" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td>
					<stripes:text name="module.name" value=${module.name}"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<stripes:text name="module.description" value=${module.description}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<stripes:submit name="save" value="save"/>
				</td>
			</tr>
		</table>
	</stripes:form>
	</stripes:layout-component>
	
</stripes:layout-render>