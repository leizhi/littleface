<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/etc/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="jsp/etc/styles/default.css" />
<script type="text/javascript" src="jsp/etc/js/util.js"></script>
<%-- 
<<script type="text/javascript"  language="javascript" charset="utf-8">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form name="default" Method="Post" action="">
<script type="text/javascript" src="jsp/etc/js/calendar.js"></script>

<%@ include file="/jsp/etc/adminHeader.jsp" %>
<!-- UpdateTable -->
    <div class="SearchTable" >
      <ul>
	<html:input type="hidden" id="ID" name="ID" property="ID"/>
     	<li class="AddOutput"><html:message key="UserName" /></li>
	<li class="AddInput"><html:input type="text" id="UserName" name="UserName" property="UserName" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Password" /></li>
	<li class="AddInput"><html:input type="password" id="Password" name="Password" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Password" /></li>
        <li class="AddInput"><html:input type="password" id="Passwordd" name="Passwordd" property="Passwordd" size="15" maxlength="255"/></li>
 
     	<li class="AddOutput"><html:message key="Country" /></li>
        <li class="AddInput"><html:select id="Country" name="Country" property="Country"/></li>

     	<li class="AddOutput"><html:message key="City" /></li>
        <li class="AddInput"><html:input type="text" id="City" name="City" property="City" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Address" /></li>
        <li class="AddInput"><html:input type="text" id="Address" name="Address" property="Address" size="30" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Email" /></li>
        <li class="AddInput"><html:input type="text" id="Email" name="Email" property="Email" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Tel" /></li>
        <li class="AddInput"><html:input type="text" id="Tel" name="Tel" property="Tel" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Zip" /></li>
        <li class="AddInput"><html:input type="text" id="Zip" name="Zip" property="Zip" size="15" maxlength="255"/></li>

    	<li class="AddOutput"><html:message key="Language" /></li>
        <li class="AddInput"><html:select id="Language" name="Language" property="Language"/></li>

     	<li class="Button"><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/OperatorUser.do?state=processUpdate');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/OperatorUser.do?state=processUpdate');"/></li>
      </ul>
    </div>
<%@ include file="/jsp/etc/winFooter.jsp" %>
</form>
</body>
</html>
