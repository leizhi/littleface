<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="Tree"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/Tree.do" var="listFile"/>

<c:url value="/Tree.do" var="promptAdd">
	<c:param name="method">promptAdd</c:param>
</c:url>

<c:url value="/Tree.do" var="processAdd">
	<c:param name="method">processAdd</c:param>
</c:url>

<c:url value="/Tree.do" var="promptUpdate">
	<c:param name="method">promptUpdate</c:param>
</c:url>

<c:url value="/Tree.do" var="processUpdate">
	<c:param name="method">processUpdate</c:param>
</c:url>

<c:url value="/Tree.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<form method="post" action="${listFile}">
<div class="command">
<input type="submit" value="List">
<input type="submit" value="Add" onclick="docommit('${promptAdd}')">
<input type="submit" value="Edit" onclick="docommit('${promptUpdate}')">
<input type="submit" value="Delete" onclick="docommit('${processDelete}')">
</div>

<div>
<fmt:message key="Tree"/>
<table>
<tr>
<td><c:out value="ID"></c:out></td>
<td><c:out value="Name"></c:out></td>
</tr>

<c:forEach var="item" items="${treeTypes}" varStatus="status">
<tr>
<td><input type="checkbox" name="id" value="${item.id}"> </td>
<td><c:out value="${item.name}"></c:out></td>
</tr>
</c:forEach>
</table>
</div>

</form>
</fmt:bundle>
</body>
</html>