function createLayer(){ 
	var mybg = document.createElement("div");  
	mybg.setAttribute("id","mybg");  
	mybg.style.background = "#000";  
	mybg.style.width = "100%";  
	mybg.style.height = "100%";  
	mybg.style.position = "fixed";  
	mybg.style.top = "0";  
	mybg.style.left = "0";  
	mybg.style.zIndex = "810";  
	mybg.style.opacity = "0.2";  
	mybg.style.filter = "Alpha(opacity=30)";  
	document.body.appendChild(mybg);  
}  

function removeLayer(){
	
	var mybg = document.getElementById('mybg');
	document.body.removeChild(mybg);
}

function centerDiv(id){
	var width = document.documentElement.clientWidth;
	var height = document.documentElement.clientHeight;
	var left = ( width - document.getElementById(id).offsetWidth ) / 2;
	var top = ( height - document.getElementById(id).offsetHeight ) / 2;

	document.getElementById(id).style.left = left + 'px'; 
	document.getElementById(id).style.top = top + 'px'; 
}

function removeMsgBox(id){
	var msgbox = document.getElementById(id);
	document.body.removeChild(msgbox);
	removeLayer();
}

function openDeclaration(){
	var open_box = document.createElement("div");
	open_box.setAttribute("id","open_box");  
			
	var styleData = "position: fixed; z-index: 850; visibility: visible; width:350px; height:200px;background:#c5e6ea;";
	open_box.style.cssText = styleData;
	//tsmit_box.setAttribute("style","position: fixed; z-index: 850; visibility: visible;");  			
			
	var htmlList = '';
	
	htmlList += '<div  style="text-align: left;margin:10px;width:330px;height:150px;background:#ffffff;padding-top:30px;">		';
   	htmlList += '<b style="text-align: left;font-size:20px;margin-left:40px;">Declaration</b><br>';
   	htmlList += '<br><span style="margin-left:40px;"">Even though there\'s no declaration to declare,</span><br>'
   	htmlList += '<span style="margin-left:40px;"">I still want to say, "<b>Holly Shit!"</b>.</span><br><br><br>'
   	htmlList += '<button type="button" class="positive" name="Submit" style="margin-left:40px;"  onclick="removeMsgBox(\'open_box\');">Agree</button>';
    htmlList += '<button type="button" class="positive" name="Cancel" style="margin-left:20px;" onclick="alert(\'You can choose another.\');">Disagree</button>';  
   	htmlList += '</div>';
	
	open_box.innerHTML = htmlList;
	document.body.appendChild(open_box); 
	centerDiv('open_box');
	createLayer();
	
}

//创建请求
var http_request;
function createXMLHttpRequest(){
	http_request=false;
    if(window.XMLHttpRequest){
		http_request=new XMLHttpRequest();  //初始化http_request
		if(http_request.overrideMimeType){
			http_request.overrideMimeType("text/html");
		}
	}else if(window.ActiveXObject){      
		try{
			http_request=new ActiveXObject("Msxml2.XMLHTTP");  //在IE中创建XMLHttpRequest对象,新版IE
		}catch(e){
			try{
				http_request=new ActiveXObject("Microsoft.XMLHTTP");  //在IE中创建XMLHttpRequest对象旧版IE
			}catch(e){}
		}
	}
		
	if(!http_request){
		window.alert("不能创建XMLHttpRequest对象实例");
		return false;
	}
}

