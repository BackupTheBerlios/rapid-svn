<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
	<table width="100%">
		<tr>
			<td>Todo Operations</td>
			<td align="right">
				<stripes:link href="/protected/todo.action" event="delete">
					<stripes:link-param name="todoId" value="${actionBean.todo.id}"/>
					delete
				</stripes:link>
				<stripes:link href="/protected/todo.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="todoId" value="${actionBean.todo.id}"/>
					edit
				</stripes:link>
			</td>
		</tr>
	</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<table>
		<tr>
			<td>Summary:</td>
			<td>${actionBean.todo.summary}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.todo.description}</pre>
			</td>
		</tr>
	</table>
	</stripes:layout-component>
	
</stripes:layout-render>