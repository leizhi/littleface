<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
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

<form method="post" action="${listAccount}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>


<input type="hidden" name="threadId" value="${threadId }"/>

<div style="width: 100%;background: #edf0f9;">

<div style="float: left;width: 20%;background-color: #e3e9ff;height:auto !important; height:20px; min-height:20px;">
${forumThread.user.name }
</div>

<div style="float: right;width: 80%;background-color: #e3e9ff;height:auto !important; height:20px; min-height:20px;">
${forumThread.subject }  <fmt:formatDate value="${forumThread.creationDate }" pattern="yyyy/MM/dd hh:mm:ss"/>
</div>
<div style="clear: both;"></div>

<div style="float: left;width: 20%;height:auto !important; height:150px; min-height:150px;">
${forumThread.user.name }
</div>

<div style="float: right;width: 80%;height:auto !important; height:150px; min-height:150px;">
<textarea rows="8" cols="40" readonly="readonly">${forumThread.body }</textarea>
</div>
<div style="clear: both;"></div>

</div>

<c:forEach var="item" items="${messages}" varStatus="status">
<div style="width: 100%;background: #edf0f9;">

<div style="float: left;width: 20%;background-color: #e3e9ff;height:auto !important; height:20px; min-height:20px;">
${item.user.name }
</div>

<div style="float: right;width: 80%;background-color: #e3e9ff;height:auto !important; height:20px; min-height:20px;">
<c:out value="${item.subject }"/>
<fmt:formatDate value="${item.creationDate }" pattern="yyyy/MM/dd hh:mm:ss"/>
<a href=""><fmt:message key="Reply"/></a>
</div>

<div style="clear: both;"></div>

<div style="float: left;width: 20%;height:auto !important; height:150px; min-height:150px;">
${item.user.name }
</div>

<div style="float: right;width: 80%;height:auto !important; height:150px; min-height:150px;">
<textarea rows="8" cols="40" readonly="readonly">${item.body }</textarea>
</div>
<div style="clear: both;"></div>

</div>

</c:forEach>
 
<div style="width: 100%;height:20px;background-color: #99b1cc;"></div>

<div style="float: left;width: 20%;text-align: right;">
<fmt:message key="Subject"/>
</div>

<div style="float: right;width: 80%;">
<input type="text" name="message.subject"/>
</div>
<div style="clear: both;"></div>

<div style="float: left;width: 20%;text-align: right;">
<fmt:message key="Body"/>
</div>

<div style="float: right;width: 80%;">
<textarea rows="8" cols="40" name="message.body"></textarea>
</div>
<div style="clear: both;"></div>

<c:url value="/ForumThread.do" var="createMessage">
	<c:param name="method">createMessage</c:param>
</c:url>
<div style="width: 100%;margin-left: 20%;">
	<input type="submit" value="<fmt:message key="Deliver"/>" onclick="docommit('${createMessage}');return false;">
</div>

<jsp:include page="../incl/g_footer.jsp" />

</form>
</fmt:bundle>
</body>
</html>