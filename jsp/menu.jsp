<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<h1>欢迎光临!</h1>
<fmt:setLocale value="zh_CN" scope="session" />
<fmt:bundle basename="MessageBundle">
<a href="<c:url value="/Login.do"/>"><fmt:message key="Login"></fmt:message></a>
</fmt:bundle>
</body>
</html>