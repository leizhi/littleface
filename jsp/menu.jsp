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

<c:url value="/User.do" var="security">
	<c:param name="bar">1</c:param>
	<c:param name="subbar">2</c:param>
</c:url>

<c:url value="/Transaction.do" var="transactionSetup">
	<c:param name="bar">1</c:param>
	<c:param name="subbar">2</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="Setup"/></li>
<!-- Accounting -->
<c:if test="${!empty Accounting && Accounting==true}">
<li><a href="${accounting}" target="main"><fmt:message key="Account"/></a></li>
</c:if>
<!-- Administrator -->
<c:if test="${!empty Security && Security==true}">
<li><a href="${security}" target="main"><fmt:message key="Security"/></a></li>
</c:if>

<!-- Transaction -->
<c:if test="${!empty Transaction && Transaction==true}">
<li><a href="${security}" target="main"><fmt:message key="Transaction"/></a></li>
</c:if>

</ul>
</c:if>

<!-- Warehouse -->
<c:if test="${!empty Warehouse && Warehouse==true}">

<c:url value="/Warehouse.do" var="warehouse">
	<c:param name="bar">0</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="Warehouse"/></li>

<li><a href="${warehouse}" target="main"><fmt:message key="Search"/></a></li>

</ul>
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
<!-- Search -->
<c:if test="${!empty Search && Search==true}">
<li><a href="${searchAccount}" target="main"><fmt:message key="Search"/></a></li>
</c:if>
<!-- Forum -->
<c:if test="${!empty Forum && Forum==true}">
<li><a href="${forumAccount}" target="main"><fmt:message key="Forum"/></a></li>
</c:if>

</ul>
</c:if>

<!-- Transaction -->
<c:if test="${!empty Transaction && Transaction==true}">

<c:url value="/Transaction.do" var="buyURL">
	<c:param name="method">buy</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<c:url value="/Transaction.do" var="saleURL">
	<c:param name="method">sale</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<c:url value="/Transaction.do" var="accountingURL">
	<c:param name="method">buy</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<c:url value="/Transaction.do" var="reportsURL">
	<c:param name="method">buy</c:param>
	<c:param name="bar">1</c:param>
	<c:param name="subbar">0</c:param>
</c:url>

<ul>
<li class="title"><fmt:message key="Transaction"/></li>

<!-- Buy -->
<c:if test="${!empty Buy && Buy==true}">
<li><a href="${buyURL}" target="main"><fmt:message key="Buy"/></a></li>
</c:if>

<!-- Sale -->
<c:if test="${!empty Sale && Sale==true}">
<li><a href="${saleURL}" target="main"><fmt:message key="Sale"/></a></li>
</c:if>

<!-- Accounting -->
<c:if test="${Accounting==true}">
<li><a href="${accountingURL}" target="main"><fmt:message key="Accounting"/></a></li>
</c:if>

<!-- Reports -->
<c:if test="${Reports==true}">
<li><a href="${reportsURL}" target="main"><fmt:message key="Reports"/></a></li>
</c:if>
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
<!-- Administrator -->
<c:if test="${!empty General && General==true}">
<li><a href="${generalMy}" target="main"><fmt:message key="General"/></a></li>
</c:if>

<li><a href="${promptChangePassword}" target="main"><fmt:message key="Password"/></a></li>

<li><a href="${processLogout}" target="_top"><fmt:message key="Logout"></fmt:message></a></li>
</ul>
</c:if>

</div>

</body>
</html>
</fmt:bundle>
