<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Components</td>
				<td align="right">
					<stripes:link href="/protected/components/componentCreate.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- ComponentList: BEGIN -->
	<div id="_ownComponentList">
		<%--
		<jsp:include page="/protected/workbench/data/ownTodos.jsp" flush="true"/>
		--%>
	</div>
	<script type="text/javascript">
		new Ajax.PeriodicalUpdater( '_ownComponentList', '/rapid/protected/workbench/data/ownComponents.jsp', {frequency:10} );
	</script>
	<!-- ComponentList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>