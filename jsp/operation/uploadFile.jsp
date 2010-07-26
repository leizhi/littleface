<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="MessageBundle">

<html>
<head>
<title><fmt:message key="UploadFile"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
</head>

<body>
<c:url value="/File.do" var="file"/>
<c:url value="/File.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<form method="post" action="${processUpload}">
<div class="command">
<input type="submit" value="Black" onclick="docommit('${file}')">
<input type="submit" value="OK"">
</div>

<div>
<fmt:message key="UploadFile"/>

</div>

</form>

</body>
</html>
</fmt:bundle>