<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="jsp/js/util.js"></script>

<script type="text/javascript">
function refreshParent(){
	window.opener.location.href = window.opener.location.href;
	if (window.opener.progressWindow){
		window.opener.progressWindow.close();
	}
	window.close();
} 
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/My.do" var="processUploadImages">
	<c:param name="method">processUploadImages</c:param>
</c:url>

<form method="post">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<div id="container">
<div><fmt:message key="UploadImages"/></div>

Upload Images Success
<div>
	<input type="button" value="Close" onclick="refreshParent();"/>
</div>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>