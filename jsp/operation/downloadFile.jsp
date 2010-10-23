<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<%@ page language="java" import="java.io.File"%>
<%@ page language="java" import="java.io.FileInputStream"%>
<%@ page language="java" import="java.io.OutputStream"%>

<%
try {
	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	String fileName = (String)request.getAttribute("fileName");

	String dDirectory = request.getRealPath("/")+"/upload/";
	File downfile = new File(dDirectory+fileName);
	
	byte[] buffer = new byte[1024];
	int len = 0;
	
	FileInputStream inStream = new FileInputStream(downfile);

	System.out.println("filename="+fileName);
	// 解决编码
	String agent = request.getHeader("User-Agent");
	String info = agent.toUpperCase();
	if ((info.indexOf("MSIE")) > -1) {
		System.out.println("ie");
		fileName = java.net.URLEncoder.encode(new String(fileName.getBytes(),"UTF-8"));
		} else {/*   
			if (info.indexOf("NAVIGATOR") < 0 && info.indexOf("FIREFOX") > -1) {   
			} else if ((info.indexOf("OPERA")) > -1) {   
			} else if (info.indexOf("CHROME") < 0  && info.indexOf("SAFARI") > -1) {   
			} else if (info.indexOf("CHROME") > -1) {   
			} else if (info.indexOf("NAVIGATOR") > -1) {   
			} else {   
			}*/
			System.out.println("firefxo");
			fileName = new String(fileName.getBytes(),"ISO-8859-1");
		}
	response.reset();
	response.setContentType("application/x-msdownload;charset=utf-8");
	response.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
	
	OutputStream outputStream =response.getOutputStream();
	
	while ((len = inStream.read(buffer)) > 0) {
		outputStream.write(buffer, 0, len);
	}
	
	inStream.close();

	outputStream.flush();
	outputStream.close();
	outputStream=null;
	response.flushBuffer();
	out.clear();
	out = pageContext.pushBody();
	
} catch (Exception ex) {
	ex.printStackTrace();
	throw new ServletException(ex);
}
%>