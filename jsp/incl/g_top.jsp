<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div id="gtop" style="width: 100%;height: 20px;text-align: left;color: #add2da;font-size: 12px;">
<script language="javascript">

function getUrl(query,key){
	var str = new String(query);
	var value = str.substr(1);
	var array = value.split('&');
	
	for(var i=0;i<array.length;i++){
		var kv = array[i].split('=');
		
		if(kv[0]==key){
			return kv[1];
		}
	}
}

function parseURL(url) {
	var a =  document.createElement('a');
	//创建一个链接
	a.href = url;
	return {
	source: url,
	protocol: a.protocol.replace(':',''),
	host: a.hostname,
	port: a.port,
	query: a.search,
	params: (function(){
	var ret = {},
	seg = a.search.replace(/^\?/,'').split('&'),
	len = seg.length, i = 0, s;
	for (;i<len;i++) {
	if (!seg[i]) { continue; }
	s = seg[i].split('=');
	ret[s[0]] = s[1];
	}
	return ret;
	})(),
	file: (a.pathname.match(/\/([^\/?#]+)$/i) || [,''])[1],
	hash: a.hash.replace('#',''),
	path: a.pathname.replace(/^([^\/])/,'/$1'),
	relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [,''])[1],
	segments: a.pathname.replace(/^\//,'').split('/')
	};
	}

	var myURL = parseURL(document.URL);
		myURL.file;     // = 'index.html'
		myURL.hash;     // = 'top'
		myURL.host;     // = 'abc.com'
		myURL.query;    // = '?id=255&m=hello'
		myURL.params;   // = Object = { id: 255, m: hello }
		myURL.path;     // = '/dir/index.html'
		myURL.segments; // = Array = ['dir', 'index.html']
		myURL.port;     // = '8080'
		myURL.protocol; // = 'http'
		myURL.source;   // = 'http://abc.com:8080/dir/index.html?id=255&m=hello#top'

		//document.write(myURL.query+"<br>");
		
	//var barIndex=getUrl(myURL.query,"bar");
	//var subbarIndex=getUrl(myURL.query,"subbar");
	var barIndex=${param.bar };
	var subbarIndex=${param.subbar };
	//document.write("<input type=\"hidden\" name=\"bar\" id=\"bar\" value=\""+barIndex+"\"/>");
	//document.write("<input type=\"hidden\" name=\"subbar\"  id=\"subbar\" value=\""+subbarIndex+"\"/>");
	
	//document.write("barIndex:" + barIndex + " subbarIndex:"+subbarIndex);
	
	var fullPackage =	 new String(myURL.segments);
	var level = fullPackage.split(",");
	
	var prefix = new Array();
	
	var bar = new Array("文件","配偶","帐户");
	
	var subbar = new Array( 
			new Array("文件","码表类型"),
			new Array("搜索","博客"),
			new Array("基本信息","密 码")
			);
	
	for(var i=0;i<prefix.length;i++){
		//document.write("-><a  href=\""+ level[i] + "\">" + prefix[i] + "</a>");
		document.write("-><a  href=\"\">" + prefix[i] + "</a>");
	}
	
	//document.write("-><a  href=\""+ level[prefix.length] + "\">" + bar[barIndex] + "</a>");
	//document.write("-><a  href=\""+ level[++prefix.length] + "\">" + subbar[barIndex][subbarIndex] + "</a>");
	document.write("-><a  href=\"\">" + bar[barIndex] + "</a>");
	document.write("-><a  href=\"\">" + subbar[barIndex][subbarIndex] + "</a>");
	
	/*
	for(var i=prefix.length;i<level.length;i++){
		
			document.write("-><a  href=\""+ level[i] + "\">" + bar[barIndex] + "</a>");
			document.write("-><a  href=\""+ level[i] + "\">" + subbar[barIndex][subbarIndex] + "</a>");
	}
	*/
	
</script>
<input type="hidden" name="bar" value="${param.bar }">
<input type="hidden" name="subbar" value="${param.subbar }">
</div>