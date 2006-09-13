<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>
<%@page import="org.syracus.rapid.profiles.UserProfile"%>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<rapid:profile var="refresh" key="<%= UserProfile.KEY_REFRESH_ISSUES %>" dflt="false"/>

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Issues</td>
				<td align="right">
					<c:if test="${refresh eq 'false'}">
						<stripes:link href="/protected/workbench/data/ownIssues.jsp" onclick="ajaxUpdate(this.href, '_ownIssueList');return false;">
							refresh
						</stripes:link>
					</c:if>
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- IssueList: BEGIN -->
	<div id="_ownIssueList">
		<c:if test="${refresh eq 'false'}">
			<jsp:include page="/protected/workbench/data/ownIssues.jsp" flush="true"/>
		</c:if>
	</div>
	<c:if test="${refresh eq 'true'}">
		<script type="text/javascript">
			new Ajax.PeriodicalUpdater( '_ownIssueList', '/rapid/protected/workbench/data/ownIssues.jsp', {frequency:10} );
		</script>
	</c:if>
	<!-- IssueList: END -->
	</stripes:layout-component>
	
</stripes:layout-render>