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
            			<div id="selected">Bank Report</div>
            			<div><a href="hr.jsp">Human Resource</a></div>
            		</div>
            		
            	</div>
            	<div  id="content_right"><form class="form">
            		<table style="margin:20px 0 0 30px;">
            			<tr>  							              				   			
            				<td><select id="date_type" style="width:80px;">
            					<option value="0">Daily</option>
            					<option value="1">Month</option>
            					<option value="2">Season</option>
            					<option value="3">Year</option>
            				</select></td>          				
            				<td><input type="text" id="date" style="width:90px;"/></td>          				
            				<td><input type="hidden"  id="bank_id" style="width:80px;" value="<%=login.getBank() %>"/></td>   	        
            				<td>
		            				<button type="button"  class="positive"  name="Confirm"  onclick="toCheckBankReport();" style="margin-left:5px;margin-bottom:-10px;">Confirm</button>
		            		</td>         			
		            		
            			</tr>
            		</table>
            		</form>
					<div id="Report_box" ></div>    		
            	</div>
            	
            </div>
            
        </div>
    </div>
    <%@ include file="foot.jsp"%>

 
</body>

