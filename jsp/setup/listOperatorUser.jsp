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
<form id="default" name="default" method="Post" action="<%=request.getContextPath()%>/OperatorUser.do" >
<%@ include file="/jsp/incl/adminHeader.jsp" %>
<!-- SearchTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="Output"><fmt:message key="ID" /></li>
	<li class="Input"><input type="text" id="ID" name="ID" property="ID" size="15" maxlength="255"/></li>

     	<li class="Output" id="flag"><fmt:message key="UserName" /> </li>
        <li class="Input"><input type="text" id="UserName" name="UserName" size="15" maxlength="255"/></li>

     	<li class="Output"><fmt:message key="Email" /></li>
	<li class="Input"><input type="text" id="Email" name="Email" property="Email" size="15" maxlength="255"/></li>

     	<li class="Output"></li>
        <li class="Input"></li>

 
     	<li class="Button"><input type="button" id="Find" name="Find" value="Find" onclick="Util.Submit('/OperatorUser.do?state=list');"/><input type="button" id="Add" name="Add" value="Add" onclick="Util.Submit('/OperatorUser.do?state=promptAdd');"/><input type="reset" id="reset" name="reset" value="reset"/></li>
      </ul>
    </div>

<!-- ListTable -->
    <div class="Table" >
      <ul>
     	<li class="Title"><fmt:message key="ID" /></li>
     	<li class="Title"><fmt:message key="UserName" /></li>
     	<li class="Title"><fmt:message key="Email" /></li>
     	<li class="Title"><fmt:message key="Operation" /></li>
<%int i=0;%>
<logic:iterate property="List">
        <li><%=Input.getValue("ID"+i)%></li>
        <li><%=Input.getValue("UserName"+i)%></li>
	<li><%=Input.getValue("Email"+i)%></li>

        <li><a href="javascript:Util.Submit('/OperatorUser.do?state=promptUpdate&Key=<%=Input.getValue("ID"+i)%>');"><fmt:message key="Update" /></a> <a href="javascript:Util.Submit('/OperatorUser.do?state=processDelete&Key=<%=Input.getValue("ID"+i)%>');"><fmt:message key="Delete" /></a></li>
<% i++; %>
</logic:iterate>

	<%-- <li><input type="checkbox" id="UserName" name="UserName" value="0"/></li> --%>
      </ul>
    </div>

<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
