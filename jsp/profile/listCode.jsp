<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="CodeType"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/CodeType.do" var="listCodeType"/>

<c:url value="/CodeType.do" var="processAddCode">
	<c:param name="method">processAddCode</c:param>
</c:url>

<c:url value="/CodeType.do" var="processUpdateCode">
	<c:param name="method">processUpdateCode</c:param>
</c:url>

<c:url value="/CodeType.do" var="processDeleteCode">
	<c:param name="method">processDeleteCode</c:param>
</c:url>

<c:url value="/CodeType.do" var="listCode">
	<c:param name="method">listCode</c:param>
</c:url>

<form method="post" action="${listCode}">
<div class="command">
<input type="submit" value="<fmt:message key="Black"/>" onclick="docommit('${listCodeType}')">
<input type="submit" value="<fmt:message key="List"/>">
<input type="submit" value="<fmt:message key="Add"/>" onclick="docommit('${processAddCode}')">
<input type="submit" value="<fmt:message key="Update"/>" onclick="docommit('${processUpdateCode}')">
<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDeleteCode}')">
</div>

<div>
<fmt:message key="Name"/>:
<input type="text" name="LinearCode.name">
<input type="hidden" name="LinearCode.typeid" value="${id}">
</div>

<div>
<c:out value="${codeType.name}"/>

<table>
<tr>
<td><fmt:message key="ID"/></td>
<td><fmt:message key="Name"/></td>
</tr>

<c:forEach var="item" items="${codes}" varStatus="status">
<tr>
<td><input type="checkbox" name="id" value="${item.id}"> </td>
<td><c:out value="${item.name}"/></td>
</tr>
</c:forEach>
</table>
</div>

</form>
</fmt:bundle>
</body>
</html>