<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="../etc/styles/default.css" />
<script type="text/javascript" src="../etc/js/util.js"></script>
<%-- 
<script type="text/javascript"  language="javascript" charset="utf-8">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form id="default" name="default" method="Post" action="<%=request.getContextPath()%>/Blog.do" >
<script type="text/javascript" src="../etc/js/calendar.js"></script>
<%@ include file="/jsp/incl/loginHeader.jsp" %>

<!-- SearchTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="Output"><fmt:message key="Title" /></li>
	<li class="Input"><input type="text" id="Title" name="Title" property="Title" size="15" maxlength="255"/></li>
     	<li class="Output"><fmt:message key="User" /></li>
	<li class="Input"><input type="text" id="User" name="User" property="User" size="15" maxlength="255"/></li>

     	<li class="Output"><fmt:message key="BlogType" /></li>
        <li class="Input"><input type="text" id="BlogType" name="BlogType" property="BlogType" size="15" maxlength="255"/></li>
     	<li class="Output"> <fmt:message key="BlogDate" /></li>
        <li class="Input"> <input type="text" id="BlogDate" name="BlogDate" property="BlogDate" size="15" maxlength="255"/> <a href="javascript:Util.Calendar('BlogDate')"><img src="../etc/images/miniDate.gif" border=0 alt="<fmt:message key="ChooseDate"/>"></a></li>
 
     	<li class="Button"><input type="button" id="Find" name="Find" value="Find" onclick="Util.Submit('/');"/><input type="button" id="Add" name="Add" value="Add" onclick="Util.Submit('/');"/><input type="reset" id="reset" name="reset" value="reset"/></li>
      </ul>
    </div>

<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
