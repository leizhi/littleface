<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${!empty param.locale}">
	<fmt:setLocale value="${param.locale}" scope="session" />
	<fmt:setTimeZone value="${param.locale}" scope="session" />
</c:if>

<c:if test="${empty param.locale}">
	<fmt:setLocale value="en_US" scope="session" />
	<fmt:setTimeZone value="en_US" scope="session" />
</c:if>
<%
Locale locale = Locale.getDefault();
Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
if (cobj != null && cobj instanceof Locale) {
	locale = (Locale) cobj;
}

Locale.setDefault(locale);
%>
<fmt:bundle basename="MessageBundle">
<html>
<head>
<title><fmt:message key="Login"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link rel="stylesheet" type="text/css" href="skin/office/default/presentation.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
</head>

<body>

<c:url value="/Login.do" var="processLogin">
	<c:param name="method">processLogin</c:param>
</c:url>

<c:url value="/Login.do" var="promptLogin">
	<c:param name="method">promptLogin</c:param>
</c:url>

<c:url value="/Login.do" var="promptRegister">
	<c:param name="method">promptRegister</c:param>
</c:url>

<form method="post" action="${processLogin}">
<div style="position:absolute;top:50%;left:50%;width:500px;height:200px; margin-top:-150px; margin-left:-250px;
border:1px #999 solid; background:#c3d9ff;">

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;">
	<a href="${processLogin}">
		<img width="80px" height="40px" src="jsp/images/xpcLogo.gif" alt="Go to main page." border=0 />
	</a>
</div>
<div style="float: left;width: 59%;text-align: left; margin-top: 20px;">
			<select name="locale" onchange="docommit('${promptLogin}')">
				<c:forEach var="items" items="${locales}" varStatus="s">
					<option value="${items}"

					<c:if test="${empty param.locale and items=='en_US'}">
						selected="selected"
					</c:if>
					<c:if test="${!empty param.locale and items==param.locale}">
						selected="selected"
					</c:if>
						>
					${items.displayName}
					</option>
				--</c:forEach>
			</select>
</div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="UserName"/>:</div>
<div style="float: left;width: 59%;text-align: left;"><input type="text" name="user.name"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;">
<div style="float: left;width: 40%;text-align: right;"><fmt:message key="Password"/>:</div>
<div style="float: left;width: 59%;text-align: left;"><input type="password" name="user.password"></div>
<div style="clear: both;"></div>
</div>

<div style="width: 100%;text-align: center;">
<input type="submit" value="<fmt:message key="Login"/>">
<input type="submit" value="<fmt:message key="Register"/>" onclick="docommit('${promptRegister}');return false;">
</div>

</div>
</form>

</body>
</html>
</fmt:bundle>
