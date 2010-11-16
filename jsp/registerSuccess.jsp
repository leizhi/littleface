<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:bundle basename="MessageBundle">
<html>
<head>
<title><fmt:message key="Login"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
</head>

<body>

<c:url value="/Login.do" var="promptLogin">
	<c:param name="method">promptLogin</c:param>
</c:url>

<form>
<div style="position:absolute;top:50%;left:50%;width:500px;margin-top:-150px; margin-left:-250px;
border:1px #999 solid; background:#CEE7FF;">

<div style="width: 100%;height:20px;background:#649caa;text-align: center;">
<fmt:message key="RegisterSuccess"/>
</div>

<div style="width: 100%;text-align: center;">
<a href="${promptLogin }"><fmt:message key="Login"/></a>
</div>

</div>
</form>

</body>
</html>
</fmt:bundle>
