package titan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import titan.bean.Login;
import titan.dao.LoginDAO;

public class LoginServ extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
				// TODO Auto-generated method stub
				super.doGet(req, resp);
		}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			 HttpSession session = req.getSession();
			 
		     int loginid = Integer.parseInt(req.getParameter("loginid"));
		     String pwd = req.getParameter("pwd");

		     LoginDAO loginDao = new LoginDAO();
		     
			Login loginbean = loginDao.findById(loginid);
		     
		     if (  loginbean != null  ) {
				    if (  ( pwd != null )  && ( pwd.trim().equals(loginbean.getPassword()) ) ) {
					       session.setAttribute("login", loginbean);
					       String logon_suc = "homepage.jsp";
					       resp.sendRedirect(logon_suc);
					       return;
				      }
		     }
		     String logon_fail = "login.jsp";
		     resp.sendRedirect(logon_fail);
		     return;
		}

}
