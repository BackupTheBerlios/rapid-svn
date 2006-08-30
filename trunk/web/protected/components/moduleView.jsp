<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Module Details</td>
				<td align="right">
					<stripes:link href="/protected/module.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="moduleId" value="${actionBean.module.id}"/>
						edit
					</stripes:link>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<table>
		<tr>
			<td>Name:</td>
			<td>${actionBean.module.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.module.description}</pre>
			</td>
		</tr>
		<tr>
			<td>Leader:</td>
			<td>
				<pre>${actionBean.module.leader.name}</pre>
			</td>
		</tr>
	</table>
	</stripes:layout-component>
	
</stripes:layout-render>