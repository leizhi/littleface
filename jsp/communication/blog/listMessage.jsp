<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="jsp/styles/default.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
<%-- 
<script type="text/javascript"  language="javascript" charset="utf-8">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form name="default" Method="Post" action="">
<%@ include file="/jsp/incl/loginHeader.jsp" %>
<div class="DivListMessage">
<ul>
<li class ="liTitle">
<html:output property="BlogUser"/>
<html:output property="BlogType"/>
<html:output property="BlogDate"/>
<html:output property="BlogTitle"/>
<html:output property="BlogDescription"/>
</li>

<%int i=0;%>
<logic:iterate property="MsgList">
<li class ="liMg">
<%=Input.getValue("MsgTitle"+i)%>
<%=Input.getValue("MsgUser"+i)%>
<%=Input.getValue("MessageDate"+i)%>
</li>
<li><p><%=Input.getValue("MsgDescription"+i)%></p></li>
<% i++; %>
</logic:iterate>

<li class ="liTitle"><fmt:message key="add.Title" />
<input type="hidden" id="BlogID" name="BlogID" property="BlogID"/></li>
<li><input type="text" id="AddTitle" name="AddTitle" size="15" maxlength="255" /></li>
<li><textarea id="Description" name="Description" cols="28" rows="4"></textarea></li>
<li><input type="button" value="new" onclick="Util.Submit('/Blog.do?state=processNewMessage');"/><input type="reset" id="reset" name="reset" value="reset"/></li>

</ul>
</div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
