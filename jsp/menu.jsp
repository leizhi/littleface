<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
<fmt:bundle basename="MessageBundle" >
<html>
<head>
<title><fmt:message key="Menu"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
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

<!-- Setup -->
<c:if test="${!empty Setup && Setup==true}">

<c:url value="/Accounting.do" var="accounting">
	<c:param name="bar">1</c:param>
	<c:param name="subbar">2</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="Setup"/></li>
<li><a href="${accounting}" target="main"><fmt:message key="Account"/></a></li>
</ul>

<!-- Administrator -->
<c:if test="${!empty Admin && Admin==true}">

</c:if>

</c:if>

<!-- File -->
<c:if test="${!empty File && File==true}">

<c:url value="/File.do" var="treeFile">
	<c:param name="bar">0</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="File"/></li>
<li><a href="${treeFile}" target="main"><fmt:message key="File"/></a></li>
</ul>
</c:if>

<!-- Activity -->
<c:if test="${!empty Activity && Activity==true}">

<c:url value="/Activity.do" var="searchAccount">
	<c:param name="method">search</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<c:url value="/Activity.do" var="blogAccount">
	<c:param name="method">blog</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">1</c:param>
</c:url>

<c:url value="/Activity.do" var="forumAccount">
	<c:param name="method">forum</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">2</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="Activity"/></li>
<li><a href="${searchAccount}" target="main"><fmt:message key="Search"/></a></li>
<%-- <li><a href="${blogAccount}" target="main"><fmt:message key="Blog"/></a></li> --%>
<li><a href="${forumAccount}" target="main"><fmt:message key="Forum"/></a></li>
</ul>
</c:if>

<!-- Profile -->
<c:if test="${!empty Profile && Profile==true}">

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

<c:url value="/Login.do" var="processLogout">
	<c:param name="method">processLogout</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="Profile"/></li>
<li><a href="${generalMy}" target="main"><fmt:message key="General"/></a></li>
<li><a href="${promptChangePassword}" target="main"><fmt:message key="Password"/></a></li>
<li><a href="${processLogout}" target="_top"><fmt:message key="Logout"></fmt:message></a></li>
</ul>
</c:if>

</div>

</body>
</html>
</fmt:bundle>
