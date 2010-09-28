<%@page contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<html>
<head>

<title>language choose</title>

</head>

<body bgcolor="#ffffff">

copyright @ lizongbo @ donews.net

<c:if test="${param['locale'] != null}">

	<fmt:setLocale value="${param['locale']}" scope="session" />

	<fmt:setTimeZone value="${param['locale']}" scope="session" />

</c:if>

<c:if test="${param['locale'] == null}">

	<fmt:setLocale value="${header['locale']}" scope="session" />

	<fmt:setTimeZone value="${header['locale']}" scope="session" />

</c:if>

<%
	Locale crtl = Locale.getDefault();
	Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");

	if (cobj != null && cobj instanceof Locale) {
		crtl = (Locale) cobj;
	}

	Locale[] la = java.text.NumberFormat.getAvailableLocales();
%>

<form method="POST" action="">language choose: <br />

<select name="locale">

	<%
		for (int i = 0; i < la.length; i++) {
	%>

	<option value="<%=la[i]%>"
		<%if (la[i].equals(crtl)) {
			out.print("selected=\"selected\"");
				}%>><%=la[i].getDisplayName(crtl)%></option>
	<%
		}
	%>

</select> <br/>
	<fmt:message key="Login"/>

<fmt:bundle basename="MessageBundle">
	<fmt:message key="Login"/>
	<c:out value="Login"></c:out>
</fmt:bundle>
<%-- 
<fmt:setBundle basename="MessageBundle" var="messageBundle" scope="page"/>
<fmt:message key="Login" bundle="${messageBundle}"></fmt:message>
--%>

<input type="submit" value="change" />
</form>

</body>

</html>