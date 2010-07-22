<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="jsp/styles/default.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>
<%-- 
<<script type="text/javascript"  language="javascript" charset="utf-8">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form name="default" Method="Post" action="">
<script type="text/javascript" src="jsp/js/calendar.js"></script>

<%@ include file="/jsp/incl/adminHeader.jsp" %>
<!-- UpdateTable -->
    <div class="SearchTable" >
      <ul>
	<input type="hidden" id="ID" name="ID" property="ID"/>
     	<li class="AddOutput"><fmt:message key="UserName" /></li>
	<li class="AddInput"><input type="text" id="UserName" name="UserName" property="UserName" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Password" /></li>
	<li class="AddInput"><input type="password" id="Password" name="Password" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Password" /></li>
        <li class="AddInput"><input type="password" id="Passwordd" name="Passwordd" property="Passwordd" size="15" maxlength="255"/></li>
 
     	<li class="AddOutput"><fmt:message key="Country" /></li>
        <li class="AddInput"><html:select id="Country" name="Country" property="Country"/></li>

     	<li class="AddOutput"><fmt:message key="City" /></li>
        <li class="AddInput"><input type="text" id="City" name="City" property="City" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Address" /></li>
        <li class="AddInput"><input type="text" id="Address" name="Address" property="Address" size="30" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Email" /></li>
        <li class="AddInput"><input type="text" id="Email" name="Email" property="Email" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Tel" /></li>
        <li class="AddInput"><input type="text" id="Tel" name="Tel" property="Tel" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Zip" /></li>
        <li class="AddInput"><input type="text" id="Zip" name="Zip" property="Zip" size="15" maxlength="255"/></li>

    	<li class="AddOutput"><fmt:message key="Language" /></li>
        <li class="AddInput"><html:select id="Language" name="Language" property="Language"/></li>

     	<li class="Button"><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/OperatorUser.do?state=processUpdate');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/OperatorUser.do?state=processUpdate');"/></li>
      </ul>
    </div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
