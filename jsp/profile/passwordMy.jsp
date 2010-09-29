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

<div style="width: 100%;">
<div style="float: left;width: 50%;text-align: right;"><fmt:message key="OldPassword"/></div>
<div style="float: left;width: 49%;text-align: left;"><input type="password"/></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 50%;text-align: right;"><fmt:message key="NewPassword"/></div>
<div style="float: left;width: 49%;text-align: left;"><input type="password"/></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 50%;text-align: right;"><fmt:message key="DoublePassword"/></div>
<div style="float: left;width: 49%;text-align: left;"><input type="password"/></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;text-align: center;">
<input type="submit" value="submit"/>
</div>

<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />
</form>
</fmt:bundle>
</body>
</html>