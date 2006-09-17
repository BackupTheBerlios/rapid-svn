<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<%@page import="org.syracus.rapid.profiles.UserProfile"%>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<rapid:profile var="refresh" key="<%= UserProfile.KEY_REFRESH_COMPONENTS %>" dflt="false"/>

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Components</td>
				<td align="right">
					<c:if test="${refresh eq 'false'}">
						<stripes:link href="/protected/workbench/data/ownComponents.jsp" onclick="ajaxUpdate(this.href, '_ownComponentList');return false;">
							refresh
						</stripes:link>
					</c:if>
					<stripes:link href="/protected/components/componentList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/component.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- ComponentList: BEGIN -->
	<div id="_ownComponentList">
		<c:if test="${refresh eq 'false'}">
			<jsp:include page="/protected/workbench/data/ownComponents.jsp" flush="true"/>
		</c:if>
	</div>
	<c:if test="${refresh eq 'true'}">
		<script type="text/javascript">
			new Ajax.PeriodicalUpdater( '_ownComponentList', '/rapid/protected/workbench/data/ownComponents.jsp', {frequency:10} );
		</script>
	</c:if>
	<!-- ComponentList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>