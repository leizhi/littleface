<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>

<c:url value="/File.do" var="fileDownload">
	<c:param name="method">download</c:param>
</c:url>

<form action="${fileDownload }" method="post">
<table>
<caption>文件信息</caption>

<tbody>
<tr>
<td style="text-align:right;">名称</td>
<td>${fileInfo.name} 
<input type="hidden" name="fileInfo.id" value="${fileInfo.id }"/>
<input type="hidden" name="fileName" value="${fileInfo.filepath }"/>
</td>
</tr>

<tr>
<td style="text-align:right;">上传日期</td>
<td>${fileInfo.datetime}</td>
</tr>

<tr>
<td colspan="2" style="text-align:center;">
	<input type="submit" value="下载">
</td>
</tr>

 
</tbody>

</table>
</form>