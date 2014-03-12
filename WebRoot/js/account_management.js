
var pwd;

function onSubmitOP(url){	
	createXMLHttpRequest();
	// 指定处理函数 事件解发器！！！
	http_request.onreadystatechange=processOP; 	
	http_request.open("POST",url,true);  
	http_request.send(null); 
}

// 处理返回信息的函数
function processOP(){	
	if(http_request.readyState==4){ 
		if(http_request.status==200){
			var msg = http_request.responseText;
			createShowBox(msg);
		}
	}
}

function toSubmitAccount(){
	var  name = document.getElementById('username').value;	
	var  id = document.getElementById('identifier').value;	
	var  at = document.getElementById('account_type').value;	
	var  ut = document.getElementById('user_type').value;	
	var  bl = document.getElementById('balance').value;	
	pwd = document.getElementById('pwd').value;	
	var params = "identifier="+id+"&username="+name+"&balance="+bl+"&account_type="+at+"&user_type="+ut+"&pwd="+pwd;	
	
	onSubmitOP("AccountServ?"+params);
}

function createShowBox(msg){
	
	var msgBox = document.createElement("div");
	msgBox.setAttribute("id","op_msg");
	var styleData = "position: fixed; z-index: 1000; width: 300px; height:200px;visibility: visible;background:#c5e6ea;";
	
	msgBox.style.cssText = styleData;
	var innerHTML = "";
	innerHTML += '<div style="margin:10px;width:280px;height:180px;background:#ffffff;">';
	if( msg != 0 ){
		innerHTML += '<div style="color:#008000;font-size:24px;padding-top:15px;">Open Succeed</div>';
		innerHTML += '<table style="margin:10px 0 0 40px;"><tr><td>Account ID: </td><td>'+msg+'</td></tr>';
		innerHTML += '<tr><td>Your Password:</td><td>'+pwd+'</td></tr></table>';
	}else{
		innerHTML += '<div style="color:#cc0000;font-size:24px;padding-top:50px;padding-bottom:50px;">Open Failed</div>';
	}
	innerHTML += '<button type="button" class="positive" name="Cancel" style="margin-top:2px;margin-left:100px;"onclick="removeMsgBox(\'op_msg\');">Confirm</button>';
	innerHTML += '</div>';
	
	msgBox.innerHTML = innerHTML;
		
	document.body.appendChild(msgBox); 
	centerDiv('op_msg');
	createLayer();
}

function toSave(){
	var  id = document.getElementById('ac_id').value;	
	var password = document.getElementById('pwd').value;	
	var  cash = document.getElementById('cash').value;	
	var params = "ac_id="+id+"&pwd="+password+"&cash="+cash;	
	onSubmit("SaveServ?"+params);	
}

function toWithdraw(){
	var  id = document.getElementById('ac_id').value;	
	var  password = document.getElementById('pwd').value;	
	var  cash = document.getElementById('cash').value;	
	var params = "ac_id="+id+"&pwd="+password+"&cash="+cash;	
	onSubmit("WithdrawServ?"+params);	
}

function toTransfer(){
	var ac_id = document.getElementById('ac_id').value;	
	var password = document.getElementById('pwd').value;	
	var identifier = document.getElementById('identifier').value;	
	var name = document.getElementById('username').value;	
	var target_id = document.getElementById('target_id').value;	
	var target_name = document.getElementById('target_name').value;	
	var amount = document.getElementById('amount').value;	
	
	var params = "ac_id="+ac_id+"&pwd="+password+"&identifier="+identifier+"&username="+name+"&target_id="+target_id+"&target_name="+target_name+"&amount="+amount;	
	onSubmit("TransferServ?"+params);	
}

function toClose(){
	var  id = document.getElementById('id').value;	
	var password = document.getElementById('pwd').value;	
	var identifier = document.getElementById('identifier').value;	
	var params = "id="+id+"&pwd="+password+"&identifier="+identifier;	
	onSubmit("CloseServ?"+params);	
	
}

function toCpwd(){ 
	var ac_id = document.getElementById('ac_id').value;	
	var identifier = document.getElementById('identifier').value;	
	var old_pwd = document.getElementById('old_pwd').value;	
	var new_pwd = document.getElementById('new_pwd').value;		
	var params = "ac_id="+ac_id+"&identifier="+identifier+"&old_pwd="+old_pwd+"&new_pwd="+new_pwd;	
	onSubmit("CpwdServ?"+params);	
}

function toCheck(){ 
	var ac_id = document.getElementById('ac_id').value;	
	var identifier = document.getElementById('identifier').value;	
	var password = document.getElementById('pwd').value;	
	var params = "ac_id="+ac_id+"&identifier="+identifier+"&pwd="+password;	
	onSubmit("CheckServ?"+params);	
}

function onSubmit(url){
	createXMLHttpRequest();
	// 指定处理函数 事件解发器！！！
	http_request.onreadystatechange=process; 	
	http_request.open("POST",url,true);  
	http_request.send(null); 
}

// 处理返回信息的函数
function process(){	
	if(http_request.readyState==4){ 
		if(http_request.status==200){
			var msg = http_request.responseText;
			createMsgBox(msg);
		}
	}
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

function createSuper(){
	var open_box = document.createElement("div");
	open_box.setAttribute("id","open_box");  
			
	var styleData = "position: fixed; z-index: 850; visibility: visible; width:500px; height:440px;background:#c5e6ea;";
	open_box.style.cssText = styleData;
	//tsmit_box.setAttribute("style","position: fixed; z-index: 850; visibility: visible;");  			
			
	var htmlList = '';
	
	htmlList += '<div  style="margin:10px;width:480px;height:390px;background:#ffffff;padding-top:30px;">		';
	htmlList += '<form class="form">';
   	htmlList += '<span style="font-size:20px;margin:20px 0 0 -20px;">Please input the infomation to create super.</span>';
    htmlList += '<table style="margin:20px 0 10px 30px;">';
    htmlList += '<tr>';
    htmlList += '<td>Account ID:</td>';
    htmlList += '<td><input id="ac_id" type="text" /></td>';
    htmlList += '<tr>';
    htmlList += '<td>Username:</td>';
    htmlList += '<td><input id="curr_name" type="text" /></td>';
    htmlList += '</tr>';
    htmlList += '<tr>';
    htmlList += '<td>Identifier:</td>';
    htmlList += '<td><input id="curr_id" type="text" /></td>';
    htmlList += '</tr>';
    htmlList += '</tr>';
    htmlList += '<tr>';
    htmlList += '<td>Your Password:</td>';
    htmlList += '<td><input id="curr_pwd" type="password" /></td>';
    htmlList += '</tr>';
    htmlList += '<tr>';
    htmlList += '<td>Super ID:</td>';
    htmlList += '<td><input id="user_id" type="text" /></td>';
    htmlList += '</tr></table></form>';
    htmlList += '<button type="button" class="positive" name="Submit" style="margin-left:40px;" onclick="toCreateSuper();">Submit</button>';
    htmlList += '<button type="button" class="positive" name="Cancel" style="margin-left:40px;" onclick="removeMsgBox(\'open_box\');">Cancel</button>';
    htmlList += '</div>';
	
	open_box.innerHTML = htmlList;
	document.body.appendChild(open_box); 
	centerDiv('open_box');
	createLayer();
	
}

function toCreateSuper(){
	var ac_id = document.getElementById('ac_id').value;	
	var curr_name = document.getElementById('curr_name').value;	
	var curr_id = document.getElementById('curr_id').value;	
	var curr_pwd = document.getElementById('curr_pwd').value;	
	var user_id = document.getElementById('user_id').value;	
	var params = "ac_id="+ac_id+"&user_name="+curr_name+"&identifier="+curr_id+"&pwd="+curr_pwd+"&user_id="+user_id;	
	alert(params);
	onSubmit("CreateSuperServ?"+params);	
}

function onSubmitCreateSuper(url){
	createXMLHttpRequest();
	// 指定处理函数 事件解发器！！！
	http_request.onreadystatechange=createProcess; 	
	http_request.open("POST",url,true);  
	http_request.send(null); 
}

// 处理返回信息的函数
function createProcess(){	
	if(http_request.readyState==4){ 
		if(http_request.status==200){
			var 	msg = eval('('+http_request.responseText+')'); 
			createMsgBox(msg); 			
		}
	}
}