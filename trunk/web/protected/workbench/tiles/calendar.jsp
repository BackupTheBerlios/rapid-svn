<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../WEB-INF/includes/taglibs.jsp" %>

<stripes:layout-render name="/WEB-INF/layouts/tileLayout.jsp">

	<stripes:layout-component name="tileHeader">
		<table width="100%">
			<tr>
				<td>Your Calendar</td>
				<%--
				<td align="right">
					<stripes:link href="/protected/todos/todoCreate.jsp" onclick="ajaxUpdate(this.href, '_workbenchContent');return false;">
						new
					</stripes:link>
				</td>
				--%>
			</tr>
		</table>
	</stripes:layout-component>
	
	<stripes:layout-component name="tileContent">
	<!-- Calendar: BEGIN -->
	<cal:month month="${param.month}" year="${param.year}" border="false">
		<cal:weekdayLength>3</cal:weekdayLength>
		<cal:dayLink style="color:yellow;" target="_blank" day="3">http://www.google.de</cal:dayLink>
		<cal:dayLink target="_blank" day="12">www.yahoo.de</cal:dayLink>
		<cal:dayStyle>background-color:red;</cal:dayStyle>
		<cal:dayStyle day="12">background-color:white;color: red;</cal:dayStyle>
		<%--
		<cal:calendarClass>calendarTable</cal:calendarClass>
		<cal:weekdayStyle>font-style:italic;color:yellow;</cal:weekdayStyle>
		<cal:weekdayLength>3</cal:weekdayLength>
		<cal:dayClass>calendarDay</cal:dayClass>
		<cal:dayStyle>background-color:red;</cal:dayStyle>
		<cal:dayStyle day="10">background-color:blue;color: red;</cal:dayStyle>
		--%>
	</cal:month>

	<!-- Calendar: END -->
	</stripes:layout-component>
	
</stripes:layout-render>
