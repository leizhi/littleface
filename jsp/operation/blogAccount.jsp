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
color:#ffffff;
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

<div id="container">

<div style="text-align: left;">
<c:url value="/ForumThread.do" var="promptCreateThread">
	<c:param name="method">promptCreateThread</c:param>
</c:url>
<span><a href="${promptCreateThread }">CreateThread</a></span>
</div>

<table>
<caption>List Forum</caption>

<thead>
<tr>
<th><fmt:message key="Title"/></th>
<th><fmt:message key="Author"/></th>
<th><fmt:message key="Reply"/></th>
<th><fmt:message key="View"/></th>
<th><fmt:message key="LastVisit"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="item" items="${forumThreads}" varStatus="status">
<tr>
<c:url value="/ForumThread.do" var="listMessage">
	<c:param name="method">listMessage</c:param>
	<c:param name="threadId">${item.id }</c:param>
</c:url>

<td><a href="${listMessage }"><c:out value="${item.subject }"/></a></td>
<td><c:out value="${item.user.name }"/></td>
<td><c:out value="${item.reply }"/></td>
<td><fmt:formatDate value="${item.creationDate }" pattern="yyyy/MM/dd hh:mm:ss"/> </td>
<td><fmt:formatDate value="${item.modifiedDate }" pattern="yyyy/MM/dd hh:mm:ss"/> By <c:out value="${item.replyPrivateUser.name }"/> </td>
</tr>
</c:forEach>
</tbody>

<tfoot>
<tr>
<!-- 分页 -->
<td colspan="5" style="width: 600px;">
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