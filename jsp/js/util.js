function refreshParent() {
window.opener.location.href = window.opener.location.href;
//window.parent.location.reload();
window.opener.location.reload();
//window.self.opener.location.reload();
if (window.opener.progressWindow) {
    window.opener.progressWindow.close();
    }
 window.close();
} 

function fiter(onclick) {
	var txt = window.document.getElementById("pager.pagerMethod");
 txt.value = onclick.name;
	//window.alert(txt.value);
}

function href(action) {
	window.location.href=action;
}

function onlyNum()
{
 //第一个if定义除数字外可响应的键,如46对应Delete键,若要响应Tab键,可在后面加上&&!(event.keyCode==9),若要允许输入小数点,则可加上&&!(event.keyCode==190),其它类似
 if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
 //第二个if定义要响应的数字键,||前面的是响应左边键盘对应的数字,后面是响应小键盘上的数字
  if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}

function confirmSubmit(obj) {
	 if(confirm("你确定删除吗？")){
		 obj.submit();
	 }
}

function isChecked(){
	if ($("chooseAll").checked) {
		$$('input[type="checkbox"]').each(function(element){
				element.checked = true; // 全选
				});
	} else {
		$$('input[type="checkbox"]').each(function(element){
			element.checked = false; // 取消全选
			});		
	}
}

function readonly(){
	$$('input').each(function(element){
		element.readOnly = true; // 全选
		});
	$$('select').each(function(element){
		element.readOnly = true; // 全选
		});
}
//alert("ok");

function viewStyle(){
	$("form input").each(function(){
		if($(this).attr('type') == 'text'){
			$(this).parent().html('<span>' + $(this).val() +'</span>');
		}else if($(this).attr('type') == 'radio'){
			if($(this).attr('checked')){
				$(this).parent().html('<span>' + $(this).val() +'</span>');
			}
		}
	});
	
	$("form span").each(function(){
		if($(this).html() == "*")
			$(this).html('');
	});
	
	$("form #save").parent().html('');
}

function jumpPage(formId, pageNo,totalPage){
if(pageNo > totalPage){
	alert("最大页数为" + totalPage + "页，请重新输入！");
}else{
	$("#pageNo").val(pageNo);
	$("#"+formId).submit();
}
}

function search(formId){
$("#order").val("");
$("#orderBy").val("");
$("#pageNo").val("1");

$("#"+formId).submit();
}
//var targetText;
 
function openLookup(myurl,targetText) {
var newWindow;
var props = 'scrollBars=yes,resizable=yes,toolbar=no,menubar=no,location=no,directories=no,width=500,height=400';
newWindow = window.open(myurl, "Add_from_Src_to_Dest", props);
newWindow.focus();
//this.targetText = targetText;
}

function submitPage(pageNo){
		$('pageNo').value=pageNo;
		document.forms[0].submit();
}

function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}