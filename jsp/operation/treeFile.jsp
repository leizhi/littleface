<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Folder tree with Drag and Drop capabilities</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" src="jsp/js/tw-sack.js"></script>
<script type="text/javascript" src="jsp/js/context-menu.js"></script><!-- IMPORTANT! INCLUDE THE context-menu.js FILE BEFORE drag-drop-folder-tree.js -->
<script type="text/javascript"	src="jsp/js/drag-drop-folder-tree.js">

/************************************************************************************************************
(C) www.dhtmlgoodies.com, July 2006

Update log:


This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	

Terms of use:
You are free to use this script as long as the copyright message is kept intact.

For more detailed license information, see http://www.dhtmlgoodies.com/index.html?page=termsOfUse 

Thank you!

www.dhtmlgoodies.com
Alf Magne Kalleland

************************************************************************************************************/
</script>

<link href="skin/office/default/drag-drop-folder-tree.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/context-menu.css" type="text/css" rel="stylesheet"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/dhtmlgoodies_calendar.css?random=20100901" media="screen" />
<script type="text/javascript" src="jsp/js/util.js"></script>
<script type="text/javascript" src="jsp/js/dhtmlgoodies_calendar.js?random=20101018"></script>

<style type="text/css">
/* CSS for the demo */
img {
	border: 0px;
}

table,td,th {
	border: 1px #000 solid;
	border: 0;
	border-collapse: collapse;
	padding: 2px;
	text-align: left;
	nowrap;
}

table {
	/*width: 80%;*/
	margin: auto;
}

thead td,thead th {
	background-color: #d3eaef;
}

caption,tfoot td,tfoot th {
	background-color: #c8d6ff;
}

tbody /*tbody td,tbody th*/ {
	margin: 2px 0 2px 0;
	text-align: left;
	width: 25px;
}

caption {
	color: #2b6ee7;
}

tfoot td {
	text-align: center;
}
tfoot tr td {
	width: 800px;
}
</style>

<c:url value="/File.do" var="ajaxRetrieve">
	<c:param name="method">retrieve</c:param>
</c:url>

<c:url value="/File.do" var="ajaxUpload">
	<c:param name="method">upload</c:param>
</c:url>

<c:url value="/File.do" var="ajaxMkdir">
	<c:param name="method">mkdir</c:param>
</c:url>

<script type="text/javascript">
var ajax = new sack();

//Use something like this if you want to save data by Ajax.
function retrieve(id) {
	//alert(id);
	ajax.setVar("fileId", id); // recomended method of setting data to be parsed.
	ajax.requestFile = "${ajaxRetrieve}";
	ajax.onCompletion = whenCompleted;
	ajax.runAJAX();
}

function whenCompleted(){
	var e = document.getElementById('view');
	var string = ajax.response;
	e.innerHTML = string;
}

function setUpload(id,name) {
	//alert(str);
	var e = document.getElementById('parent');
	e.value = name;

	e = document.getElementById('parentId');
	e.value = id;

	e = document.getElementById('dparent');
	e.value = name;

	e = document.getElementById('dparentId');
	e.value = id;
}

function addFile() {
	var nav = document.getElementById("fileData"); 
	var rowFile = document.getElementById("rowFile");
	nav.appendChild(rowFile.cloneNode(true));
}

function removeFile() {
	var nav = document.getElementById("fileData");
	rows = nav.getElementsByTagName("tr");
	nav.removeChild(rows[rows.length-1]);
}

function doUpload() {
	alert("确认上传");
	ajax.requestFile = "${ajaxUpload}";
	ajax.onCompletion = flushTree;
	ajax.runAJAX();
}

function flushTree(){
	var e = document.getElementById('tree');
	var string = ajax.response;
	e.innerHTML = string;
}

function upload(){
	var e = document.getElementById('upload');
	if(e.style.display=="block")
		e.style.display="none";
	else
		e.style.display="block";
}

function mkdir(){
	var e = document.getElementById('mkdir');
	if(e.style.display=="block")
		e.style.display="none";
	else
		e.style.display="block";
}

function change(obj){
	//alert(obj);
	if (navigator.userAgent.indexOf("MSIE")!=-1) {
		var arr = obj.value.split("\\");
		document.getElementById('fileName').value=arr[arr.length-1];
	}else if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Mozilla")!=-1) {
    		document.getElementById('fileName').value=obj.files.item(0).fileName;
	}else {
         alert("Not IE or Firefox (userAgent=" + navigator.userAgent + ")");
	}
}
</script>

</head>
<body>
<jsp:include page="/jsp/incl/g_top.jsp" />

<div id="container">
<div style="width: 100%;background-color: #eeeeee;color: #add2da;font-size: 12px;">
	<input type="button" onclick="upload()" value="上传">
	<input type="button" onclick="mkdir()" value="新建目录">
</div>

<div id="upload" style="width: 100%;background-color: #fff;color: #add2da;font-size: 12px;display:none;">
<form id="upit" method="post"  action="${ajaxUpload}" enctype="multipart/form-data">
<table>
<caption>上传文件</caption>

<tbody id="fileData">
<tr>
<td colspan="2" style="text-align:center;">
	<input type="submit" value="确认">
</td>
</tr>

<tr>
<td style="text-align:right;">父文件夹</td>
<td><input type="text" id="parent" readonly="readonly"/>
<input type="hidden" name="parentId" id="parentId"/></td>
</tr>

<tr>
<td style="text-align:right;">名称</td>
<td><input type="text" name="name" id="fileName"/></td>
</tr>
<%--
<tr>
<td style="text-align:right;">上传日期</td>
<td><input type="text" name="datetime" id="datetime"/>
<img src="jsp/images/miniDate.gif" border=0 alt="<fmt:message key="choosedate"/>" onclick="displayCalendar(document.forms[0].datetime,'yyyy/mm/dd hh:ii',this,true)"/>
</td>
</tr>
--%>
<tr id="rowFile">
<td style="text-align:right;">文件</td>
<td><input type="file" name="file" id="file" onchange="change(this)"/><%--  <input type="button" onclick="addFile();" value="增加"/><input type="button" onclick="removeFile();" value="删除"/>--%></td>
</tr>
 
</tbody>

</table>
</form>
</div>

<div id="mkdir" style="width: 100%;background-color: #fff;color: #add2da;font-size: 12px;display:none;">
<form id="mkit" method="post"  action="${ajaxMkdir}">
<table>
<caption>新建目录</caption>

<tbody id="fileData">
<tr>
<td colspan="2" style="text-align:center;">
	<input type="submit" value="确认">
</td>
</tr>

<tr>
<td style="text-align:right;">父文件夹</td>
<td><input type="text" id="dparent" readonly="readonly"/>
<input type="hidden" name="parentId" id="dparentId"/></td>
</tr>

<tr>
<td style="text-align:right;">名称</td>
<td><input type="text" name="folderName"/></td>
</tr>
</tbody>

</table>
</form>
</div>
<div style="width: 100%;background-color: #bbeedd;color: #add2da;font-size: 12px;">
<div id="tree" style="overflow: scroll; height: 300px; width: 19%;float:left;background-color: #fff;color: #000;border: 0.1% solid #005AA7; margin: 0.5%;"">
${stringTree }
<%--
<ul id="dhtmlgoodies_tree2" class="dhtmlgoodies_tree">
	<li id="node0" noDrag="true" noSiblings="true" noDelete="true" noRename="true"><a href="#">Root node</a>
	<ul>
		<li id="node1"><a href="#">Europe</a>
		<ul>
			<li id="node2" noDelete="true"><a href="#" onclick="alert('ok');">Norway</a>
			<ul>
				<li id="node3" noRename="true"><a href="#">Stavanger</a></li>
				<li id="node6"><a href="#">Bergen</a></li>
				<li id="node7"><a href="#">Oslo</a></li>
			</ul>
			</li>
		</ul>
		</li>
		<li id="node15"><a href="#">Asia</a>
		<ul>
			<li id="node151"><a href="#">Japan</a>
			<li id="node152"><a href="#">China</a>
			<li id="node153"><a href="#">Indonesia</a>
		</ul>
		</li>
		<li id="node24" noChildren="true"><a href="#">Cannot have
		children</a></li>
		<li id="node25" noDrag="true"><a href="#">Cannot be dragged</a></li>
	</ul>
	</li>
</ul>
 --%>
</div>

<div id="view" style="width: 79%;float:right;background-color: #bbeedd;color: #000;border: 0.1% solid #005AA7; margin: 0.5%;">
<p  style="color: #000;">welcome h</p>
</div>

<div style="clear: both;"></div>
</div>

</div>
<%--
<form>
<input type="button" onclick="saveMyTree()" value="Save">
</Form>
 --%>
 
<script type="text/javascript">
	treeObj = new JSDragDropTree();
	treeObj.setTreeId('dhtmlgoodies_tree2');
	treeObj.setMaximumDepth(7);
	treeObj.setMessageMaximumDepthReached('Maximum depth reached'); // If you want to show a message when maximum depth is reached, i.e. on drop.
	treeObj.initTree();
	treeObj.expandAll();
</script>
<a href="#" onclick="treeObj.collapseAll()">Collapse all</a> | 
<a href="#" onclick="treeObj.expandAll()">Expand all</a>
	
<!-- Form - if you want to save it by form submission and not Ajax -->
<form name="myForm" method="post" action="saveNodes.jsp">
	<input type="hidden" name="saveString">
</form>

</body>
</html>