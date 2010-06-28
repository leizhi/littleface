<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<html>

<frameset cols="*" frameborder="0" framespacing="0" border="0">
<frame name="main" src=<%=request.getContextPath()%>/jsp/default.jsp>
</frameset>
<html:menulist property="BlogCategory"/>
</html>
