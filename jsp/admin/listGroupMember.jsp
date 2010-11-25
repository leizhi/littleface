<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="GroupMember"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/GroupMember.do" var="listGroupMember"/>

<form method="post" action="${listGroupMember}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="config.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>

<div id="container">

<div>
<span><strong>List GroupMember</strong></span>
</div>

<table>

<thead>

<tr>
<td colspan="3" >
<%@ include file="../incl/pageNavigation.jsp"%>
<%--
<table>
<tbody>
<tr>
<td><a href="${listGroupMember }">listGroupMember</a></td>
<td><a href="${listGroupMember }">listGroupMember</a></td>
</tr>
</tbody>
</table>
--%>
</td>
</tr>

<tr>
<th><input type="checkbox"/></th>
<th><fmt:message key="UserGroup"/></th>
<th><fmt:message key="User"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="item" items="${groupMembers}" varStatus="status">
<tr>
<td><input type="checkbox"/></td>
<td><c:out value="${item.userGroup.name }"/></td>
<td><c:out value="${item.user.name }"/></td>
</tr>
</c:forEach>
</tbody>

<tfoot>
<tr>
<!-- 分页 -->
<td colspan="3" style="width: 600px;text-align: left;">
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