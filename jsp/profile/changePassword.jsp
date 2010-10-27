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


<c:url value="/My.do" var="processChangePassword">
	<c:param name="method">processChangePassword</c:param>
</c:url>

<form method="post" action="${processChangePassword}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<div id="container">

<div style="width: 100%;">
<div style="float: left;width: 50%;text-align: right;"><fmt:message key="OldPassword"/></div>
<div style="float: left;width: 49%;text-align: left;"><input name="oldPassword" type="password"/></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 50%;text-align: right;"><fmt:message key="NewPassword"/></div>
<div style="float: left;width: 49%;text-align: left;"><input name="newPassword" type="password"/></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 50%;text-align: right;"><fmt:message key="DoublePassword"/></div>
<div style="float: left;width: 49%;text-align: left;"><input name="doublePassword" type="password"/></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;text-align: center;">
<input type="submit" value="change"/>
</div>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>