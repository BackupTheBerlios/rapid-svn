<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<%@page import="org.syracus.rapid.profiles.UserProfile"%>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Modules</td>
				<td align="right">
					<stripes:link href="/protected/components/moduleList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/components/moduleCreate.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- ModuleList: BEGIN -->
	<div id="_ownModuleList">
		<%--
		<jsp:include page="/protected/workbench/data/ownTodos.jsp" flush="true"/>
		--%>
	</div>
	<rapid:profile var="refresh" key="<%= UserProfile.KEY_REFRESH_MODULES %>">
		<c:if test="${refresh eq 'true'}">
			<script type="text/javascript">
				new Ajax.PeriodicalUpdater( '_ownModuleList', '/rapid/protected/workbench/data/ownModules.jsp', {frequency:10} );
			</script>
		</c:if>
	</rapid:profile>
	<!-- ModuleList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>