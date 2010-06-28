//a:output field name
//b:year field name or all field name (when not splited)
//c:month field name
//d:day field name

// <a href=# onclick="javascript:setParams('null', 'fieldName');"><img src=images/miniDate.gif></a>

        function setParams(b,c,d){
//        popRemote = window.open("../../incl/calendar.jsp","Remote", "width=220,height=252,scrollbars");
        document.forms[0].fieldName.value = null;
        if (setParams.arguments.length < 3){
        document.forms[0].fieldName1.value = b;
        document.forms[0].fieldName2.value = '';
        document.forms[0].fieldName3.value = '';
        }
        else{
        document.forms[0].fieldName1.value = b;
        document.forms[0].fieldName2.value = c;
        document.forms[0].fieldName3.value = d;
        }

        var today = new Date();
        m = today.getMonth();
        var y = today.getYear();
        if(y < 1000){
        y += 1900;
        }
        document.forms[0].month.value = m;
        document.forms[0].year.value = y;
        }

var htm = '<input type="hidden" name="year"><input type="hidden" name="month">';
htm += '<input type="hidden" name="fieldName"><input type="hidden" name="fieldName1">';
htm += '<input type="hidden" name="fieldName2"><input type="hidden" name="fieldName3">';
htm += '<input type="hidden" name="null">';
document.write(htm);

        var today = new Date();
        m = today.getMonth();        var y = today.getYear();
        if(y < 1000){
        y += 1900;
        }
        document.forms[0].month.value = m;
        document.forms[0].year.value = y;
