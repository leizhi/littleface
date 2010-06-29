<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/nick-html.tld" prefix="html" %>
<html>
<head>
<title>Nick</title>
<link rel="stylesheet" type="text/css" href="../etc/styles/default.css" />
<%-- 
<script src="" type="text/javascript">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form name="default" Method="Post" action="">
<%@ include file="/jsp/incl/loginHeader.jsp" %>

<div align=center class=title><bean:message key="setup.Title" /></div>
<div class="text" align=center>
<table align=center border="0" cellspacing="0" cellpadding="1" width="500" bgcolor="#ffffff">
  <tr>
    <td>
      <table border="0" cellspacing="0" cellpadding="4" width="100%" bgcolor="#bcbcbc">
        <tr>
        <td>
<!--  -->
<table align=center width=90% border=0 cellspacing=0 cellpadding=0>
<tr><td  colspan=3 class=col2>
        <html:message key="setup.Location" />......</td></tr>

<tr><td width=20% nowrap>&nbsp;</td>
<td valign=top>
  <ul id=small>
        <li><a href="<%=request.getContextPath()%>/Region.do?state=listAllRegion">
<html:message key="setup.Region"/></a></li>
        <li><a href="<%=request.getContextPath()%>/Country.do?state=listAllCountry">
<html:message key="setup.Country" /></a></li>
  </ul>
</td>
<td valign=top nowrap>
  <ul id=small>
        <li><a href="<%=request.getContextPath()%>/Branch.do?state=listAllBranch">
<html:message key="setup.Branch" /></a></li>
        <li><a href="<%=request.getContextPath()%>/Principle.do?state=listAllPrinciple">
<html:message key="setup.Principle" /></a></li>
  </ul>
</td></tr>
</table>
        </td>
        </tr>
     </table>
   </td>
  </tr>
</table>
<br>

<table align=center border="0" cellspacing="0" cellpadding="1" width="500" bgcolor="#ffffff">
  <tr>
    <td>
      <table border="0" cellspacing="0" cellpadding="4" width="100%" bgcolor="#bcbcbc">
        <tr>
        <td>
<!--  -->
<table align=center width=90% border=0 cellspacing=0 cellpadding=0>
<tr><td colspan=3 class=col2>
 <html:message key="setup.TypeDefinition"/>......</td></tr>
<tr><td width=20%>&nbsp;</td>
<td valign=top>
  <ul id=small>
        <li><a href="<%=request.getContextPath()%>/JobType.do">
<html:message key="setup.JobType" /></a></li>
        <li><a href="<%=request.getContextPath()%>/PartnerType.do">
<html:message key="setup.PartnerType" /></a></li>
        <li><a href="<%=request.getContextPath()%>/WeightType.do">
<html:message key="setup.WeightType" /></a></li>
        <li><a href="<%=request.getContextPath()%>/ChargeableType.do">
<html:message key="setup.ChargeableType" /></a></li>
  </ul>
</td>
<td valign=top nowrap>
  <ul id=small>
        <li><a href="<%=request.getContextPath()%>/InlandType.do">
<html:message key="setup.InlandType" /></a></li>
        <li><a href="<%=request.getContextPath()%>/ContainerType.do">
<html:message key="setup.ContainerType"/></a></li>
                       <li><a href="<%=request.getContextPath()%>/DocNoteTemplate.do"><html:message key="DocNoteTemplates"/></a></li>
  </ul>
</td>
</tr>
</table>
        </td>
        </tr>
     </table>
   </td>
  </tr>
</table>
<br>
</div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
