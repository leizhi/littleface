<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	function doState(value) { 
	   var state = document.getElementById('state');
		state.value=value;
	}
</script>
<style type="">
</style>
</head>
<body>
<html:form action="/Index.do"  method="post">
hello!
id:<input name="id" type="text"/><br/>
age:<input name="age" type="text"/><br/>
name:<input name="name" type="text"/><br/>
school:<input name="school" type="text"/><br/>

<html:submit value="增加" state="accept"/>
<html:submit value="首页" state="promptIndex"/>
</html:form>
</body>
</html>