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

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="Search Account" name="title"/>
</jsp:include>

<div>

<c:url value="/ForumThread.do" var="promptCreateThread">
	<c:param name="method">promptCreateThread</c:param>
</c:url>


<a href="${promptCreateThread }">CreateThread</a>
</div>

<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="List Forum" name="title"/>
</jsp:include>
<table border="0" cellspacing="0" cellpadding="1" width="100%" bgcolor=#fcfdfe id=small>
<tr>
<td><fmt:message key="Title"/></td>
<td><fmt:message key="Author"/></td>
<td><fmt:message key="Reply"/></td>
<td><fmt:message key="View"/></td>
<td><fmt:message key="LastVisit"/></td>
</tr>

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
<td><c:out value="${item.replyPrivateUser.name }"/> <fmt:formatDate value="${item.modifiedDate }" pattern="yyyy/MM/dd hh:mm:ss"/></td>
</tr>
</c:forEach>

</table>


<%--
<c:forEach var="item" items="${forums}" varStatus="status">

<div style="float: left;width: 100%;text-align: left;background-color: #fff;color: #000000;font-size: 12px;border: 1px solid #ffffff;">

<div style="width: 100%;height:20px;background-color: #649caa;">
<span><c:out value="${item.name }"></c:out></span>
</div>

<table border="0" cellspacing="0" cellpadding="1" width="100%" bgcolor=#ffffff id=small>
<tr>
<td><fmt:message key="Title"/></td>
<td><fmt:message key="Author"/></td>
<td><fmt:message key="Reply"/></td>
<td><fmt:message key="View"/></td>
<td><fmt:message key="LastVisit"/></td>
</tr>

<tr>
<td>Title</td>
<td>Author</td>
<td>Reply</td>
<td>View</td>
<td>LastVisit</td>
</tr>
</table>

</div>
</c:forEach>
 --%>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />

</form>
</fmt:bundle>
</body>
</html>