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

<c:url value="/CodeType.do" var="list"/>

<c:url value="/CodeType.do" var="promptAdd">
	<c:param name="method">promptAdd</c:param>
</c:url>

<c:url value="/CodeType.do" var="processAdd">
	<c:param name="method">processAdd</c:param>
</c:url>

<c:url value="/CodeType.do" var="promptUpdate">
	<c:param name="method">promptUpdate</c:param>
</c:url>

<c:url value="/CodeType.do" var="processUpdate">
	<c:param name="method">processUpdate</c:param>
</c:url>

<c:url value="/CodeType.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/CodeType.do" var="listCode">
	<c:param name="method">listCode</c:param>
</c:url>

<form method="post" action="${list}">
<div class="command">
<input type="submit" value="<fmt:message key="List"/>">
<input type="submit" value="<fmt:message key="Add"/>" onclick="docommit('${promptAdd}')">
<input type="submit" value="<fmt:message key="Edit"/>" onclick="docommit('${promptUpdate}')">
<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDelete}')">
<input type="submit" value="<fmt:message key="CodeManager"/>" onclick="docommit('${listCode}')">
</div>

<div>
			<select name="codeCategory">
				<c:forEach var="items" items="${codeCategory}" varStatus="s">
					<option value="${items}"

					<c:if test="${items==param.codeCategory}">
						selected="selected"
					</c:if>
						>
					<fmt:message key="${items}"/>
					</option>
				</c:forEach>
			</select>
</div>
<div>
<fmt:message key="CodeType"/>
<table>
<tr>
<td><fmt:message key="ID"/></td>
<td><fmt:message key="Name"/></td>
<td><fmt:message key="Category"/></td>
</tr>

<c:forEach var="item" items="${linearTypes}" varStatus="status">
<tr>
<td><input type="checkbox" name="id" value="${item.id}"> </td>
<td><c:out value="${item.name}"></c:out></td>
<td><fmt:message key="${item.category}"/></td>
</tr>
</c:forEach>
</table>
</div>

</form>
</fmt:bundle>
</body>
</html>