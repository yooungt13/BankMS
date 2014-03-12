function toCheckLog(type){
	var ac_id = document.getElementById('ac_id').value;	
	//var identifier = document.getElementById('identifier').value;	
	var password = document.getElementById('pwd').value;	
	var params = "ac_id="+ac_id+"&pwd="+password +"&type="+type;	
	onSubmitCheckLog("CheckLogServ?"+params);
}

function onSubmitCheckLog(url){
	createXMLHttpRequest();
	// 指定处理函数 事件解发器！！！
	http_request.onreadystatechange=logReady; 	
	http_request.open("POST",url,true);  
	http_request.send(null); 
}

// 处理返回信息的函数
function logReady(){	
	if(http_request.readyState==4){ 
		if(http_request.status==200){
			var logs = eval('('+http_request.responseText+')');
			createLogBox(logs);			
		}
	}
}

function createLogBox(logs){
	
	var msgBox = document.createElement("div");
	msgBox.setAttribute("id","log_box");
	var styleData = "position: fixed; z-index: 1000; width: 400px; height:413px;visibility: visible;background:#c5e6ea;";
	
	msgBox.style.cssText = styleData;
	var innerHTML = "";
	innerHTML += '<div style="margin:10px;padding-top:5px;width:380px;height:350px;background:#ffffff;overflow:auto;">';
	
	//innerHTML += '<div style="color:#008000;font-size:24px;padding-top:15px;">Open Succeed</div>';
	innerHTML += '<table border=1 style="margin:10px 0 0 10px;"><tr><td>ID</td><td>Content</td><td>Time</td></tr>';
	
	for( var i = 0; i < logs.length; ++i ){
		var log = logs[i];
		innerHTML +='<tr>';
		innerHTML +='<td>'+log["id"]+'</td>';
		innerHTML +='<td>'+log["content"]+'</td>';
		innerHTML +='<td>'+log["time"]+'</td>';
		innerHTML +='</tr>';
	}
		
	innerHTML += '</table>';
	innerHTML += '</div>';
	
	innerHTML += '<button type="button" class="positive" name="OK" style="margin-left:155px;"onclick="removeMsgBox(\'log_box\');">Confirm</button>';
	
	msgBox.innerHTML = innerHTML;
		
	document.body.appendChild(msgBox); 
	centerDiv('log_box');
	createLayer();
}
