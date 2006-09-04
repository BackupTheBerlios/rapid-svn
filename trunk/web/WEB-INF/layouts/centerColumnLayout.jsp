<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp" %>
<stripes:layout-definition>
<table width="100%" border="1">
	<tr>
		<td width="25%" valign="top">
			<stripes:layout-component name="leftColumn"/>
		</td>
		<td valign="top">
			<stripes:layout-component name="centerColumn"/>
		</td>
		<td width="25%" valign="top">
			<stripes:layout-component name="rightColumn"/>
		</td>
	</tr>
</table>
</stripes:layout-definition>