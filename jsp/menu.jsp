<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
<fmt:bundle basename="MessageBundle" >
<html>
<head>
<title><fmt:message key="Menu"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<style type="text/css">
ul {
	padding: 0;
	margin: 0 auto;
	border: 0px solid #000000;
	width: 100%;
	list-style: none;
}

li {
	padding: 0;
	border: 0px solid #000000;
	margin: 0 auto;
	width: 100%;
	text-align: left;
	background-color:  #fff;
}

.title {
	background-color: #dde5ff;
	color: #2b6ee7;
}
</style>
</head>
<body>
<div id="container">

<span>online:<%=com.mooo.mycoz.util.SessionCounter.getCount()%></span>

<c:url value="/Login.do" var="processLogout">
	<c:param name="method">processLogout</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="File"/></li>
<%-- <li><a href="<c:url value="/File.do?bar=0&subbar=0"/>" target="main"><fmt:message key="File"/></a></li> --%>
<li><a href="<c:url value="/File.do?bar=0&subbar=0"/>" target="main"><fmt:message key="File"/></a></li>
<li><a href="<c:url value="/CodeType.do?bar=0&subbar=1"/>" target="main"><fmt:message key="CodeType"/></a></li>
</ul>

<c:url value="/Account.do" var="searchAccount">
	<c:param name="method">search</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<c:url value="/Account.do" var="blogAccount">
	<c:param name="method">blog</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">1</c:param>
</c:url>
<ul>
<li class="title"><fmt:message key="Consort"/></li>
<li><a href="${searchAccount}" target="main"><fmt:message key="Search"/></a></li>
<li><a href="${blogAccount}" target="main"><fmt:message key="Blog"/></a></li>
</ul>

<c:url value="/My.do" var="generalMy">
	<c:param name="method">general</c:param>
	<c:param name="bar">2</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<c:url value="/My.do" var="promptChangePassword">
	<c:param name="method">promptChangePassword</c:param>
	<c:param name="bar">2</c:param>
	<c:param name="subbar">1</c:param>
</c:url>
<ul>
<li class="title"><fmt:message key="Account"/></li>
<li><a href="${generalMy}" target="main"><fmt:message key="General"/></a></li>
<li><a href="${promptChangePassword}" target="main"><fmt:message key="Password"/></a></li>
<li><a href="${processLogout}" target="_top"><fmt:message key="Logout"></fmt:message></a></li>
</ul>
</div>

</body>
</html>
</fmt:bundle>
