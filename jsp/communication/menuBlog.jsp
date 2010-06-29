<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<html>
<head>
<title>Nick</title>
<link type="text/css" rel="stylesheet" href="jsp/css/css4cnltreemenu.css" />
<link type="text/css" rel="stylesheet" href="jsp/css/default.css" />
<script type="text/javascript" src="jsp/js/js4cnltreemenu.js"></script>
<%-- 
<script type="text/javascript">

</script> 
<style type="text/css">

</style>
--%>
</head>

<body>
<form name="default" Method="Post" action="">
<%@ include file="/jsp/incl/winHeader.jsp" %>
<%--
<%int i=0;%>
<logic:iterate property="TypeList">
    <div class="DivColumns" >
      <ul>
     	<li class ="liBg"><%=Input.getValue("Type"+i)%></li>

<%int j=0;%>
<logic:iterate property="<%="BlogList"+i%>">
     	<li><a href="<%=request.getContextPath()%>/Blog.do?state=listMessage&Key=<%=Input.getValue("Key"+i+j)%>"><%=Input.getValue("Blog"+i+j)%> </a></li>
<% j++; %>
</logic:iterate>

      </ul>
    </div>
<% i++; %>
</logic:iterate>
--%>
<div id="listBlog">
<ul>
<%int i=0;%>
<logic:iterate property="TypeList">
<li class="Title"><%=Input.getValue("Type"+i)%></li>
<li class="c1">module</li>
<li class="c2">title</li>
<li class="c3">count</li>
<li class="c4">endTalk</li>

<%int j=0;%>
<logic:iterate property="<%="BlogList"+i%>">
<li class="c1"><%=Input.getValue("Blog"+i+j)%></li>
<li class="c2"><%=Input.getValue("Blog"+i+j)%></li>
<li class="c3"><%=Input.getValue("CountMsg"+i+j)%></li>
<li class="c4"><%=Input.getValue("Key"+i+j)%></li>
<% j++; %>
</logic:iterate>

<% i++; %>
</logic:iterate>
</ul>
</div>
<%@ include file="/jsp/incl/winFooter.jsp" %>
</form>
</body>
</html>
