package titan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import titan.bean.Account;
import titan.bean.Login;
import titan.dao.AccountDAO;
import titan.dao.LoginDAO;

public class AccountServ extends HttpServlet{

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
				// TODO Auto-generated method stub
				try{
					 HttpSession session = req.getSession();
					 resp.setContentType( "text/json;charset=utf-8"); 
					 resp.setHeader( "Cache-Control", "no-cache"); 
					 resp.setHeader( "Pargma", "no-cache"); 
					 
					// 创建响应输出，通过out将返回值写入response
				     PrintWriter out  = resp.getWriter();
					 
				     String identifier = req.getParameter("identifier").trim();
				     String username = req.getParameter("username").trim();
				     Float balance = Float.parseFloat(req.getParameter("balance").trim());
				     String type = req.getParameter("user_type").trim() +  req.getParameter("account_type").trim();
				     String pwd = req.getParameter("pwd").trim();
				     boolean sts = true;	     
				     
				     Login login = (Login) req.getSession().getAttribute("login");
				     
				     Account ac = new Account();     
				     ac.setIdentifier(identifier);
				     ac.setUsername(username);
				     ac.setBalance(balance);
				     ac.setType(type);
				     ac.setPassword(pwd);
				     ac.setSts(sts);
	
				     AccountDAO actDao = new AccountDAO();
				     
				     int id =  actDao.add(ac,login.getLoginid());
				     out.write(id+""); 
				     out.close();
				}catch(Exception e){
					e.printStackTrace();	
			    }
		}
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.doGet(req, resp);
		}

}
