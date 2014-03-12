<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Welcome to Bank Manage System.</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="./css/styles.css" type="text/css" media="screen" rel="stylesheet" />
    <link href="./css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
   	<link href="./css/keyboard.css" type="text/css"  rel="stylesheet" />
   	<script src="./js/index.js" type="text/javascript" ></script>
    <script src="./js/jquery-1.6.2.min.js"></script>
    <script src="./js/jquery-ui-1.8.16.custom.min.js"></script>
    <script src="./js/jquery.keyboard.extension-typing.js" type="text/javascript" ></script>
    <script src="./js/jquery.keyboard.js" type="text/javascript" ></script>
    
    <script type="text/javascript">
        $(document).ready(function() {
            $('#user_password').keyboard({
                openOn: null,
                stayOpen: true,
                layout: 'qwerty'
            }).addTyping();
            $('#passwd').click(function() {
                $('#user_password').getkeyboard().reveal();
            });

            $(".logininput").blur(function() {
                if ($(this).val() == "") {
                    $(this).css("border-color", "red");
                                    }
                else
                    $(this).css("border-color", "#D9D6C4");
            });

            $("#loginbtn").click(function() {
                var k = 0;
                var ajaxhtml = "";
                $(".logininput").each(function(i, obj) {
                    if ($(obj).val().trim() == "") {
                        k++;
                        $(this).css("border-color", "red");
                        $(this).focus();
                        return false;
                    }
                });
                if (k != 0) return;
                ajaxhtml = $("#loginbtn").html();
                $("#loginbtn").html("Loading....  <img src='css/images/loading.gif' alt='Announcement' /> ");
                $("#loginbtn").attr("disabled", "disabled");
                
				var form = document.getElementById("loginForm");
				form.submit();
            });
        });
        </script>
  </head>
  <body id="login">
    <div id="wrappertop">
    </div>
    <div id="wrapper">
        <div id="content">
            <div id="header">
                <h1>
                    <a href=""></a></h1>
            </div>
            <div id="darkbanner" class="banner320">
                <h2>
                    Bank Manage  System</h2>
            </div>
            <div id="darkbannerwrap">
            </div>
            <form id="loginForm"  method="post" name="login"  action="LoginServ"   autocomplete="off">
            <fieldset class="form">
                <p>
                    <label class="loginlabel" >
                        LoginID:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="loginid"
                        id="loginid" type="text" value="" />
                </p>
                <p>
                    <label class="loginlabel" for="user_password">
                        Password:</label>
                    <span>
                        <input class="logininput"   name="pwd"  id="user_password" type="password" /><img
                            id="passwd" class="tooltip" alt="Click to open the virtual keyboard" title="Click to open the virtual keyboard"
                            src="css/images/keyboard.png" /></span>
                </p>
                
                
                <button id="loginbtn"  type="button" class="positive" name="Submit">
                    <img src="css/images/key.png" alt="Announcement" />Login</button>
                
                <button id="declaration"  type="button" class="positive" name="Submit" onclick="openDeclaration()">Declaration</button>                              
            </fieldset>
			</form>
        </div>
    </div>
    <div id="wrapperbottom_branding">
        <div id="wrapperbottom_branding_text">
			<br>
            Copyright © <a href="http://weibo.com/yooungt"><font color="#63bad8">有田十三</font></a> 2013  All Rights Reserved.
            <br><br>
        </div>
    </div>
   	 
</body>

</html>
