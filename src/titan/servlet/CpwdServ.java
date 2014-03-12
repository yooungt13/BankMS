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
import titan.dao.LogDAO;
import titan.dao.UserDAO;

public class CpwdServ extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try{
			 HttpSession session = req.getSession();
			 resp.setContentType( "text/json;charset=utf-8"); 
			 resp.setHeader( "Cache-Control", "no-cache"); 
			 resp.setHeader( "Pargma", "no-cache"); 
			 
			// 创建响应输出，通过out将返回值写入response
		     PrintWriter out  = resp.getWriter();
			 
		     int ac_id = Integer.parseInt(req.getParameter("ac_id").trim());
		     String identifier = req.getParameter("identifier").trim();   
		     String old_pwd = req.getParameter("old_pwd").trim();
		     String new_pwd = req.getParameter("new_pwd").trim();
		     
		     Login login = (Login) req.getSession().getAttribute("login");
		     
		     Account ac = new Account();     
		     ac.setId(ac_id);
		     ac.setIdentifier(identifier);
		     ac.setPassword(old_pwd);

		     UserDAO userDao = new UserDAO();
		     
		     if( userDao.cpwd(ac,new_pwd,login.getLoginid() ) ){		    	 	    	 
		    	 out.write("true"); 
		     }else{
		    	 out.write("false"); 
		     }
		    
	    	out.close();
		}catch(Exception e){
			e.printStackTrace();	
	    }
	}

}
