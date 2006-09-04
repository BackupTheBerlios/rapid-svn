<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Projects</td>
				<td align="right">
					<stripes:link href="/protected/components/projectCreate.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- ProjectList: BEGIN -->
	<div id="_ownProjectList">
		<%--
		<jsp:include page="/protected/workbench/data/ownTodos.jsp" flush="true"/>
		--%>
	</div>
	<script type="text/javascript">
		new Ajax.PeriodicalUpdater( '_ownProjectList', '/rapid/protected/workbench/data/ownProjects.jsp', {frequency:10} );
	</script>
	<!-- ProjectList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>