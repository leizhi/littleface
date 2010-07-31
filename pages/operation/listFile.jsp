<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="File"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/File.do" var="listFile"/>

<c:url value="/File.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/File.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/File.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/File.do" var="processDownload">
	<c:param name="method">processDownload</c:param>
</c:url>

<form method="post" action="${listFile}">
<div class="command">
<input type="submit" value="<fmt:message key="List"/>">
<input type="submit" value="<fmt:message key="Upload"/>" onclick="docommit('${promptUpload}')">
<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDelete}')">
<input type="submit" value="<fmt:message key="Download"/>" onclick="docommit('${processDownload}')">
<input type="submit" value="<fmt:message key="Browse"/>" onclick="docommit('${processDownload}')">
</div>

<div>
<fmt:message key="File"/>
<table>
<tr>
<td><fmt:message key="ID"/></td>
<td><fmt:message key="Name"/></td>
<td><fmt:message key="Download"/></td>
<td><fmt:message key="Image"/></td>
<td><fmt:message key="Date"/></td>
</tr>

<c:forEach var="item" items="${files}" varStatus="status">
<tr>
<td><input type="checkbox" name="id" value="${item.id}"> </td>
<td><c:out value="${item.name}"></c:out></td>
<td><a href="upload/${item.downloadpath}"><img src="jsp/images/down.gif" border=0 alt=""/></a> </td>
<td><a href="upload/${item.imagepath}"><img src="jsp/images/down.gif" border=0 alt=""/></a> </td>
<td><c:out value="${item.date}"></c:out></td>
</tr>
</c:forEach>
</table>
</div>

</form>
</fmt:bundle>
</body>
</html>