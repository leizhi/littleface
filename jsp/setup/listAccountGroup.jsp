<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="File"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/File.do" var="listFile">
	<c:param name="method">list</c:param>
</c:url>

<form method="post" action="${listFile}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<div id="container">
<div style="height: 100%;width: 100%;margin-top: 5%;">
	<div style="width: 60%;height: 16%;border: 1px solid green;margin: 0 auto;text-align: left;">
	    <p>Setup Account.......</p>
	</div>
</div>
<jsp:include page="../incl/g_footer.jsp" />
</div>
</form>
</fmt:bundle>
</body>
</html>