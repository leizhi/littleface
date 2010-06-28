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
<form name="default" Method="Post" action="<%=request.getContextPath()%>/AccountType.do?state=promptAdd">
<%@ include file="/jsp/etc/adminHeader.jsp" %>
<!-- AddTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="AddOutput"><html:message key="Code" /></li>
	<li class="AddInput"><html:input type="text" id="Code" name="Code" property="Code" size="15" maxlength="255"/></li>

     	<li class="AddOutput"><html:message key="NoteType" /></li>
	<li class="AddInput"><html:select id="NoteType" name="NoteType" property="NoteType" /></li>

     	<li class="AddOutput"><html:message key="Category" /></li>
	<li class="AddInput"><html:select id="Category" name="Category" property="Category" /></li>

     	<li class="AddOutput"><html:message key="Description" /></li>
        <li class="AddInput"><html:input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/></li>
 
     	<li class="Button"><input type="button" id="Add" name="Add" value="Add" onclick="Util.Submit('/AccountType.do?state=processAdd');"/><input type="reset" id="reset" name="reset" value="reset"/></li>
      </ul>
    </div>
<%@ include file="/jsp/etc/winFooter.jsp" %>
</form>
</body>
</html>
