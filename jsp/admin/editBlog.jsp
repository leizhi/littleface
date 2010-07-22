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
<form id="default" name="default" method="Post" action="<%=request.getContextPath()%>/Blog.do" >
<%@ include file="/jsp/incl/adminHeader.jsp" %>
<!-- UpdateTable -->
    <div class="SearchTable" >
      <ul>
	<input type="hidden" id="ID" name="ID" property="ID"/>
     	<li class="AddOutput"><fmt:message key="Title" /></li>
	<li class="AddInput"><input type="text" id="Title" name="Title" property="Title" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="User" /></li>
	<li class="AddInput">	<input type="text" id="User" name="User" property="User" size="15" maxlength="255" onkeyup="Util.AutoComlete('User','BlogTypeD')" /></li>

     	<li class="AddOutput"><fmt:message key="BlogType" /></li>
	<li class="AddInput"><div   id="BlogTypeD"></div>
	<input type="text" id="BlogType" name="BlogType" property="BlogType" size="15" maxlength="255" onkeyup="Util.AutoComlete('BlogType','BlogTypeD')"/></li>

     	<li class="AddOutput"><fmt:message key="BlogDate" /></li>
	<li class="AddInput"><input type="text" id="BlogDate" name="BlogDate" property="BlogDate" size="15" maxlength="255"/><a href="javascript:Util.Calendar('BlogDate')"><img src="jsp/etc/images/miniDate.gif" border=0 alt="<fmt:message key="ChooseDate"/>"></a></li>

     	<li class="AddOutput"><fmt:message key="Description" /></li>
        <li class="AddInput"><input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/> </li>

<%-- <textarea name="NAME" cols=15 rows=4></textarea> --%>
     	<li class="Button"><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/Blog.do?state=processUpdateAdminBlog');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/Blog.do?state=processUpdateAdminBlog');"/></li>
      </ul>
    </div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
