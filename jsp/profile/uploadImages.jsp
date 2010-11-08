<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="jsp/js/util.js"></script>

<style type="text/css">
ul {
	width: 100%;
	margin: 0;
	padding: 0;
}

li {
	background-color: #edf0f9;
	float: left;
	display: block;
	width: 100%;
}

ul .tl {
	text-align: center;
	background-color: e3e9ff;
	width: 20%;
}

ul .tr {
	text-align: right;
	background-color: e3e9ff;
	width: 80%;
}

ul .title {
	text-align: center;
	background-color: e3e9ff;
	width: 100%;
}

ul .left {
	text-align: center;
	width: 20%;
}

ul .right {
	width: 80%;
}
</style>

<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
function addFile() {
	var nav = document.getElementById("fileData"); 
	var rowFile = document.getElementById("rowFile");
	nav.appendChild(rowFile.cloneNode(true));
}

function removeFile() {
	var nav = document.getElementById("fileData");
	rows = nav.getElementsByTagName("li");
	nav.removeChild(rows[rows.length-1]);
}


function refreshParent(){
	window.opener.location.href = window.opener.location.href;
	if (window.opener.progressWindow){
		window.opener.progressWindow.close();
	}
	window.close();
} 
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/My.do" var="processUploadImages">
	<c:param name="method">processUploadImages</c:param>
</c:url>

<form method="post" action="${processUploadImages}" enctype="multipart/form-data">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<div id="container">
<div><fmt:message key="UploadImages"/></div>
<div>
	<ul id="fileData">
		<li id="rowFile" ><input type="file" id="file" name="file"/><input type="button" onclick="addFile();" value="增加"/><input type="button" onclick="removeFile();" value="删除"/></li>
	</ul>
</div>

<div>
	<input type="submit" value="Upload"/>
</div>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>