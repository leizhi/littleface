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

<c:url value="/Activity.do" var="listActivity"/>

<c:url value="/Activity.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/Activity.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/Activity.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/Activity.do" var="processDownload">
	<c:param name="method">processDownload</c:param>
</c:url>

<form method="post" action="${listActivity}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<div id="container">

<div>
<span><strong>List Forum</strong></span>
</div>

<c:forEach var="item" items="${forums}" varStatus="status">
<%-- 
<c:if test="${!empty item.threadTypes && fun:length(item.threadTypes) >0 }">
--%>
<table>

<thead>
<tr>
<th><c:out value="${item.name }"/></th>
<th><fmt:message key="Topics"/></th>
<th><fmt:message key="Posts"/></th>
<th><fmt:message key="LastPost"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="itemf" items="${item.threadTypes }" varStatus="statusf">
<tr>
<c:url value="/ForumThread.do" var="listThread">
	<c:param name="method">listThread</c:param>
	<c:param name="threadTypeId">${itemf.id }</c:param>
</c:url>

<td><a href="${listThread }"><c:out value="${itemf.name }"/></a></td>
<td><c:out value="${itemf.extension.topics }"/></td>
<td><c:out value="${itemf.extension.posts}"/></td>
<td><c:out value="${itemf.name }"/> </td>
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
<%-- 
</c:if>
--%>
</c:forEach>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>