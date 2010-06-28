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
     	<li class="AddOutput"><html:message key="Code" /></li>
	<li class="AddInput"><html:input type="text" id="Code" name="Code" property="Code" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Name" /></li>
	<li class="AddInput"><html:input type="text" id="Name" name="Name" property="Name" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="Description" /></li>
        <li class="AddInput"><html:input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/> </li>
     	<li class="Button"><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/Language.do?state=processUpdate');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/Language.do?state=processUpdate');"/></li>
      </ul>
    </div>
<%@ include file="/jsp/etc/winFooter.jsp" %>
</form>
</body>
</html>
