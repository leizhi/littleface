<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
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
     	<li class="Output"><fmt:message key="JobID" /></li>
	<li class="Input"><input type="text" id="JobID" name="JobID" property="JobID" size="15" maxlength="255"/></li>
     	<li class="Output"><fmt:message key="NoteNo" /></li>
	<li class="Input"><html:output id="NoteNo" name="NoteNo" property="NoteNo"/>	<input type="hidden" id="NoteNo" name="NoteNo" property="NoteNo"/></li>

     	<li class="Output"><fmt:message key="NoteType" /></li>
        <li class="Input"><html:select id="NoteType" name="NoteType" property="NoteType" /></li>

     	<li class="Output"><fmt:message key="Operator" /></li>
        <li class="Input"><html:select id="Operator" name="Operator" property="Operator" /></li>

     	<li class="Output"><fmt:message key="ChargeTo" /></li>
        <li class="Input"><html:select id="ChargeTo" name="ChargeTo" property="ChargeTo" /></li>

     	<li class="Output"> <fmt:message key="Date" /></li>
        <li class="Input"> <html:output property="Date" /> <input type="hidden" id="Date" name="Date" property="Date"/></li>
    	<li class="Output"> <fmt:message key="DueDate" /></li>
        <li class="Input"> <input type="text" id="DueDate" name="DueDate" property="DueDate" size="15" maxlength="255"/> <a href="javascript:Util.Calendar('DueDate')"><img src="../etc/images/miniDate.gif" border=0 alt="<fmt:message key="ChooseDate"/>"></a></li>

     	<li class="Output"><fmt:message key="Description" /></li>
        <li class="Input"><input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/></li>

      </ul>
    </div>
<!-- ListTable -->
    <div class="Table7" >
      <ul>
     	<li class="Title"><fmt:message key="Category" /></li>
     	<li class="Title"><fmt:message key="Item Name" /></li>
     	<li class="Title"><fmt:message key="Charge Rate" /></li>
     	<li class="Title"><fmt:message key="Unit" /></li>
     	<li class="Title"><fmt:message key="Quantity" /></li>
     	<li class="Title"><fmt:message key="Bill Amount" /></li>
     	<li class="Title"><fmt:message key="Currency" /></li>

     	<li><html:select id="Account" name="Account" property="Account" /></li>
     	<li><input type="text" id="ItemName" name="ItemName" property="ItemName" size="10" maxlength="255"/></li>
     	<li><input type="text" id="ItemRate" name="ItemRate" property="ItemRate" size="10" maxlength="255"/></li>
     	<li><input type="text" id="ItemUnit" name="ItemUnit" property="ItemUnit" size="10" maxlength="255"/></li>
     	<li><input type="text" id="ItemQuantity" name="ItemQuantity" property="ItemQuantity" size="10" maxlength="255"/></li>
     	<li><input type="text" id="BillAmount" name="BillAmount" property="BillAmount" size="10" maxlength="255"/></li>
	<li><html:select id="Currency" name="Currency" property="Currency" /></li>

     	<li class="Action"><input type="button" id="Add" name="Add" value="Add" onclick="Util.Submit('/Accounting.do?state=processNewNote');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/Accounting.do');"/><input type="button" id="Delete" name="Delete" value="Delete" onclick="Util.Submit('/Accounting.do');"/></li>



<%int i=0;%>
<logic:iterate property="List">
        <li><input type="checkbox" id="<%="ID"+i%>" name="<%="ID"+i%>" value="<%=Input.getValue("ID"+i)%>"/><%=Input.getValue("Account"+i)%></li>
        <li><%=Input.getValue("ItemName"+i)%></li>
        <li><%=Input.getValue("ItemRate"+i)%></li>
	<li><%=Input.getValue("ItemUnit"+i)%></li>
	<li><%=Input.getValue("ItemQuantity"+i)%></li>
	<li><%=Input.getValue("BillAmount"+i)%></li>
	<li></li>
<% i++; %>
</logic:iterate>
      </ul>
    </div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
