<%@ page import="java.util.*" %>
<%@ page import="com.manihot.xpc.tools.*" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<form action="#">
hello!
age:<input name="age" type="text"/>
name:<input name="name" type="text"/>
school:<input name="school" type="text"/>
<%
SampleBean bean = new SampleBean();
if(request.getParameter("age") != null)
ParamUtil.buildAddSQL(request,"EXAMPLE");

ParamUtil.bindData(request, bean);
//ParamUtil.add(request,"EXAMPLE");
out.println("Age = " + bean.getAge());
//out.println("Department = " + bean.getDepartment());
out.println("Name = " + bean.getName());
out.println("School = " + bean.getSchool());

Enumeration en = request.getParameterNames();
String name;
String value;

while(en.hasMoreElements()){
	name = (String) en.nextElement();
	value = (String) request.getParameter(name);
	out.println(name+"="+value);
}
%>
<input type="submit"/>
</form>
</body>
</html>