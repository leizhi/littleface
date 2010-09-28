<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<c:url value="/Login.do" var="processLogin">
	<c:param name="method">processLogin</c:param>
</c:url>
<form method="post" action="${processLogin}">
<%--
	<input type="text" name="name">
	<input type="password" name="password">
	 --%>
<input name="id" type="hidden" value="${id}"/>
age:<input name="age" type="text"/><br/>
name:<input name="name" type="text"/><br/>
school:<input name="school" type="text"/><br/>
<input type="submit">
</form>
</body>
</html>