<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Todos</td>
				<td align="right">
					<stripes:link href="/protected/todos/todoCreate.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- TodoList: BEGIN -->
	<div id="_ownTodoList">
		<%--
		<jsp:include page="/protected/workbench/data/ownTodos.jsp" flush="true"/>
		--%>
	</div>
	<script type="text/javascript">
		new Ajax.PeriodicalUpdater( '_ownTodoList', '/rapid/protected/workbench/data/ownTodos.jsp', {frequency:10} );
	</script>
	<!-- TodoList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>