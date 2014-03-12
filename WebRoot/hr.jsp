<%@page import="titan.dao.LogDAO"%>
<%@ page language="java"  import="java.util.*,java.sql.ResultSet" pageEncoding="UTF-8"%>
<%
	titan.bean.Login login=(titan.bean.Login)session.getAttribute("login");
	titan.dao.LoginDAO loginDao = new titan.dao.LoginDAO(); 
    ResultSet rs = loginDao.findAll();
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
   	<script src="js/report_management.js" type="text/javascript" ></script>
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
            			<div ><a href="logcheck.jsp">Personal Report</a></div>
            			<div><a href="deptlog.jsp">Dept Report</a></div>
            			<div><a href="banklog.jsp">Bank Report</a></div>
            			<div  id="selected">Human Resource</div>
            		</div>
            		
            	</div>
            	<div  id="content_right">
            	<table style="margin:25px 0 0 30px;">
            		<tr><td>ID</td><td>Name</td><td>Department</td><td>Bank</td></tr>
            		<% while( rs.next() ){%>
	            		<tr>
		            		<td><%=rs.getInt(1) %></td>
		            		<td style="width:50%;"><%=rs.getString(4) %></td>
		            		<td><%=rs.getString(5) %></td>
		            		<td><%=rs.getString(6) %></td>
	            		</tr>
            		<%} %>
            	</table>  
            	<% if( login.getType() == 3 ){ %>
            			<button type="button"  class="positive"  onclick="" style="margin:20px 0 0 40px;">Manage</button>
            	<%} %>		
            	</div>       	
            </div>           
        </div>
    </div>
    <%@ include file="foot.jsp"%>
</body>

