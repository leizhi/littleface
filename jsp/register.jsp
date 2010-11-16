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

<c:url value="/Login.do" var="processRegister">
	<c:param name="method">processRegister</c:param>
</c:url>

<c:url value="/Login.do" var="promptRegister">
	<c:param name="method">promptRegister</c:param>
</c:url>

<form method="post" action="${processRegister}">
<div style="position:absolute;top:50%;left:50%;width:500px;margin-top:-150px; margin-left:-250px;
border:1px #999 solid; background:#CEE7FF;">

<div style="width: 100%;height:20px;background:#649caa;text-align: center;">
<fmt:message key="Register"/>
</div>

<input type="hidden""" name="user.id" value="${user.id}">

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="UserName"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="text" name="user.name" value="${user.name}"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Password"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="user.password" value="${user.password}"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Password"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="dpassword"></div>
<div style="clear: both;"></div>
</div>
<%--
<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Sex"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Height"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Weight"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Birthday"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Career"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Education"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Married"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="QQ"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Email"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Secret"/></div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="User.password"></div>
<div style="clear: both;"></div>
</div>
--%>
<div style="width: 100%;text-align: center;">
<input type="submit" value="<fmt:message key="Ok"/>">
<input type="submit" value="<fmt:message key="Black"/>" onclick="history.go(-1);return false;">
</div>

</div>
</form>

</body>
</html>
</fmt:bundle>
