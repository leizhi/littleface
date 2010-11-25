<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="AuthGroup"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/AuthGroup.do" var="listAuthGroup"/>

<form method="post" action="${listAuthGroup}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="config.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>

<div id="container">

<table>
<caption>Search Account</caption>
<tbody>

<tr>
	<td style="text-align: right;"><fmt:message key="Action"/></td>
	<td><input name="query_name" value="${query_name }"/></td>
	<td style="text-align: right;"><fmt:message key="Method"/></td>
	<td><input name="query_name" value="${query_name }"/></td>
</tr>

<tr>
	<td style="text-align: right;"><fmt:message key="UserGroup"/></td>
	<td><input name="query_name" value="${query_name }"/></td>
	<td style="text-align: right;"></td>
	<td></td>
</tr>

<tr>
	<td colspan="4" style="text-align: center;">
			<input type="submit" value="<fmt:message key="List"/>">
	 </td>
</tr>
</tbody>
</table>

<table>
<caption>List AuthGroup</caption>

<thead>

<tr>
<td colspan="4" >
<%@ include file="../incl/pageNavigation.jsp"%>
<%--
<table>
<tbody>
<tr>
<td><a href="${listAuthGroup }">listAuthGroup</a></td>
<td><a href="${listAuthGroup }">listAuthGroup</a></td>
</tr>
</tbody>
</table>
--%>
</td>
</tr>

<tr>
<th><input type="checkbox"/></th>
<th><fmt:message key="UserGroup"/></th>
<th><fmt:message key="Action"/></th>
<th><fmt:message key="Method"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="item" items="${authGroups}" varStatus="status">
<tr>
<td><input type="checkbox"/></td>
<td><c:out value="${item.userGroup.name }"/></td>
<td><c:out value="${item.action.name }"/></td>
<td><c:out value="${item.method.methodName }"/></td>
</tr>
</c:forEach>
</tbody>

<tfoot>
<tr>
<!-- 分页 -->
<td colspan="4" style="width: 600px;text-align: left;">
<%@ include file="../incl/pageNavigation.jsp"%>
</td>
</tr>
</tfoot>
</table>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>