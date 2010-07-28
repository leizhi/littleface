<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${!empty param.locale}">
	<fmt:setLocale value="${param.locale}" scope="session" />
	<fmt:setTimeZone value="${param.locale}" scope="session" />
</c:if>

<c:if test="${empty param.locale}">
	<fmt:setLocale value="${header.locale}" scope="session" />
	<fmt:setTimeZone value="${header.locale}" scope="session" />
</c:if>

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

<div id="bodyblock">
<form method="post" action="${processLogin}">
<div id="login">
	<table align="center">
		<tbody>
			<tr><td align="left" colspan="2">
			<a href="${processLogin}">
				<img src="jsp/images/xpcLogo.gif" alt="Go to main page." border=0 />
			</a>

			<select name="locale" onchange="docommit('${promptLogin}')">
				<c:forEach var="items" items="${locales}" varStatus="s">
					<option value="${items}"
					<c:if test="${!empty param.locale && items==param.locale}">
						selected="selected"
					</c:if>
					<c:if test="${empty param.locale && items==header.locale}">
						selected="selected"
					</c:if>
						>
					${items.displayName}
					</option>
				</c:forEach>
			</select>
			
			</td>
			</tr>
		
			<tr>
  				<td><br><b><fmt:message key="UserName"/>:</b></td>
 				<td><br><input type="text" name="User.name"></td>
			</tr>

			<tr>
  				<td><b><fmt:message key="Password"/>:</b></td>
  				<td><input type="password" name="User.password"></td>
			</tr>
			
			<tr><td align="right" colspan="2"> <input type="submit" value="<fmt:message key="Login"/>"></td></tr>
			<tr><td align="center" colspan="2"><font size=-2 color=#999999>Copyright&copy;2010 mooo.com </font></tr>
			
		</tbody>
	</table>
</div>
</form>
</div>

</body>
</html>
</fmt:bundle>