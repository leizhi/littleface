<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="File"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
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
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="Search File" name="title"/>
</jsp:include>

	<table align="center">
	<tr>
		<td>索引名称</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
		<td>类型</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
	</tr>
	
	<tr>
		<td>索引名称</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
		<td>类型</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
	</tr>
	
	<tr >
		<td colspan="4">
		<span>
				<input type="submit" value="<fmt:message key="List"/>">
				<input type="submit" value="<fmt:message key="Upload"/>" onclick="docommit('${promptUpload}')">
				<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDelete}')">
				<input type="submit" value="<fmt:message key="Download"/>" onclick="docommit('${processDownload}')">
				<input type="submit" value="<fmt:message key="Browse"/>" onclick="docommit('${processDownload}')">
		</span>
		 </td>
	</tr>
	</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="List File" name="title"/>
</jsp:include>

<table border="0" cellspacing="0" cellpadding="1" width="100%" bgcolor=#ffffff id=small>
<tr bgcolor="#649caa">
<td><fmt:message key="ID"/></td>
<td><fmt:message key="Type"/></td>
<td><fmt:message key="Name"/></td>
<td><fmt:message key="Size"/></td>
<td><fmt:message key="Download"/></td>
<td><fmt:message key="Date"/></td>
</tr>

<c:forEach var="item" items="${files}" varStatus="status">
<tr>
<td><input type="checkbox" name="id" value="${item.id}"> </td>
<td><c:out value="${item.typename}"></c:out></td>
<td><c:out value="${item.name}"></c:out></td>
<td><c:out value="${item.size}"></c:out></td>
<td><a href="upload/${item.filepath}"><img src="jsp/images/down.gif" border=0 alt=""/></a> </td>
<td><c:out value="${item.datetime}"></c:out></td>
</tr>
</c:forEach>
</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />

</form>
</fmt:bundle>
</body>
</html>