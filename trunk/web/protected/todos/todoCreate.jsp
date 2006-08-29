<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<stripes:form action="/protected/todo.action" method="post">
		<table>
			<tr>
				<td>Summary:</td>
				<td>
					<stripes:text name="todo.summary" value="${todo.summary}"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<stripes:text name="todo.description" value="${todo.description}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<stripes:button name="save" value="save" onclick="ajaxForm( this.form, this.name, '_workbenchContent' );"/>
				</td>
			</tr>
		</table>
	</stripes:form>
	</stripes:layout-component>
	
</stripes:layout-render>