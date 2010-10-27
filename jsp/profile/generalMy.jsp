<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="jsp/js/util.js"></script>
<script type="text/javascript" src="jsp/js/tw-sack.js" ></script>

<style type="text/css">
.box {
	margin: 0;
	padding: 0;
	width: 49%;
	float: left;
	text-align: left;
	background-color: edf0f9;
	color: 000000;
	font-size: 12px;
	border: 1px solid #ffffff;
}

.box ul {
	width: 100%;
}

.box li {
	background-color: #edf0f9;
	float: left;
	display: block;
}

.box ul .tl {
	text-align:center;
	background-color:e3e9ff;
	width: 20%;
}

.box ul .tr {
	text-align:right;
	background-color:e3e9ff;
	width: 80%;
}

.box ul .left {
	text-align:center;
	width: 20%;
}
.box ul .right {
	width: 80%;
}
</style>

<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
</script>

<script type="text/javascript">
var ajax = new sack();

function whenLoading(){
	var e = document.getElementById('replaceme'); 
	e.innerHTML = "<p>Sending Data...</p>";
}

function whenLoaded(){
	var e = document.getElementById('replaceme'); 
	e.innerHTML = "<p>Data Sent...</p>";
}

function whenInteractive(){
	var e = document.getElementById('replaceme'); 
	e.innerHTML = "<p>getting data...</p>";
}

function whenCompleted(){
	var e = document.getElementById('sackdata'); 
	if (ajax.responseStatus){
		var string = "<p>Status Code: " + ajax.responseStatus[0] + "</p><p>Status Message: " + ajax.responseStatus[1] + "</p><p>URLString Sent: " + ajax.URLString + "</p>";
	} else {
		var string = "<p>URLString Sent: " + ajax.URLString + "</p>";
	}
	e.innerHTML = string;
}

function doit(){
	var form = document.getElementById('form');
	ajax.setVar("myTextBox", form.mytext.value); // recomended method of setting data to be parsed.
	ajax.requestFile = "sackdemo.php";
	ajax.method = form.method.value;
	ajax.element = 'replaceme';
	ajax.onLoading = whenLoading;
	ajax.onLoaded = whenLoaded; 
	ajax.onInteractive = whenInteractive;
	ajax.onCompletion = whenCompleted;
	ajax.runAJAX();
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

<div id="container">
	
<div class="box">
	<ul>
		<li class="tl"><fmt:message key="General"/></li>
		<li class="tr"><a href=""><fmt:message key="Edit"/></a></li>
		<li style="clear: both;"/>
				
		<li class="left"><fmt:message key="Name" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>

		<li class="left"><fmt:message key="Sex" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Height" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Weight" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Birthday" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Career" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Education" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Married" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="QQ" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Email" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Secret" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
	</ul>
</div>

<div class="box">

	<ul>
		<li class="tl"><fmt:message key="Address"/></li>
		<li class="tr"><a href=""><fmt:message key="Edit"/></a></li>
		<li style="clear: both;"/>
				
		<li class="left"><fmt:message key="Country" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>

		<li class="left"><fmt:message key="City" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="PostalCode" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Address" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Tel" /></li>
		<li class="right"><input name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="MobileNo" /></li>
		<li class="right"><c:out value="${user.name }" /></li>
		<li style="clear: both;"/>
	</ul>
</div>
<div style="clear: both;"></div>
<!-- one row end -->

<div class="box">

	<ul>
		<li class="tl"><fmt:message key="Images"/></li>
		<li class="tr"><a href=""><fmt:message key="Edit"/></a></li>
		<li style="clear: both;"/>
				
		<li>
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

<div class="box">

	<ul>
		<li class="tl"><fmt:message key="Friend"/></li>
		<li class="tr"><a href=""><fmt:message key="Edit"/></a></li>
		<li style="clear: both;"/>
				
		<li>abc@163.com</li>
		<li>abc@163.com</li>
		<li>abc@163.com</li>
		<li>abc@163.com</li>
		<li>abc@163.com</li>
	</ul>
</div>
<div style="clear: both;"></div>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>