<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/etc/static.inc" %>

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
<%@ include file="/jsp/etc/loginHeader.jsp" %>

<!-- SearchTable -->
    <div class="SearchTable" >
      <ul>
     	<li class="Output"><html:message key="JobID" /></li>
	<li class="Input"> <html:output property="JobID"/> <html:input type="hidden" id="JobID" name="JobID" property="JobID"/></li>
     	<li class="Output"><html:message key="NoteNo" /></li>
	<li class="Input"> <html:output property="NoteNo"/> <html:input type="hidden" id="NoteNo" name="NoteNo" property="NoteNo"/></li>

     	<li class="Output"><html:message key="NoteType" /></li>
        <li class="Input"> <html:output property="NoteType" /> <html:input type="hidden" id="NoteType" name="NoteType" property="NoteType"/></li>

     	<li class="Output"><html:message key="Operator" /></li>
        <li class="Input"> <html:output property="Operator" /> <html:input type="hidden" id="Operator" name="Operator" property="Operator"/></li>

     	<li class="Output"><html:message key="ChargeTo" /></li>
        <li class="Input"><html:output property="ChargeTo" /> <html:input type="hidden" id="ChargeTo" name="ChargeTo" property="ChargeTo"/></li>

     	<li class="Output"> <html:message key="Date" /></li>
        <li class="Input"> <html:output property="Date" /> <html:input type="hidden" id="Date" name="Date" property="Date"/></li>
    	<li class="Output"> <html:message key="DueDate" /></li>
        <li class="Input"> <html:output property="DueDate" /> <html:input type="hidden" id="Date" name="DueDate" property="DueDate"/></li>

     	<li class="Output"> <html:message key="Balance" /></li>
        <li class="Input"> <html:output property="Balance" /> <html:input type="hidden" id="Balance" name="Balance" property="Balance"/></li>

     	<li class="Output"><html:message key="AccountToCredit" /></li>
        <li class="Input"><html:output property="AccountToCredit" /> <html:input type="hidden" id="AccountToCredit" name="AccountToCredit" property="AccountToCredit"/></li>
     	<li class="Output"><html:message key="AmountToCredit" /></li>
        <li class="Input"><html:input type="text" id="AmountToCredit" name="AmountToCredit" property="AmountToCredit" size="15" maxlength="255"/></li>

     	<li class="Output"><html:message key="AccountToDebit" /></li>
        <li class="Input"><html:output property="AccountToDebit" /> <html:input type="hidden" id="AccountToDebit" name="AccountToDebit" property="AccountToDebit"/></li>
     	<li class="Output"><html:message key="AmountToDebit" /></li>
        <li class="Input"><html:input type="text" id="AmountToDebit" name="AmountToDebit" property="AmountToDebit" size="15" maxlength="255"/></li>

     	<li class="Output"><html:message key="Description" /></li>
        <li class="Input"><html:input type="text" id="Description" name="Description" property="Description" size="15" maxlength="255"/></li>

     	<li class="Button"><input type="button" id="ToJournal" name="ToJournal" value="ToJournal" onclick="Util.Submit('/Accounting.do');"/></li>
      </ul>
    </div>

<%@ include file="/jsp/etc/winFooter.jsp" %>
</form>
</body>
</html>
