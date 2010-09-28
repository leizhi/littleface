<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="LinearCode"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/LinearCode.do" var="list"/>

<c:url value="/LinearCode.do" var="promptAdd">
	<c:param name="method">promptAdd</c:param>
</c:url>

<c:url value="/LinearCode.do" var="processAdd">
	<c:param name="method">processAdd</c:param>
</c:url>

<c:url value="/LinearCode.do" var="promptUpdate">
	<c:param name="method">promptUpdate</c:param>
</c:url>

<c:url value="/LinearCode.do" var="processUpdate">
	<c:param name="method">processUpdate</c:param>
</c:url>

<c:url value="/LinearCode.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/CodeManager.do" var="codeManager"/>

<form method="post" action="${list}">
<div class="command">
<input type="submit" value="<fmt:message key="List"/>">
<input type="submit" value="<fmt:message key="Add"/>" onclick="docommit('${promptAdd}')">
<input type="submit" value="<fmt:message key="Edit"/>" onclick="docommit('${promptUpdate}')">
<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDelete}')">
<input type="submit" value="<fmt:message key="CodeManager"/>" onclick="docommit('${codeManager}')">
</div>

<div>
<fmt:message key="LinearCode"/>
<table>
<tr>
<td><fmt:message key="ID"/></td>
<td><fmt:message key="Name"/></td>
</tr>

<c:forEach var="item" items="${linearTypes}" varStatus="status">
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