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
     	<li class="AddOutput"><fmt:message key="Code" /></li>
	<li class="AddInput"><input type="text" id="Code" name="Code" property="Code" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><fmt:message key="Description" /></li>
        <li class="AddInput"><input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/> </li>
     	<li class="Button"><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/AccountCategory.do?state=processUpdate');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/AccountCategory.do?state=processUpdate');"/></li>
      </ul>
    </div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
