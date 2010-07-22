<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="../etc/styles/default.css" />
<script type="text/javascript" src="../etc/js/util.js"></script>

<script type="text/javascript"  language="javascript" charset="utf-8">
</script>

<style type="text/css">
</style>

</head>

<body>
<form id="default" name="default" method="Post" action="<%=request.getContextPath()%>/Accounting.do" >
<script type="text/javascript" src="../etc/js/calendar.js"></script>
<%@ include file="/jsp/incl/loginHeader.jsp" %>

<!-- SearchTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="Output"><fmt:message key="JobID" /></li>
	<li class="Input"> <html:output property="JobID"/> <input type="hidden" id="JobID" name="JobID" property="JobID"/></li>
     	<li class="Output"><fmt:message key="NoteNo" /></li>
	<li class="Input"> <html:output property="NoteNo"/> <input type="hidden" id="NoteNo" name="NoteNo" property="NoteNo"/></li>

     	<li class="Output"><fmt:message key="NoteType" /></li>
        <li class="Input"> <html:output property="NoteType" /> <input type="hidden" id="NoteType" name="NoteType" property="NoteType"/></li>

     	<li class="Output"><fmt:message key="Operator" /></li>
        <li class="Input"> <html:output property="Operator" /> <input type="hidden" id="Operator" name="Operator" property="Operator"/></li>

     	<li class="Output"><fmt:message key="ChargeTo" /></li>
        <li class="Input"><html:output property="ChargeTo" /> <input type="hidden" id="ChargeTo" name="ChargeTo" property="ChargeTo"/></li>

     	<li class="Output"> <fmt:message key="Date" /></li>
        <li class="Input"> <html:output property="Date" /> <input type="hidden" id="Date" name="Date" property="Date"/></li>
    	<li class="Output"> <fmt:message key="DueDate" /></li>
        <li class="Input"> <html:output property="DueDate" /> <input type="hidden" id="Date" name="DueDate" property="DueDate"/></li>

     	<li class="Output"> <fmt:message key="Balance" /></li>
        <li class="Input"> <html:output property="Balance" /> <input type="hidden" id="Balance" name="Balance" property="Balance"/></li>

     	<li class="Output"><fmt:message key="AccountToCredit" /></li>
        <li class="Input"><html:output property="Account" /> <input type="hidden" id="Account" name="Account" property="Account"/></li>
     	<li class="Output"><fmt:message key="AmountToCredit" /></li>
        <li class="Input"><input type="text" id="AmountToCredit" name="AmountToCredit" property="AmountToCredit" size="15" maxlength="255"/></li>

     	<li class="Output"><fmt:message key="AccountToDebit" /></li>
        <li class="Input"><html:output property="Account" /> <input type="hidden" id="Account" name="Account" property="Account"/></li>
     	<li class="Output"><fmt:message key="AmountToDebit" /></li>
        <li class="Input"><input type="text" id="AmountToCredit" name="AmountToCredit" property="AmountToCredit" size="15" maxlength="255"/></li>

     	<li class="Output"><fmt:message key="Description" /></li>
        <li class="Input"><input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/></li>

     	<li class="Button"><input type="button" id="ToJournal" name="ToJournal" value="ToJournal" onclick="Util.Submit('/Accounting.do');"/></li>
      </ul>
    </div>

<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
