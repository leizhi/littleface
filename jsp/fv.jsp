<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	/*
	These cookie functions are downloaded from 
	http://www.mach5.com/support/analyzer/manual/html/General/CookiesJavaScript.htm
	*/	
	function doState(value) { 
	   var state = document.getElementById('state');
		state.value=value;
	}
</script>
</head>
<body>
<html:form action="/Index.do"  method="post">
<input type="hidden" id="state" name="state" value="promptIndex"/>
hello!
id:<input name="id" type="text"/><br/>
age:<input name="age" type="text"/><br/>
name:<input name="name" type="text"/><br/>
school:<input name="school" type="text"/><br/>

<input type="submit" name="state" value="accept" /><br/>
<input type="submit" name="state" value="promptIndex" /><br/>

<input type="submit" value="增加" onclick="document.getElementById('state').value='accept'"/><br/>
<input type="submit" value="首页" onclick="document.getElementById('state').value='promptIndex'"/><br/>

</html:form>
</body>
</html>