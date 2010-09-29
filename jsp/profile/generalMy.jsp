<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
.title {
background-color:#464646;
color:blue;
}
li {
background-color:#c0c0c0;
color:blue;
}

</style>

<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/Account.do" var="listAccount"/>

<c:url value="/Account.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/Account.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/Account.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/Account.do" var="processDownload">
	<c:param name="method">processDownload</c:param>
</c:url>

<form method="post" action="${listAccount}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="Search Account" name="title"/>
</jsp:include>

<div style="float: left;width: 49%;text-align: left;background-color: #bbbccc;color: #add2da;font-size: 12px;border: 1px solid #ffffff;">

<ul style="width: 100%;margin-left:0px;">
<li class="title">

<div style="float: left;width: 59%;text-align: left;">
<span><fmt:message key="General"/></span>
</div>
<div style="float: left;width: 40%;text-align: right;">
<span><a href=""><fmt:message key="Edit"/></a></span>

</div>
<div style="clear: both;"></div>
</li>

<li><span><fmt:message key="Name"/></span><span></span></li>
<li><span><fmt:message key="Sex"/></span><span></span></li>
<li><span><fmt:message key="Height"/></span><span></span></li>
<li><span><fmt:message key="Weight"/></span><span></span></li>
<li><span><fmt:message key="Birthday"/></span><span></span></li>
<li><span><fmt:message key="Career"/></span><span></span></li>
<li><span><fmt:message key="Education"/></span><span></span></li>
<li><span><fmt:message key="Married"/></span><span></span></li>
<li><span><fmt:message key="QQ"/></span><span></span></li>
<li><span><fmt:message key="Email"/></span><span></span></li>
<li><span><fmt:message key="Secret"/></span><span></span></li>
</ul>

</div>

<div style="float: left;width: 50%;text-align: left;background-color: #bbbccc;color: #add2da;font-size: 12px;border: 1px solid #ffffff;">

<ul style="width: 100%;margin-left:0px;">
<li class="title">

<div style="float: left;width: 59%;text-align: left;">
<span><fmt:message key="Address"/></span>
</div>
<div style="float: left;width: 40%;text-align: right;">
<span><a href=""><fmt:message key="Edit"/></a></span>

</div>
<div style="clear: both;"></div>
</li>

<li><span><fmt:message key="Country"/></span><span></span></li>
<li><span><fmt:message key="City"/></span><span></span></li>
<li><span><fmt:message key="PostalCode"/></span><span></span></li>
<li><span><fmt:message key="Address"/></span><span></span></li>
<li><span><fmt:message key="Tel"/></span><span></span></li>
<li><span><fmt:message key="MobileNo"/></span><span></span></li>
</ul>

</div>
<div style="clear: both;"></div>
<!-- one row end -->

<div style="float: left;width: 49%;text-align: left;background-color: #bbbccc;color: #add2da;font-size: 12px;border: 1px solid #ffffff;">

<ul style="width: 100%;margin-left:0px;">
<li class="title">

<div style="float: left;width: 59%;text-align: left;">
<span><fmt:message key="Images"/></span>
</div>
<div style="float: left;width: 40%;text-align: right;">
<span><a href=""><fmt:message key="Edit"/></a></span>

</div>
<div style="clear: both;"></div>
</li>
<li><img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
<img width="80px" height="60px" src=""/>
</li>
</ul>

</div>

<div style="float: left;width: 50%;text-align: left;background-color: #bbbccc;color: #add2da;font-size: 12px;border: 1px solid #ffffff;">

<ul style="width: 100%;margin-left:0px;">
<li class="title">

<div style="float: left;width: 59%;text-align: left;">
<span><fmt:message key="Friend"/></span>
</div>
<div style="float: left;width: 40%;text-align: right;">
<span><a href=""><fmt:message key="Edit"/></a></span>

</div>
<div style="clear: both;"></div>
</li>

<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
<li><span>abc@163.com</span></li>
</ul>

</div>
<div style="clear: both;"></div>

<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />
</form>
</fmt:bundle>
</body>
</html>