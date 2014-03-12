
function toCheckReport(){
	var op_id = document.getElementById('op_id').value;	
	var params = "op_id="+op_id;	
	onSubmitCheckReport("CheckReportServ?"+params);
}

function toCheckPersonalReport(){
	var op_id = document.getElementById('op_id').value;
	var date_type = document.getElementById('date_type').value;	
	var date = document.getElementById('date').value;	
	var params = "op_id="+op_id+"&date_type="+date_type+"&date="+date;	
	onSubmitCheckReport("CheckPersonalReportServ?"+params);
}

function toCheckDeptReport(){
	var dept_id = document.getElementById('dept_id').value;	
	var date_type = document.getElementById('date_type').value;	
	var date = document.getElementById('date').value;	
	var params = "dept_id="+dept_id+"&date_type="+date_type+"&date="+date;	 
	onSubmitCheckReport("CheckDeptReportServ?"+params);
}

function toCheckBankReport(){
	var bank_id = document.getElementById('bank_id').value;	
	var date_type = document.getElementById('date_type').value;	
	var date = document.getElementById('date').value;	
	var params = "bank_id="+bank_id+"&date_type="+date_type+"&date="+date;	
	onSubmitCheckReport("CheckBankReportServ?"+params);
}

function onSubmitCheckReport(url){
	createXMLHttpRequest();
	// 指定处理函数 事件解发器！！！
	http_request.onreadystatechange=ReportReady; 	
	http_request.open("POST",url,true);  
	http_request.send(null); 
}

// 处理返回信息的函数
function ReportReady(){	
	if(http_request.readyState==4){ 
		if(http_request.status==200){
			
			if( http_request.responseText != '' ){
				var reports = eval('('+http_request.responseText+')'); 
				createReportBox(reports);		
			}else{
				createMsgBox(''); 			
			}
		}
	}
}

function createReportBox(reports){
	
	var doc = document.getElementById('content_right');
	var showBox = document.getElementById("Report_box");
	doc.removeChild(showBox);
	
	var msgBox = document.createElement("div");
	msgBox.setAttribute("id","Report_box");
	var styleData = "max-height:380px;overflow:auto;margin-left:20px;";
	
	msgBox.style.cssText = styleData;
	var innerHTML = "";
	//innerHTML += '<div style="margin:10px;padding-top:5px;width:480px;height:350px;background:#ffffff;overflow:auto;">';
	
	//innerHTML += '<div style="color:#008000;font-size:24px;padding-top:15px;">Open Succeed</div>';
	innerHTML += '<table border=1 style="margin:10px 0 0 10px;"><tr><td>ID</td><td>Account</td><td>User</td><td>Content</td><td>Operator</td><td>Time</td></tr>';
	
	for( var i = 0; i < reports.length; ++i ){
		var report = reports[i];
		innerHTML +='<tr>';
		innerHTML +='<td>'+report["id"]+'</td>';
		innerHTML +='<td style="width:10%;">'+report["account"]+'</td>';
		innerHTML +='<td>'+report["user"]+'</td>';
		innerHTML +='<td  style="width:25%;">'+report["content"]+'</td>';
		innerHTML +='<td>'+report["operator"]+'</td>';
		innerHTML +='<td>'+report["time"]+'</td>';
		
		innerHTML +='</tr>';
	}
		
	innerHTML += '</table>';
	msgBox.innerHTML = innerHTML;
	doc.appendChild(msgBox); 
}

function createMsgBox(msg){
	
	var msgBox = document.createElement("div");
	msgBox.setAttribute("id","msg_box");
	var styleData = "position: fixed; z-index: 1000; width: 200px; height:100px;visibility: visible;";
	
	var innerHTML = "";
	innerHTML += '<div style="margin:5px;width:190px;height:90px;background:#ffffff;">';
	if( msg == 'true' ){
		styleData += "background:#008000;";
		innerHTML += '<div style="color:#008000;font-size:18px;padding-top:35px;">Operate Succeed</div>';
	}else if( msg == 'false' || msg == '' ){
		styleData += "background:#cc0000;";
		innerHTML += '<div style="color:#cc0000;font-size:18px;padding-top:35px;">Operate Failed</div>';
	}else{
		styleData += "background:#63bad8;";
		innerHTML += '<div style="font-size:18px;padding-top:35px;">Balance: '+msg+'</div>';	
	}
	innerHTML += '</div>';
	msgBox.style.cssText = styleData;
	msgBox.innerHTML = innerHTML;
		
	document.body.appendChild(msgBox); 
	centerDiv('msg_box');
	
	function removeChild(){
		document.body.removeChild(msgBox); 
	}
	setInterval(removeChild,1500);
}
