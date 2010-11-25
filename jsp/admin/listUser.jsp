<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="User"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/User.do" var="listUser"/>

<form method="post" action="${listUser}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="config.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>

<div id="container">

<div>
<span><strong>List User</strong></span>
</div>

<table>

<thead>

<tr>
<td colspan="4" >
<%@ include file="../incl/pageNavigation.jsp"%>
<%--
<table>
<tbody>
<tr>
<td><a href="${listUser }">listUser</a></td>
<td><a href="${listUser }">listUser</a></td>
</tr>
</tbody>
</table>
--%>
</td>
</tr>

<tr>
<th><input type="checkbox"/></th>
<th><fmt:message key="Name"/></th>
<th><fmt:message key="Alias"/></th>
<th><fmt:message key="Active"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="item" items="${users}" varStatus="status">
<tr>
<td><input type="checkbox"/> </td>
<td><c:out value="${item.name }"/></td>
<td><c:out value="${item.alias }"/></td>
<td><c:out value="${item.active }"/></td>
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