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
<script type="text/javascript" src="jsp/js/calendar.js"></script>
<%@ include file="/jsp/incl/loginHeader.jsp" %>

<!-- SearchTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="Output"><fmt:message key="JobID" /></li>
	<li class="Input"><input type="text" id="JobID" name="JobID" property="JobID" size="15" maxlength="255"/></li>
     	<li class="Output"><fmt:message key="JobNoteID" /></li>
	<li class="Input"><input type="text" id="JobNoteID" name="JobNoteID" property="JobNoteID" size="15" maxlength="255"/></li>

     	<li class="Output"><fmt:message key="Customer" /></li>
        <li class="Input"><input type="text" id="Customer" name="Customer" property="Customer" size="15" maxlength="255"/></li>
     	<li class="Output"><fmt:message key="SearchType" /></li>
        <li class="Input"><html:select id="SearchType" name="SearchType" property="SearchType" /></li>

     	<li class="Output"> <fmt:message key="StartDate" /></li>
        <li class="Input"> <input type="text" id="StartDate" name="StartDate" property="StartDate" size="15" maxlength="255"/> <a href="javascript:Util.Calendar('StartDate')"><img src="jsp/etc/images/miniDate.gif" border=0 alt="<fmt:message key="ChooseDate"/>"></a></li>
    	<li class="Output"> <fmt:message key="EndDate" /></li>
        <li class="Input"> <input type="text" id="EndDate" name="EndDate" property="EndDate" size="15" maxlength="255"/> <a href="javascript:Util.Calendar('EndDate')"><img src="jsp/etc/images/miniDate.gif" border=0 alt="<fmt:message key="ChooseDate"/>"></a></li>

     	<li class="Button"><input type="button" id="List" name="List" value="List" onclick="Util.Submit('/Accounting.do');"/><input type="button" id="NewNote" name="NewNote" value="NewNote" onclick="Util.Submit('/Accounting.do?state=promptNewNote');"/><input type="reset" id="reset" name="reset" value="reset"/></li>
      </ul>
    </div>
<!-- ListTable -->
    <div class="Table6" >
      <ul>
     	<li class="Title"><fmt:message key="NoteNo" /></li>
     	<li class="Title"><fmt:message key="Account" /></li>
     	<li class="Title"><fmt:message key="Amount" /></li>
     	<li class="Title"><fmt:message key="ChargeTo" /></li>
     	<li class="Title"><fmt:message key="Date" /></li>
     	<li class="Title"><fmt:message key="Operation" /></li>
<%int i=0;%>
<logic:iterate property="List">
        <li><%=Input.getValue("NoteNo"+i)%></li>
        <li><%=Input.getValue("Account"+i)%></li>
        <li><%=Input.getValue("Amount"+i)%></li>
        <li><%=Input.getValue("ChargeTo"+i)%></li>
	<li><%=Input.getValue("Date"+i)%></li>

        <li><a href="javascript:Util.Submit('/Accounting.do?state=promptUpdate&NoteNo=<%=Input.getValue("NoteNo"+i)%>');"><fmt:message key="Update" /></a> <a href="javascript:Util.Submit('/Accounting.do?state=processDelete&NoteNo=<%=Input.getValue("NoteNo"+i)%>');"><fmt:message key="View" /></a> <a href="javascript:Util.Submit('/Accounting.do?state=prompt<%=Input.getValue("Command"+i)%>&NoteNo=<%=Input.getValue("NoteNo"+i)%>&AccountID=<%=Input.getValue("AccountID"+i)%>');"><%=Input.getValue("Command"+i)%></a></li>
<% i++; %>
</logic:iterate>

	<%-- <li><input type="checkbox" id="UserName" name="UserName" value="0"/></li> --%>
      </ul>
    </div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
