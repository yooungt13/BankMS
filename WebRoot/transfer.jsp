<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	titan.bean.Login login=(titan.bean.Login)session.getAttribute("login");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head style="height:100%; overflow:hidden; margin:0px; padding:0px;">
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" type="image/ico" href="/images/favicon.ico" />
    <title>Welcome to Bank Manage System</title>
    <link href="css/styles.css" type="text/css" media="screen" rel="stylesheet" />
    <link href="css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
   	<link href="css/keyboard.css" type="text/css"  rel="stylesheet" />
   	<script src="js/index.js" type="text/javascript" ></script>
   	<script src="js/account_management.js" type="text/javascript" ></script>
    <script src="js/jquery-1.6.2.min.js"></script>
    <script src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script src="js/jquery.keyboard.extension-typing.js" type="text/javascript" ></script>
    <script src="js/jquery.keyboard.js" type="text/javascript" ></script>
</head>
<body id="homepage">
    <div id="wrapper">
        <div id="content">
			<%@ include file="header.jsp"%>
<div id="content_main">
            	<div  id="content_left">
            		<div id="content_list">
            			<div ><a href="homepage.jsp">Open Account</a></div>
            			<div><a href="save.jsp">Save</a></div>
            			<div><a href="withdraw.jsp">Withdraw</a></div>
            			<div><a href="check.jsp">Check</a></div>
            			<div id="selected">Transfer</div>
            			<div><a href="cpwd.jsp">Change PWD</a></div>
            			<div><a href="close.jsp">Close Account</a></div>
            		</div>
            		
            	</div>
            	<div  id="content_right"><form class="form">
            		<table style="margin:20px 0 0 30px;">
            			<tr>
            				<td>Account ID:</td>
            				<td><input type="text" id="ac_id"/></td>
            			</tr>
            			<tr>
            				<td>Your Password:</td>
            				<td><input type="password"  id="pwd"/></td>
            			</tr>
            			<tr>
            				<td>Your Name:</td>
            				<td><input type="text" id="username"/></td>
            			</tr>
            			<tr>
            				<td>Identifier:</td>
            				<td><input type="text" id="identifier"/></td>
            			</tr>
            			<tr>
            				<td>Target ID:</td>
            				<td><input type="text" id="target_id"/></td>
            			</tr>
            			<tr>
            				<td>Target Name:</td>
            				<td><input type="text" id="target_name"/></td>
            			</tr>
            			<tr>
            				<td>Amount:</td>
            				<td><input type="text" id="amount"/></td>
            			</tr>
            			
            			<tr><td></td><td></td></tr>
            		</table>
            		<button id="openCount" type="button" class="positive" name="Confirm" onclick="toTransfer();">Confirm</button>
            		</form>
            		
            	</div>
            	
            </div>
            
        </div>
    </div>
    <%@ include file="foot.jsp"%>

 
</body>

