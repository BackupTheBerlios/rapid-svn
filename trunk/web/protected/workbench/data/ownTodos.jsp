<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:useActionBean var="todoAction" binding="/protected/todo.action"/>
<table width="100%">
	<c:forEach var="todo" items="${todoAction.ownTodos}">
	<tr>
		<td>
			<stripes:link href="/protected/todo.action" event="view" title="${todo.description}" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
				<stripes:link-param name="todoId" value="${todo.id}"/>
				<c:if test="${todo.done == true}">
					${todo.summary}
				</c:if>
				<c:if test="${todo.done == false}">
					<span style="text-decoration:line-through;">${todo.summary}</span>
				</c:if>
			</stripes:link>
		</td>
	</tr>
	</c:forEach>
</table>
