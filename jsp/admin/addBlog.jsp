<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/etc/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="jsp/etc/styles/default.css" />
<script type="text/javascript" src="jsp/etc/js/util.js"></script>

<%-- 
<script type="text/javascript"  language="javascript" charset="utf-8">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form id="default" name="default" method="Post" action="<%=request.getContextPath()%>/Blog.do" >
<script type="text/javascript" src="jsp/etc/js/calendar.js"></script>

<%@ include file="/jsp/etc/adminHeader.jsp" %>
<!-- Add Blog -->
    <div class="SearchTable" >
      <ul>
     	<li class="AddOutput"><html:message key="Title" /></li>
	<li class="AddInput"><html:input type="text" id="Title" name="Title" property="Title" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="User" /></li>
	<li class="AddInput"><%-- <div   id="UserD"></div> --%>
	<html:input type="text" id="User" name="User" property="User" size="15" maxlength="255" onkeyup="Util.AutoComlete('User','BlogTypeD')" />
	<div   id="flag"><!--this   is   the   autocomplete   container.--></div>

     	<li class="AddOutput"><html:message key="BlogType" /></li>
	<li class="AddInput"><div   id="BlogTypeD"></div>
	<html:input type="text" id="BlogType" name="BlogType" property="BlogType" size="15" maxlength="255" onkeyup="Util.AutoComlete('BlogType','BlogTypeD')"/></li>

     	<li class="AddOutput"><html:message key="BlogDate" /></li>
	<li class="AddInput"><html:input type="text" id="BlogDate" name="BlogDate" property="BlogDate" size="15" maxlength="255"/><a href="javascript:Util.Calendar('BlogDate')"><img src="jsp/etc/images/miniDate.gif" border=0 alt="<html:message key="ChooseDate"/>"></a></li>

     	<li class="AddOutput"><html:message key="Description" /></li>
        <li class="AddInput"><html:input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/></li>

     	<li class="Button"><input type="button" id="Add" name="Add" value="Add" onclick="Util.Submit('/Blog.do?state=processNewAdminBlog');"/><input type="reset" id="reset" name="reset" value="reset"/></li>
      </ul>
    </div>
<%@ include file="/jsp/etc/winFooter.jsp" %>
</form>
</body>
</html>
