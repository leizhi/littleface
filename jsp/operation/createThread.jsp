<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<fmt:bundle basename="MessageBundle">
<c:url value="/ForumThread.do" var="processCreateThread">
	<c:param name="method">processCreateThread</c:param>
</c:url>

<form method="post" action="${processCreateThread}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>
<div id="container">

<div>
<span>Create Thread</span>

<input type="hidden" name="forumThread.id" value="${forumThread.id }"/>

<div style="width: 60%;text-align: center;align:center;">

<table border="0" cellspacing="0" cellpadding="1" width="100%" bgcolor="#ffffff" align="center">
<tr>
<td align="right" width="40%"><fmt:message key="Subject"/></td>
<td><input type="text" name="forumThread.subject"/></td>
</tr>

<tr>
<td align="right" width="40%"><fmt:message key="Body"/></td>
<td><textarea rows="8" cols="40" name="forumThread.body"></textarea></td>
</tr>

<tr>
<td colspan="2" align="center">
	<input type="submit" value="<fmt:message key="Deliver"/>" />
</td>
</tr>
</table>

</div>

</div>
<jsp:include page="../incl/g_footer.jsp" />
</div>
</form>
</fmt:bundle>
</body>
</html>