<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
.title {
background-color:#464646;
color:blue;
}
li {
background-color:#c0c0c0;
color:blue;
}

</style>

<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/Account.do" var="listAccount"/>

<c:url value="/Account.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/Account.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/Account.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/Account.do" var="processDownload">
	<c:param name="method">processDownload</c:param>
</c:url>

<form method="post" action="${listAccount}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="Search Account" name="title"/>
</jsp:include>

	<table align="center">
	<tr>
		<td>年龄</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
		<td>性别</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
	</tr>
	
	<tr>
		<td>地区</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
		<td>名称</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
	</tr>
	
	<tr >
		<td colspan="4" align="center">
		<span>
				<input type="submit" value="<fmt:message key="List"/>">
		</span>
		 </td>
	</tr>
	</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="List Account" name="title"/>
</jsp:include>

<c:forEach var="item" items="${accounts}" varStatus="status">

</c:forEach>
 
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />

</form>
</fmt:bundle>
</body>
</html>