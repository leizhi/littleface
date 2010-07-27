<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="MessageBundle">

<html>
<head>
<title><fmt:message key="Menu"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
</head>
<body class="mbody">
<div>
online:<%=com.mooo.mycoz.util.SessionCounter.getRealCount()%>

<c:url value="/Login.do" var="processLogout">
	<c:param name="method">processLogout</c:param>
</c:url>

<ul>
<li><a href="<c:url value="/File.do"/>" target="main"><fmt:message key="File"/></a></li>
<li><a href="<c:url value="/Setup.do"/>" target="main"><fmt:message key="Setup"/></a></li>
<li><a href="<c:url value="/System.do"/>" target="main"><fmt:message key="System"/></a></li>
<li><a href="${processLogout}" target="_top"><fmt:message key="Logout"></fmt:message></a></li>
</ul>
</div>

</body>
</html>
</fmt:bundle>