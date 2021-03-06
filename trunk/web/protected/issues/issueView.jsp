<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../WEB-INF/includes/taglibs.jsp" %>
<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%" border="0">
			<tr>
				<td align="left">Issue Details</td>
				<td align="right">
					<c:set var="status" value="${actionBean.issueStatus}"/>
					<label>Status:</label>
					<select name=""
						onchange="ajaxUpdate('${pageContext.request.contextPath}/protected/status.action?init=&issueId=${actionBean.issue.id}&statusId='+selectedValue(this),'_workbenchContent');">
						<c:forEach var="s" items="${status}">
							<c:choose>
								<c:when test="${s.id eq actionBean.issue.status.id}">
									<option value="${s.id}" selected="selected">${s.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${s.id}">${s.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td align="right">
					<stripes:link href="/protected/issues/issueList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/issue.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="issueId" value="${actionBean.issue.id}"/>
						edit
					</stripes:link>
					<stripes:link href="/protected/issue.action" event="delete" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="issueId" value="${actionBean.issue.id}"/>
						delete
					</stripes:link>
				</td>
				<%--
				<td align="right">
					<stripes:link href="/protected/components/componentList.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						all
					</stripes:link>
					<stripes:link href="/protected/issue.action" event="create" onclick="ajaxUpdate(this.href, '_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
						issue
					</stripes:link>
					<stripes:link href="/protected/component.action" event="edit" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
						edit
					</stripes:link>
					<stripes:link href="/protected/component.action" event="delete" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
						<stripes:link-param name="componentId" value="${actionBean.component.id}"/>
						delete
					</stripes:link>
				</td>
				--%>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<table>
		<tr>
			<td>KEY:</td>
			<td>
				${actionBean.issue.key}
			</td>
		</tr>
		<tr>
			<td>Module:</td>
			<td>
				<c:if test="${not empty actionBean.issue.module}">
				<stripes:link href="/protected/module.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="moduleId" value="${actionBean.issue.module.id}"/>
					${actionBean.issue.module.name}
				</stripes:link>
				</c:if>
				<c:if test="${empty actionBean.issue.module}">
					No module assigned.
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Project:</td>
			<td>
				<c:if test="${not empty actionBean.issue.project}">
				<stripes:link href="/protected/project.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="projectId" value="${actionBean.issue.project.id}"/>
					${actionBean.issue.project.name}
				</stripes:link>
				</c:if>
				<c:if test="${empty actionBean.issue.project}">
					No project assigned.
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Component:</td>
			<td>
				<c:if test="${not empty actionBean.issue.component}">
				<stripes:link href="/protected/component.action" event="view" onclick="ajaxUpdate(this.href,'_workbenchContent');return(false);">
					<stripes:link-param name="componentId" value="${actionBean.issue.component.id}"/>
					${actionBean.issue.component.name}
				</stripes:link>
				</c:if>
				<c:if test="${empty actionBean.issue.component}">
					No component assigned.
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Type:</td>
			<td>${actionBean.issue.type.name}</td>
		</tr>
		<tr>
			<td>Priority:</td>
			<td>${actionBean.issue.priority.name}</td>
		</tr>
		<tr>
			<td>Status:</td>
			<td>${actionBean.issue.status.name}</td>
		</tr>
		<tr>
			<td>Summary:</td>
			<td>${actionBean.issue.summary}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<pre>${actionBean.issue.description}</pre>
			</td>
		</tr>
		<tr>
			<td>Reporter:</td>
			<td>
				${actionBean.issue.reporter.name}
			</td>
		</tr>
		<tr>
			<td>Assignee:</td>
			<td>
				${actionBean.issue.assignee.name}
			</td>
		</tr>
		<tr>
			<td>Created:</td>
			<td>
				${actionBean.issue.created} by ${actionBean.issue.creator.name}
			</td>
		</tr>
		<tr>
			<td>Last Modified:</td>
			<td>
				${actionBean.issue.modified} by ${actionBean.issue.modifier.name}
			</td>
		</tr>
	</table>
	
	<table width="100%" border="1">
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<stripes:link href="/protected/issues/issueAttachements.jsp" onclick="ajaxUpdate(this.href,'_issueViewContent');return false;">
								<stripes:link-param name="issueId" value="${actionBean.issue.id}"/>
								Attachements
							</stripes:link>
						</td>
						<td>
							<stripes:link href="/protected/issues/issueHistory.jsp" onclick="ajaxUpdate(this.href,'_issueViewContent');return false;">
								<stripes:link-param name="issueId" value="${actionBean.issue.id}"/>
								History
							</stripes:link>
						</td>
						<td>
							<stripes:link href="" onclick="return false;">
								<stripes:link-param name="issueId" value="${actionBean.issue.id}"/>
								Comments
							</stripes:link>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="_issueViewContent">
					<c:choose>
						<c:when test="${param.tab1 eq 'comments'}">
						</c:when>
						<c:otherwise>
							<c:import url="/protected/issues/issueAttachements.jsp">
								<c:param name="issueId" value="${actionBean.issue.id}"/>
							</c:import>
						</c:otherwise>
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
	
	</stripes:layout-component>
	
</stripes:layout-render>