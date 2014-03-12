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
   	<script src="js/account_management.js" type="text/javascript" ></script>
   	<script src="js/index.js" type="text/javascript" ></script>
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
            			<div id="selected">Open Account</div>
            			<div><a href="save.jsp">Save</a></div>
            			<div><a href="withdraw.jsp">Withdraw</a></div>
            			<div><a href="check.jsp">Check</a></div>
            			<div><a href="transfer.jsp">Transfer</a></div>
            			<div><a href="cpwd.jsp">Change PWD</a></div>
            			<div><a href="close.jsp">Close Account</a></div>
            		</div>
            		
            	</div>
            	<div  id="content_right">
            	<form id="open_account" class="form" action="AccountServ"  method="post"   autocomplete="off">
            		<table style="margin:20px 0 0 30px;">
            			<tr>
            				<td>Username:</td>
            				<td><input type="text" name="username" id="username"/></td>
            			</tr>
            			<tr>
            				<td>Identifier:</td>
            				<td><input type="text" name="identifier" id="identifier"/></td>
            			</tr>
            			<tr>
            				<td>Type:</td>
            				<td><select name="account_type" id="account_type">
										 <option value="0">Current</option>
										 <option value="1">Deposit</option>
										</select>
							</td>
							<td><select name="user_type" id="user_type">
										 <option value="0">Normal</option>
										 <option value="1">VIP</option>
										 <option value="2">Company</option>
										</select>
							</td>
            			</tr>
            			<tr>
            				<td>Start Cash:</td>
            				<td><input type="text" name="balance" id="balance"/></td>
            			</tr>
            			<tr>
            				<td>-------------------</td>
            				<td>-------------------------------</td>
            				<td>-------------------------------</td>
            			</tr>
            			<tr>
            				<td>Your Password:</td>
            				<td><input type="password" name="pwd" id="pwd"/></td>
            			</tr>
            			<tr><td></td><td></td></tr>
            		</table>
            		<button type="button" class="positive" name="Confirm" style="margin-left:40px;" onclick="toSubmitAccount();">Confirm</button>
            		<button type="button" class="positive" name="Confirm" style="margin-left:45px;" onclick="createSuper();">Create Super</button>        		
            		</form>         		
            	</div>            	
            </div>          
        </div>
    </div>
    <%@ include file="foot.jsp"%>

 
</body>

