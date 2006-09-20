<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		Change Status
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
		<stripes:form action="/protected/status.action" method="post">
			<stripes:hidden name="issue.id" value="${issue.id}"/>
			<table width="100%">
				<tr>
					<td><label>Issue Key:</label></td>
					<td>${actionBean.issue.key}</td>
				</tr>
				<tr>
					<td><label>Issue Summary:</label></td>
					<td>${actionBean.issue.summary}</td>
				</tr>
				<tr>
					<td><label>Old status:</label></td>
					<td>${actionBean.issue.status.name}</td>
				</tr>
				<tr>
					<td><label>Status:</label></td>
					<td>
						<c:set var="status" value="${actionBean.otherIssueStatus}"/>
						<stripes:select name="statusId"
							onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/issue.action?statusDescription=&statusId='+selectedValue(this),'_statusDescription');">
							<stripes:options-collection collection="${status}" label="name" value="id"/>
						</stripes:select>
						<div id="_statusDescription">
							<c:if test="${not empty actionBean.selectedStatus.description}">
								${actionBean.selectedStatus.description}
							</c:if>
							<c:if test="${empty actionBean.selectedStatus.description}">
								No description available.
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>Assignee:</label></td>
					<td>
						<stripes:useActionBean var="realmAction" beanclass="org.syracus.rapid.actions.realm.RealmActionBean"/>
						<stripes:select name="issue.assignee.id">
							<stripes:options-collection collection="${realmAction.allUsers}" label="name" value="id"/>
						</stripes:select>
					</td>
				</tr>
				<tr>
					<td><label>Description:</label></td>
					<td>
						<stripes:textarea name="historyComment"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<stripes:button name="save" value="save" onclick="ajaxForm(this.form,this.value,'_workbenchContent');"/>
					</td>
				</tr>
			</table>
			
		</stripes:form>
		
		
	
	</stripes:layout-component>
	
</stripes:layout-render>