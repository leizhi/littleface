<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<html>

<frameset cols="*" frameborder="0" framespacing="0" border="0">
<frame name="main" src="/Menu.do">
</frameset>
</html>

<%=request.getAttribute("name") %>
${requestScope.name}