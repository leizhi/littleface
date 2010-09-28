<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.sql.*,java.util.*"%>
<%@ page import="com.mooo.mycoz.db.pool.DbConnectionManager"%>

<%!public String chomp(String str, String separator) {
		if (str == null || str.length() == 0 || separator == null) {
			return str;
		}
		if (str.endsWith(separator)) {
			return str.substring(0, str.length() - separator.length());
		}
		return str;
	}%>
5591416
<%!public String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(
				Character.toUpperCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}%>
<%
	String tableName = request.getParameter("t");
	if (tableName == null || "".equals(tableName)) {
		out.println("参数t");
		return;
	}
%>

<%
/*
	Connection conn;
	String DBUser = "ww";
	String DBPassword = "ww";
	String DBServer = "192.168.0.3"; // Can't use localhost , you must use IP or CNAME 
	String DBNAME = "khdb"; // change to your db name 
	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	//connect to the database 
	conn = java.sql.DriverManager.getConnection(
			"jdbc:oracle:thin:@192.168.0.3:1521:khdb", DBUser,
			DBPassword);
*/
	Connection conn = DbConnectionManager.getConnection();

	String sql = "select * from " + tableName;// change to your table name 

	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	ResultSetMetaData meta = rs.getMetaData();

	// tableName=meta.getTableName(1); 

	int count = meta.getColumnCount();
	ArrayList AutoIncrementFields = new ArrayList();
	String[] ColumnNames = new String[count];
	String ColumnClassNames[] = new String[count];

	for (int i = 0; i < count; i++) {
		if (meta.isAutoIncrement(i + 1)) {
			AutoIncrementFields.add(new Integer(i));
		}

		ColumnNames[i] = meta.getColumnName(i + 1);
		ColumnClassNames[i] = meta.getColumnClassName(i + 1);

	}

	rs.close();
	stmt.close();
	conn.close();//改成自己的数据库连接释放 

	HashMap m = new HashMap();

	m.put("java.lang.Boolean", "Boolean");
	//m.put("java.lang.Integer","Integer"); 
	m.put("java.lang.Integer", "Int");
	m.put("java.lang.Long", "Long");
	m.put("java.math.BigDecimal", "BigDecimal");
	m.put("jjava.lang.Float", "Float");
	m.put("java.lang.Double", "Double");
	m.put("java.lang.String", "String");
	m.put("java.sql.Date", "Date");
	m.put("java.sql.Time", "Time");
	m.put("java.sql.Timestamp", "Timestamp");
	m.put("java.lang.Object", "Object");

	StringBuffer select = new StringBuffer("select ");
	StringBuffer insert1 = new StringBuffer("insert into ");
	insert1.append(tableName);
	insert1.append("(");
	StringBuffer insert2 = new StringBuffer(" )values( ");
	StringBuffer update = new StringBuffer(" update ");
	update.append(tableName);
	update.append(" set ");

	StringBuffer insertp1 = new StringBuffer(insert1.toString());
	StringBuffer insertp2 = new StringBuffer(insert2.toString());
	StringBuffer updatep = new StringBuffer(update.toString());

	for (int i = 0; i < count; i++) {
		select.append(ColumnNames[i]);
		select.append(",");
		if (!AutoIncrementFields.contains(new Integer(i))) {
			insert1.append(ColumnNames[i]);
			insert1.append(",");
			insertp1.append(ColumnNames[i]);
			insertp1.append(",");
			insert2.append("'\"+");
			insert2.append(ColumnNames[i]);
			insert2.append("+\"'");
			insert2.append(",");
			insertp2.append("?");
			insertp2.append(",");
			update.append(ColumnNames[i]);
			update.append(" = '\"+");
			update.append(ColumnNames[i]);
			update.append("+\"',");
			updatep.append(ColumnNames[i]);
			updatep.append(" = ");
			updatep.append("?");
			updatep.append(",");
		}
	}

	select = new StringBuffer(chomp(select.toString(), ","));
	select.append(" from ");
	select.append(tableName);
	select.append("");

	insert1 = new StringBuffer(chomp(insert1.toString(), ","));
	insert2 = new StringBuffer(chomp(insert2.toString(), ","));
	insert2.append("')");
	insert1.append(insert2);

	update = new StringBuffer(chomp(update.toString(), ","));
	update.append(" where ");
	if (AutoIncrementFields.size() > 0) {
		update
				.append(ColumnNames[((Integer) AutoIncrementFields
						.get(0)).intValue()]);
		update.append(" = '\"+");
		update
				.append(ColumnNames[((Integer) AutoIncrementFields
						.get(0)).intValue()]);
		update.append("+\"'");
	}
	insertp1 = new StringBuffer(chomp(insertp1.toString(), ","));
	insertp2 = new StringBuffer(chomp(insertp2.toString(), ","));
	insertp2.append(")");
	insertp1.append(insertp2);

	updatep = new StringBuffer(chomp(updatep.toString(), ","));
	updatep.append(" where ");
	if (AutoIncrementFields.size() > 0) {
		updatep.append(ColumnNames[((Integer) AutoIncrementFields
				.get(0)).intValue()]);
		updatep.append(" = ");
		updatep.append("?");
	}
%>
<html>
<head>
<title>sql语句生成啦</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<p>表 <%=tableName%> 共 <%
	out.print(count);
	out.print(" 个字段 ");

	Iterator iterator = AutoIncrementFields.iterator();
	int j = AutoIncrementFields.size();
	while (iterator.hasNext()) {
		j--;
		if (j == AutoIncrementFields.size() - 1) {
			out.print("其中 ");
		}
		int i = ((Integer) iterator.next()).intValue();
		out.print(ColumnNames[i]);
		if (j != 0) {
			out.print(" , ");
		} else {
			out.print(" 是 AutoIncrement 类型不出现在sql语句中");
		}
	}
%> <%
 	out.print("<br>");
 	out.println(select);
 	out.print("<br>");
 	out.print("<br>");
 	out.println(insert1);
 	out.print("<br>");
 	out.print("<br>");
 	out.println(update);

 	out.print("<br>");
 	out.print("<br>");
 	out.println(insertp1);

 	out.print("<br>");
 	out.print("<br>");
 	out.println(updatep);
 %>
</p>
<hr>
<p>
<%
	for (int i = 0; i < count; i++) {
		out.print("private ");
		out.print(ColumnClassNames[i]);
		out.print(" ");
		out.print(ColumnNames[i]);
		out.println(";<br>");
	}
%> <%
 	out.println("<br>");
 	for (int i = 0; i < count; i++) {
 		out.print("public void set");
 		out.print(capitalize(ColumnNames[i]));
 		out.print("(");
 		out.print(ColumnClassNames[i]);
 		out.print(" ");
 		out.print(ColumnNames[i]);
 		out.print("){");
 		out.println("<br>");
 		out.print("this.");
 		out.print(ColumnNames[i]);
 		out.print("=");
 		out.print(ColumnNames[i]);
 		out.print("; }");
 		out.println("<br>");
 		out.print("public ");
 		out.print(ColumnClassNames[i]);
 		out.print(" get");
 		out.print(capitalize(ColumnNames[i]));
 		out.print("() {");
 		out.println("<br>");
 		out.print("return ");
 		out.print(ColumnNames[i]);
 		out.println(";}<br>");

 	}
 %>
</p>
<hr>
<p>
<%
	for (int i = 0; i < count; i++) {
		out.print(ColumnNames[i]);
		out.print(" = ");
		out.print(tableName);
		out.print(".get");
		out.print(capitalize(ColumnNames[i]));
		out.println("();<br>");
	}
%>
</p>
<hr>
<p>ParameterParser parser = new ParameterParser(request); <br />
<%
	for (int i = 0; i < count; i++) {
		out.print("String ");
		out.print(ColumnNames[i]);
		out.print(" = ");
		out.print("parser.getStringParameter(\"");
		out.print(ColumnNames[i]);
		out.print("\",\"\");");
		out.println("<br>");
	}
%>
</p>
<hr>

<p>
<%
	for (int i = 0; i < count; i++) {
		out.print("String ");
		out.print(ColumnNames[i]);
		out.print(" = ");
		out.print("request.getParameter(\"");
		out.print(ColumnNames[i]);
		out.print("\");");
		out.println("<br>");
	}
%>
</p>
<hr>
<pre> 
Connection conn = ConnectionManager.getConnection(); 

PreparedStatement stmt = null; 
StringBuffer sql = new StringBuffer(); 
sql.append("<%
	out.print(insertp1);
%>"); 
try { 
stmt = conn.prepareStatement(sql.toString()); 
</pre>
<%
	int jj = 0;
	for (int i = 0; i < count; i++) {
		if (!AutoIncrementFields.contains(new Integer(i))) {
			jj++;
			out.print("stmt.set");
			out.print(m.get(ColumnClassNames[i]));
			out.print("(");
			out.print(jj);
			out.print(",");
			out.print(ColumnNames[i]);
			out.print(");<br>");
		}
	}
%>
<pre> 
int rows = stmt.executeUpdate(); 
} 

} 
catch (SQLException e) { 
throw e; 
} 

finally { 
if (stmt != null) { 
stmt.close(); 
} 

if (conn != null) { 
ConnectionManager.colse(conn); 
} 
} 
</pre>
<hr>
<pre> 
Connection conn = ConnectionManager.getConnection(); 

PreparedStatement stmt = null; 
StringBuffer sql = new StringBuffer(); 
sql.append("<%
	out.print(updatep);
%>"); 
try { 
stmt = conn.prepareStatement(sql.toString()); 
</pre>
<%
	int jjj = 0;
	for (int i = 0; i < count; i++) {
		if (!AutoIncrementFields.contains(new Integer(i))) {
			jjj++;
			out.print("stmt.set");
			out.print(m.get(ColumnClassNames[i]));
			out.print("(");
			out.print(jjj);
			out.print(",");
			out.print(ColumnNames[i]);
			out.print(");<br>");
		}
	}

	if (AutoIncrementFields.size() > 0) {
		jjj++;
		out.print("stmt.set");
		out.print(m.get(ColumnClassNames[((Integer) AutoIncrementFields
				.get(0)).intValue()]));
		out.print("(");
		out.print(jjj);
		out.print(",");
		out.print(ColumnNames[((Integer) AutoIncrementFields.get(0))
				.intValue()]);
		out.print(");<br>");
	}
%>
<pre> 
int rows = stmt.executeUpdate(); 
} 

} 
catch (SQLException e) { 
throw e; 
} 

finally { 
if (stmt != null) { 
stmt.close(); 
} 

if (conn != null) { 
ConnectionManager.colse(conn); 
} 
} 
</pre>
<hr>
<pre> 
&lt;table width="90%" border="0" align="center" cellpadding="1" cellspacing="1"&gt; 
&lt;form name="form1" method="post" action=""&gt; 
</pre>
<%
	for (int i = 0; i < count; i++) {
		if (!AutoIncrementFields.contains(new Integer(i))) {
			out.print("&lt;tr&gt;");
			out.print("&lt;td&gt;");
			out.print(ColumnNames[i]);
			out.print("&lt;/td&gt;");
			out.print("&lt;td&gt;");
			out.print("&lt;input type=\"text\" name=\"");
			out.print(ColumnNames[i]);
			out.print("\"&gt;");
			out.print("&lt;/td&gt;");
			out.print("&lt;/tr&gt;");
			out.println("<br>");
		}
	}
	/* if (AutoIncrementFields.size()>0) 
	 { 
	 out.print("&lt;input type=\"hidden\" name=\""); 
	 out.print(ColumnNames[((Integer)AutoIncrementFields.get(0)).intValue()]); 
	 out.print("\"&gt;"); 
	 }*/
%>
<pre> 
&lt;/form&gt; 
&lt;/table&gt; 
</pre>
<hr>
<pre> 
&lt;table width="90%" border="0" align="center" cellpadding="1" cellspacing="1"&gt; 
&lt;form name="form1" method="post" action=""&gt; 
</pre>
<%
	for (int i = 0; i < count; i++) {
		if (!AutoIncrementFields.contains(new Integer(i))) {
			out.print("&lt;tr&gt;");
			out.print("&lt;td&gt;");
			out.print(ColumnNames[i]);
			out.print("&lt;/td&gt;");
			out.print("&lt;td&gt;");
			out.print("&lt;input type=\"text\" name=\"");
			out.print(ColumnNames[i]);
			out.print("\" value=\"&lt;%=");
			out.print(ColumnNames[i]);
			out.print("%&gt;\"&gt;");
			out.print("&lt;/td&gt;");
			out.print("&lt;/tr&gt;");
			out.println("<br>");
		}
	}
	if (AutoIncrementFields.size() > 0) {
		out.print("&lt;input type=\"hidden\" name=\"");
		out.print(ColumnNames[((Integer) AutoIncrementFields.get(0))
				.intValue()]);
		out.print("\" value=\"&lt;%=");
		out.print(ColumnNames[((Integer) AutoIncrementFields.get(0))
				.intValue()]);
		out.print("%&gt;\"&gt;");
	}
%>
<pre> 
&lt;/form&gt; 
&lt;/table&gt; 
</pre>
</body>
</html>
