<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<%@page import="org.syracus.rapid.profiles.UserProfile"%>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<rapid:profile var="refresh" key="<%= UserProfile.KEY_REFRESH_MODULES %>" dflt="false"/>
	
	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Modules</td>
				<td align="right">
					<c:if test="${refresh eq 'false'}">
						<stripes:link href="/protected/workbench/data/ownModules.jsp" onclick="ajaxUpdate(this.href, '_ownModuleList');return false;">
							refresh
						</stripes:link>
					</c:if>
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
		<c:if test="${refresh eq 'false'}">
			<jsp:include page="/protected/workbench/data/ownModules.jsp" flush="true"/>
		</c:if>
	</div>
	<c:if test="${refresh eq 'true'}">
		<script type="text/javascript">
			new Ajax.PeriodicalUpdater( '_ownModuleList', '/rapid/protected/workbench/data/ownModules.jsp', {frequency:10} );
		</script>
	</c:if>
	<!-- ModuleList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>