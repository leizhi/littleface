<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="jsp/styles/default.css" />
<script type="text/javascript" src="jsp/js/util.js"></script>

<script type="text/javascript"  language="javascript" charset="utf-8">
function checkAll(obj) {
  for (var i = 0; i < document.getElementsByName("SelectItem").length; i++) {
	document.getElementsByName("SelectItem")[i].checked = false;
	}
	obj.checked = true;
var isA = obj.value;
document.getElementById("ItemName").value = document.getElementById("ItemName"+isA).value;
document.getElementById("ItemRate").value = document.getElementById("ItemRate"+isA).value;
document.getElementById("ItemUnit").value = document.getElementById("ItemUnit"+isA).value;
document.getElementById("ItemQuantity").value = document.getElementById("ItemQuantity"+isA).value;

var str = document.getElementById("BillAmount"+isA).value;
document.getElementById("BillAmount").value = str.substring(0,str.length-1); 

document.getElementById("Account").value = document.getElementById("AccountID"+isA).value;
document.getElementById("Currency").value = document.getElementById("CurrencyID"+isA).value;
//window.alert(obj.value);
}

function billAmout(obj) {

var rate = document.getElementById("ItemRate").value;
var quantity = document.getElementById("ItemQuantity").value;

if ((typeof(rate) !="undefined") && (rate != 0) && (quantity != 0) && (typeof(quantity) !="undefined") )
document.getElementById("BillAmount").value = quantity*rate;
	//window.alert("util*rate="+quantity*rate);
}

</script>

<style type="text/css">

</style>

</head>

<body>
<form id="default" name="default" method="Post" action="<%=request.getContextPath()%>/Accounting.do" >
<script type="text/javascript" src="jsp/js/calendar.js"></script>
<%@ include file="/jsp/incl/loginHeader.jsp" %>

<!-- SearchTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="Output"><fmt:message key="JobID" /></li>
	<li class="Input"><input type="text" id="JobID" name="JobID" property="JobID" size="15" maxlength="255"/></li>
     	<li class="Output"><fmt:message key="NoteNo" /></li>
	<li class="Input"><html:output id="NoteNo" name="NoteNo" property="NoteNo"/>	<input type="hidden" id="NoteNo" name="NoteNo" property="NoteNo"/></li>

     	<li class="Output"><fmt:message key="NoteType" /></li>
        <li class="Input"><html:select id="NoteType" name="NoteType" property="NoteType" onchange="javascript:Util.Submit('/Accounting.do?state=promptNewNote');"/></li>

     	<li class="Output"><fmt:message key="Operator" /></li>
        <li class="Input"><html:select id="Operator" name="Operator" property="Operator" /></li>

     	<li class="Output"><fmt:message key="ChargeTo" /></li>
        <li class="Input"><html:select id="ChargeTo" name="ChargeTo" property="ChargeTo" /></li>

     	<li class="Output"> <fmt:message key="Date" /></li>
        <li class="Input"> <html:output property="Date" /> <input type="hidden" id="Date" name="Date" property="Date"/></li>
    	<li class="Output"> <fmt:message key="DueDate" /></li>
        <li class="Input"> <input type="text" id="DueDate" name="DueDate" property="DueDate" size="15" maxlength="255"/> <a href="javascript:Util.Calendar('DueDate')"><img src="jsp/etc/images/miniDate.gif" border=0 alt="<fmt:message key="ChooseDate"/>"></a></li>

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

     	<li><html:select id="Account" name="Account" property="Account" onchange="javascript:Util.Submit('/Accounting.do?state=promptNewNote');"/></li>
     	<li><input type="text" id="ItemName" name="ItemName" property="ItemName" size="10" maxlength="255"/></li>
     	<li><input type="text" id="ItemRate" name="ItemRate" property="ItemRate" size="10" maxlength="255" onchange="javascript:billAmout(this);"/></li>
     	<li><input type="text" id="ItemUnit" name="ItemUnit" property="ItemUnit" size="10" maxlength="255"/></li>
     	<li><input type="text" id="ItemQuantity" name="ItemQuantity" property="ItemQuantity" size="10" maxlength="255" onchange="javascript:billAmout(this);"/></li>
     	<li><input type="text" id="BillAmount" name="BillAmount" property="BillAmount" size="10" maxlength="255"/></li>
	<li><html:output property="Currency"/> <input type="hidden" id="Currency" name="Currency" property="Currency"/></li>

     	<li class="Action"><input type="button" id="Add" name="Add" value="Add" onclick="Util.Submit('/Accounting.do?state=processNewNote');"/><input type="button" id="Update" name="Update" value="Update" onclick="Util.Submit('/Accounting.do');"/><input type="button" id="Delete" name="Delete" value="Delete" onclick="Util.Submit('/Accounting.do');"/></li>



<%int i=0;%>
<logic:iterate property="List">
        <li><input type="checkbox" id="SelectItem" name="SelectItem" value="<%=""+i%>"  onclick="javascript:checkAll(this);"/><html:output property="<%="Account"+i%>"/><input type="hidden" id="<%="AccountID"+i%>" name="<%="AccountID"+i%>" property="<%="AccountID"+i%>"/></li>
        <li><html:output property="<%="ItemName"+i%>"/> <input type="hidden" id="<%="ItemName"+i%>" name="<%="ItemName"+i%>" property="<%="ItemName"+i%>"/> </li>
        <li><html:output property="<%="ItemRate"+i%>"/> <input type="hidden" id="<%="ItemRate"+i%>" name="<%="ItemRate"+i%>" property="<%="ItemRate"+i%>"/> </li>
        <li><html:output property="<%="ItemUnit"+i%>"/> <input type="hidden" id="<%="ItemUnit"+i%>" name="<%="ItemUnit"+i%>" property="<%="ItemUnit"+i%>"/> </li>
        <li><html:output property="<%="ItemQuantity"+i%>"/> <input type="hidden" id="<%="ItemQuantity"+i%>" name="<%="ItemQuantity"+i%>" property="<%="ItemQuantity"+i%>"/> </li>
        <li><html:output property="<%="BillAmount"+i%>"/> <input type="hidden" id="<%="BillAmount"+i%>" name="<%="BillAmount"+i%>" property="<%="BillAmount"+i%>"/> </li>
	<li><html:output property="<%="Currency"+i%>"/> <input type="hidden" id="<%="CurrencyID"+i%>" name="<%="CurrencyID"+i%>" property="<%="CurrencyID"+i%>"/></li>
<% i++; %>
</logic:iterate>
      </ul>
    </div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
